package cn.seeyou.serialport.comm.agreement;

import com.seeyou.comm.framework.commframe.ICMDHandler;

import cn.seeyou.serialport.comm.handler.CmdHandler;

/**
 * Create by seeyou
 */
public enum HandlerConfig {
    //handler这里注册
    HEART_BEAT_PKG(ProtocolDefine.FID.FID_BASE_ID, CmdHandler.class);

    private byte mFid;
    private String mClassName;
    <T extends ICMDHandler> HandlerConfig(byte fidCmd, Class<T> clz){
        this.mFid = fidCmd;
        this.mClassName = clz.getName();
    }

    public String getClassName(){
        return this.mClassName;
    }

    public int getCmd(){
        return (this.mFid & 0xff);
    }
}
