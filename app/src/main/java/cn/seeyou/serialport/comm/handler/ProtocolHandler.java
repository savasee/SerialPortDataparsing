package cn.seeyou.serialport.comm.handler;

import android.content.Context;

import com.seeyou.comm.framework.commframe.ICMDHandler;
import com.seeyou.comm.framework.commframe.IProtocolHandler;
import com.seeyou.comm.framework.commframe.KAChecker;
import com.seeyou.comm.framework.utils.ICrcTools;

import cn.seeyou.serialport.comm.agreement.ProtocolDefine;

/**
 * Create by seeyou
 */
public class ProtocolHandler implements IProtocolHandler {
    private static final String TAG = "ProtocolHandler";
    private ICrcTools crcCreater;

    private ICMDHandler mCmdManager = null;
    private KAChecker mKAChecker = null;
    private Context context;

    public ProtocolHandler(ICMDHandler manager, ICrcTools crcTools) {
        mCmdManager = manager;
        crcCreater = crcTools;
    }
    public ProtocolHandler(Context context, ICrcTools crcTools) {
        crcCreater = crcTools;
        this.context = context;
    }
    public boolean checkHead(byte[] src, int pkglen){
        int head = byteToInt(src[0]);
        return head == byteToInt(ProtocolDefine.FRAME.FRAME_MC_HEAD);
    }

    public boolean checkTail(byte[] src, int pkglen){
        int length = (int)getShort(byteToInt(src[1]), byteToInt(src[2]));
        int tail = byteToInt(src[length-1]);
        return tail == byteToInt(ProtocolDefine.FRAME.FRAME_MC_TAIL);
    }

    public boolean checkLength(byte[] src){
        int length = (int)getShort(byteToInt(src[1]), byteToInt(src[2]));
        return length >=12 && length <= 1036;
    }


    public void parseCmdPkg(byte[] src, int pkglen){
        int cmdPkgLen = pkglen- ProtocolDefine.FRAME.FRAME_NOTUSE_LENTH ;//减去（头+lenth+lenth取非+Crc+tail）长度 得到从src开始到data结束
        byte cmdPkg[] = new byte[cmdPkgLen];
        byte pkgId = src[ProtocolDefine.FRAME.FRAME_MC_PKGID_INDEX];
        System.arraycopy(src,
                ProtocolDefine.FRAME.FRAME_MC_SRCADDR_INDEX,
                cmdPkg,
                0,
                cmdPkgLen);
        mCmdManager.handle(cmdPkg, pkgId);
    }
    
    public boolean checkFrameCrc(byte[] pkgData, int crcFramelen){
        int sumCrc = crcCreater.handle(pkgData, crcFramelen) & 0xffff;

        int crc = (pkgData[(crcFramelen+1) % ProtocolDefine.FRAME.FRAME_MC_lENGTH]&0xFF)
                | ((pkgData[crcFramelen % ProtocolDefine.FRAME.FRAME_MC_lENGTH] << 8 )&0xFF00);
        return sumCrc == crc;
    }
    
    public boolean checkFrameTail(byte[] src, int startIndex, int pkglen){
        int tailIndex = (startIndex + pkglen-1) % ProtocolDefine.FRAME.FRAME_MC_lENGTH;
        return src[tailIndex] == ProtocolDefine.FRAME.FRAME_MC_TAIL;
    }

    public boolean checkCommFrameHead(byte[] src, int startIndex){
        int headIndex = (startIndex + ProtocolDefine.FRAME.FRAME_MC_HEAD_INDEX) % ProtocolDefine.FRAME.FRAME_MC_lENGTH;
//        boolean ret;
//        ret = (src[headIndex] == ProtocolDefine.FRAME.FRAME_MC_HEAD);
//        Log.e("ret:",""+ ret);
        return (src[headIndex] == ProtocolDefine.FRAME.FRAME_MC_HEAD);
//        {
//            return true;
//        }
//        else {
//            return false;
//        }
    }

    public boolean checkPkgLength(byte[] src, int startIndex){
        int pkgLen = getPkgLength(src, startIndex);
        int pkgNotLen = getPkgLengthNot(src, startIndex);
        return (((~pkgLen)&0xffff)  == ((pkgNotLen)&0xffff));
    }

    //验证src
    public boolean checkFrameSourceAdd(byte[] src, int startIndex){
        byte source = src[(startIndex + ProtocolDefine.FRAME.FRAME_MC_SRCADDR_INDEX)%ProtocolDefine.FRAME.FRAME_MC_lENGTH];
        return ProtocolDefine.FRAME.COMMINUCATION_SRC_ADDR == source;
    }

    //验证target
    public boolean checkFrameTarget(byte[] src, int startIndex){
        byte target = src[(startIndex + ProtocolDefine.FRAME.FRAME_MC_TAGADDR_INDEX)%ProtocolDefine.FRAME.FRAME_MC_lENGTH];
        if(target == ProtocolDefine.FRAME.COMMINUCATION_BROADCAST_ADDR){
            return true;
        } else {
            return ProtocolDefine.FRAME.COMMINUCAITON_TAR_ADDR == target;
        }
    }

    //获取pkg
    public int getPkgLength(byte[] src, int startIndex){
        byte lenH = src[(startIndex+ProtocolDefine.FRAME.FRAME_MC_LEN_H_INDEX)%ProtocolDefine.FRAME.FRAME_MC_lENGTH];
        byte lenL = src[(startIndex+ProtocolDefine.FRAME.FRAME_MC_LEN_L_INDEX)%ProtocolDefine.FRAME.FRAME_MC_lENGTH];

        int length = ((lenL & 0xFF)
                | ((lenH<<8) & 0xFF00));
        return length;
    }

    //获取LengthNot域
    public int getPkgLengthNot(byte[] src, int startIndex){
        byte lenH = src[(startIndex+ProtocolDefine.FRAME.FRAME_MC_NOTLEN_H_INDEX)%ProtocolDefine.FRAME.FRAME_MC_lENGTH];
        byte lenL = src[(startIndex+ProtocolDefine.FRAME.FRAME_MC_NOTLEN_L_INDEX)%ProtocolDefine.FRAME.FRAME_MC_lENGTH];
        int length = ((lenL & 0xFF)
                | ((lenH<<8) & 0xFF00));
        return length;
    }

    //获取包最小长度
    public int getMinFrameLength() {
        return ProtocolDefine.FRAME.MIN_PACKAGE_LENGTH;
    }
    //获取最大包长度
    public int getMaxFrameLength(){
        return ProtocolDefine.FRAME.MAX_PACKAGE_LENGTH;
    }

    private static int byteToInt(byte b) {
        //Java 总是把 byte 当做有符处理；我们可以通过将其和 0xFF 进行二进制与得到它的无符值
        return b & 0xFF;
    }

    private static short getShort(byte byte1,byte byte2) {
        short sho= (short)(byte2 | (((short)byte1) << 8));
        return sho;
    }

    private static short getShort(int byte1,int byte2) {
        short sho= (short)(byte2 | (((short)byte1) << 8));
        return sho;
    }

}
