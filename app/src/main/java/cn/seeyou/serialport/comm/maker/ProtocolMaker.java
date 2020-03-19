package cn.seeyou.serialport.comm.maker;

import com.seeyou.comm.framework.commframe.makermanager.IMaker;
import com.seeyou.comm.framework.utils.ByteConverter;
import com.seeyou.comm.framework.utils.CRCToolsA;
import com.seeyou.comm.framework.utils.ICrcTools;

import cn.seeyou.serialport.comm.agreement.ProtocolDefine;

/**
 * Create by seeyou
 */
public abstract class ProtocolMaker implements IMaker {
    private static byte bPackageid = (byte)0x00;
    private ICrcTools createCrc;

    public abstract byte[] createData();
    public abstract byte createCmd();

    public ProtocolMaker(){
        createCrc = new CRCToolsA();
    }

    @Override
    public Object make() {
        // TODO Auto-generated method stub
        byte[] commPkg;
        byte[] commDat = createData();
        if(commDat != null){
            commPkg = new byte[commDat.length + ProtocolDefine.FRAME.MIN_PACKAGE_LENGTH ];
        }
        else {
            commPkg = new byte[ProtocolDefine.FRAME.MIN_PACKAGE_LENGTH ];
        }
        makeHead(commPkg);
        makeCmd(commPkg, createCmd());
        makeData(commDat,commPkg);
        makeCrcAndTail(commPkg);
        return commPkg;
    }




    private void makeHead(byte[] pkg) {
        short iFrameDataLen = (short)pkg.length;
        byte[] bFrameHeadDataLen = ByteConverter.shortToBytes(iFrameDataLen);
        pkg[ProtocolDefine.FRAME.FRAME_MC_HEAD_INDEX] = ProtocolDefine.FRAME.FRAME_MC_HEAD;
        pkg[ProtocolDefine.FRAME.FRAME_MC_LEN_H_INDEX] = bFrameHeadDataLen[1];
        pkg[ProtocolDefine.FRAME.FRAME_MC_LEN_L_INDEX] = bFrameHeadDataLen[0];
        pkg[ProtocolDefine.FRAME.FRAME_MC_NOTLEN_H_INDEX] = (byte)(~bFrameHeadDataLen[1]);
        pkg[ProtocolDefine.FRAME.FRAME_MC_NOTLEN_L_INDEX] = (byte)(~bFrameHeadDataLen[0]);
        pkg[ProtocolDefine.FRAME.FRAME_MC_SRCADDR_INDEX] = (byte)ProtocolDefine.FRAME.COMMINUCAITON_TAR_ADDR;      //源地址
        pkg[ProtocolDefine.FRAME.FRAME_MC_TAGADDR_INDEX] = (byte)ProtocolDefine.FRAME.COMMINUCATION_SRC_ADDR;      //目的地址
        pkg[ProtocolDefine.FRAME.FRAME_MC_PKGID_INDEX] = getPackgeId();      //包id
    }
    private void makeCmd(byte[] pkg, byte cmd){
        pkg[ProtocolDefine.FRAME.FRAME_MC_CMD_INDEX] = cmd;
    }
    private void makeData(byte[] src,byte[] pkg){
        System.arraycopy(src,0 , pkg, ProtocolDefine.FRAME.FRAME_MC_DATA_INDEX, src.length);
    }

    private void makeCrcAndTail(byte[] pkg){
        int crcLength = pkg.length - 3;
        int sCRCData = (createCrc.handle(pkg, crcLength));
        byte[] bCRCData = ByteConverter.shortToBytesBig((short)sCRCData);
        pkg[crcLength] = bCRCData[0];
        pkg[crcLength+1] = bCRCData[1];
        pkg[crcLength+2] = ProtocolDefine.FRAME.FRAME_MC_TAIL;
    }

    private static byte getPackgeId(){
        return bPackageid++;
    }
}
