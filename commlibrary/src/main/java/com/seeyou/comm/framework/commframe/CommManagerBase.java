package com.seeyou.comm.framework.commframe;

import android.content.Context;
import android.util.Log;

/**
 * 基础通信框架
 * */
public class CommManagerBase {

	private static final String TAG = "CommManagerBase";

	private IReadwriter mUartReadwriter;
    private final Context mContext;

    
    private LoopBuffer mRecLoopBuffer = null;
    private BaseProtocolHandler mProtocolHandler = null;	//
	 public void init(CommConfiguration config){
    	mRecLoopBuffer.init(config.getFramelength());				
		mProtocolHandler.init(config.getProtocolHandler());
		mUartReadwriter.start(config.getDevicePath(), config.getDevicePort(), config.getBaudrate());
		mProtocolHandler.start();
		
		Log.i(TAG, "init() end");
    }
    
    /**
     * @param 
     * @explain 
     */
	protected CommManagerBase(Context context) {
		mContext = context;
		mRecLoopBuffer = new LoopBuffer();
		mUartReadwriter = new UartReadwriter(mContext, mRecLoopBuffer);


    	mProtocolHandler = new BaseProtocolHandler(mRecLoopBuffer);
    	
    }

	public void sendDirect(byte[] directDatas) {
		mUartReadwriter.sendData(directDatas);
	}

}
