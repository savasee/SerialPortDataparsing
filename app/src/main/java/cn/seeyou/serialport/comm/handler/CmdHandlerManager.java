package cn.seeyou.serialport.comm.handler;

import android.content.Context;
import android.util.Log;

import com.seeyou.comm.framework.commframe.ICMDHandler;
import com.seeyou.comm.framework.commframe.cmdmanager.SIDHandlerManagerBase;

import java.lang.reflect.Constructor;
import java.util.EnumSet;

import cn.seeyou.serialport.comm.agreement.HandlerConfig;
import cn.seeyou.serialport.comm.manager.CommManager;

/**
 * Create by seeyou
 */
public class CmdHandlerManager extends SIDHandlerManagerBase implements ICMDHandler {
    private final static String TAG = "CmdHandlerManager";

    private CommManager mCommManagerr;

    public CmdHandlerManager(Context mContext, CommManager commManagerr){
        super(mContext);
        this.mCommManagerr = commManagerr;
        initCmdHandler();
    }
    private void initCmdHandler(){
        Class[] parameterTypes = {Context.class, CommManager.class};

        EnumSet<HandlerConfig> cmdConfig = EnumSet.allOf(HandlerConfig.class);
        for(HandlerConfig item : cmdConfig){
            Constructor con = null;
            Class clz = null;
            try {
                clz = (Class<ICMDHandler>) Class.forName(item.getClassName());
                con = clz.getConstructor(parameterTypes);
                addItem(item.getCmd(), (ICMDHandler) con.newInstance(mContext, mCommManagerr));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return;
    }
    //获取指定handler
    @Override
    public void handle(byte[] bytes, byte packageid) {
        ICMDHandler CmdHandler = getHandler(bytes[0], bytes[1]);
        //数据数组无Handler指定标识 则可任意规定一个
        //ICMDHandler CmdHandler = getHandler(00, 0xA0);
        handlerProcessor(CmdHandler,bytes,packageid);
        return;
    }

    /**
     * @param handler
     * @param bytes
     * @param packageid
     * @exception命令处理handler
     */
    private synchronized void handlerProcessor(final ICMDHandler handler, final byte[] bytes, final byte packageid){
        try{
            if ( null == handler ) {
                Log.e(TAG,"evevatorhandle function in getHandler bytes[0]:" + Integer.toHexString(bytes[0])
                        + " ,bytes[1]:" + Integer.toHexString(bytes[1]));
                return ;
            }
            handler.handle(bytes, packageid);

        } catch(Exception ex){
            ex.printStackTrace();
        }
        return;
    }

}
