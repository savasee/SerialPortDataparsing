package com.seeyou.comm.framework.commframe;

/**
 * @Description.循环buffer处理
 */
public class LoopBuffer{	
	private static final String TAG = "CommLoopBufferBase";
	
	private byte[] mBuffer = null; //数据缓存buffer
	private int mRdIndex = 0;    //读数据数组下标
	private int mWrIndex = 0;   //写数据数组下标
	private int	mBufferLen;
	private int mDataLen = 0;
		
	public void init(int len) {	   	
	    this.mBufferLen = len;
	    if ( null == mBuffer ) {	    	
	    	mBuffer = new byte[mBufferLen];
		    mBuffer[0] = (byte)(0x00);
	    }	
	    mRdIndex = 0;
	    mWrIndex = 0;
	}
	
	public int getBufferLength(){
		return this.mBufferLen;
	}
	
	public synchronized int getCurrentRd(){
		return this.mRdIndex;
	}
	public synchronized byte[] getBuffer(){
		return this.mBuffer;
	}
	/*
	 * 返回缓冲区有效数据长度
	 * */
	public int getDataLen(){
		return this.mDataLen;
	}
	/*
	 * 返回缓冲区剩余字节长度
	 * */
	public int getSpaceLen(){
		return mBufferLen-mDataLen;
	}
	
	public synchronized void incDataLength(){
		if(mDataLen < mBufferLen){
			mDataLen += 1;
		}
	}
	
	public void incDataLength(int len)	{
		for (int i = 0; i < len; i++){
			incDataLength();
		}
	}
	
	public synchronized void decDataLength(){
		if(mDataLen > 0){
			mDataLen -= 1;
		}		
	}	
	
	public void decDataLength(int len)	{
		for (int i = 0; i < len; i++){
			decDataLength();
		}
	}
	
	public synchronized void incRdIndex(int len){
		if (mRdIndex + len < mBufferLen){
			mRdIndex += len;
		} else {
			mRdIndex = mRdIndex + len - mBufferLen;
		}			
	}

	private synchronized void incWrIndex(int len){
		if (mWrIndex + len < mBufferLen){
			mWrIndex += len;
		}else{
			mWrIndex = mWrIndex + len - mBufferLen;
		}
	}

	/**
	 * 写数据到缓冲区，返回成功写入数据长度 
	 * */
	public int pushData(byte[] src, int idx, int len){
		int length = getSpaceLen();
		
		if(length < len){
			return pushNByte(src, idx, length);
		} else {
			return pushNByte(src, idx, len);
		}		
	}
	
	/**
	 * 写数据到缓冲区
	 * 写之前需要调用getDataLen()方法确认空间足够
	 * */
	public int pushNByte(byte[] src, int idx, int len)	{
		for (int i = 0; i < len; i++){
			this.mBuffer[mWrIndex] = src[idx + i];
			incWrIndex(1);
			incDataLength(1);
		}
		return len;
	}
	
	public int pullData(byte[] bytes, int len){
		int dataLen = getDataLen();
		if(dataLen < len){
			return pullNByte(bytes, dataLen);
		} else {
			return pullNByte(bytes, len);
		}
	}
	
	public int pullNByte(byte[] bytes, int len){
		for(int i = 0; i < len; i++){
			bytes[i] = this.mBuffer[mRdIndex];
			incRdIndex(1);
			decDataLength(1);
		}
		return len;
	}
	
	

	

	
}
