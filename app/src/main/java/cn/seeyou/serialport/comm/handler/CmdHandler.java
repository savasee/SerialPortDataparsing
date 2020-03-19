package cn.seeyou.serialport.comm.handler;

import android.content.Context;

import com.seeyou.comm.framework.commframe.ICMDHandler;

import cn.seeyou.serialport.comm.manager.CommManager;

/**
 * Create by seeyou
 */
public class CmdHandler implements ICMDHandler {

    private Context mContext;
    public CmdHandler(Context context, CommManager manager){
        this.mContext = context;
    }
    @Override
    public void handle(byte[] bytes, byte packageid) {
        parseProtocol(bytes);
    }
    //数据解析
    public void  parseProtocol(byte[] datas){

    }
}
