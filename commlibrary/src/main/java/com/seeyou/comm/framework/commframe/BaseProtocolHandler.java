package com.seeyou.comm.framework.commframe;

/**
 * Create by seeyou
 * seeyou1052@foxmail.com
 */
public class BaseProtocolHandler {
	private static final String TAG = "ProtocolHandlerBase";
	private Thread mThread = null;
    private boolean isRunning;
    protected LoopBuffer mLoopBuffer;
    private IProtocolHandler mProtocolHandler;
    
    public BaseProtocolHandler(LoopBuffer buffer){
    	isRunning = false;
    	mLoopBuffer = buffer;
    	mProtocolHandler = new DefaultProtocolHandler();
    }
    public void init(IProtocolHandler handler){
    	mProtocolHandler = handler;
    }
	public void start() {
        isRunning = true;
        mThread = new Thread(new ProtocolHandlerRunable());
        mThread.start();
    }

    public void stop() {
        isRunning = false;
        if (null != mThread) {
            mThread.interrupt();
            mThread = null;
        }           
    }
	
	protected class ProtocolHandlerRunable implements Runnable{
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (isRunning) {
				handleComm();				
				try {
					Thread.sleep(2);						
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}    	
    }
	
	protected void handleComm(){
    	int frameStartIndex;
    	byte[] recBuffer = mLoopBuffer.getBuffer();
    	int commFramePkgLen;
    	while(mLoopBuffer.getDataLen() > mProtocolHandler.getMinFrameLength()){    		
    		frameStartIndex = mLoopBuffer.getCurrentRd();
			if (!mProtocolHandler.checkCommFrameHead(recBuffer, frameStartIndex))	{
				//Log.e(TAG,"checkCommFrameHead 验证头部失败");
				mLoopBuffer.incRdIndex(1);
				mLoopBuffer.decDataLength(1);
				continue;
			}
			
			commFramePkgLen = mProtocolHandler.getPkgLength(recBuffer, frameStartIndex);
			
			if(!mProtocolHandler.checkPkgLength(recBuffer, frameStartIndex)){
				//Log.e(TAG,"checkPkgLength 验证包长失败");
				mLoopBuffer.incRdIndex(1);
				mLoopBuffer.decDataLength(1);
				continue;
			}
			
			if(commFramePkgLen > mLoopBuffer.getDataLen()){
				return;
			}
			if(commFramePkgLen < mProtocolHandler.getMinFrameLength()){
				mLoopBuffer.incRdIndex(1);
				mLoopBuffer.decDataLength(1);
				continue;   		
	    	} 
			
			if(!mProtocolHandler.checkFrameTail(recBuffer, frameStartIndex, commFramePkgLen)){
				//Log.e(TAG,"checkFrameTail failed");
				mLoopBuffer.incRdIndex(1);
				mLoopBuffer.decDataLength(1);
				continue;
			}
			
			// 有效包，读出解析
			byte[] readOutPkg = new byte[commFramePkgLen];
			mLoopBuffer.pullNByte(readOutPkg, commFramePkgLen);
//			Log.i(getClass().getName(), " handleComm() bytes:"+Arrays.toString(readOutPkg));
//			Log.i(getClass().getName(), " handleComm() commFramePkgLen:"+commFramePkgLen);
			if(!mProtocolHandler.checkPkgLength(recBuffer, frameStartIndex)){
				//Log.e(TAG,"checkPkgLength failed");
				return;
			}
			
			if(!mProtocolHandler.checkFrameCrc(readOutPkg, commFramePkgLen-3)){
				//Log.e(TAG,"checkFrameCrc failed");
				return;
			}
			
			mProtocolHandler.parseCmdPkg(readOutPkg, commFramePkgLen);
		}
    }
	 
	
}
