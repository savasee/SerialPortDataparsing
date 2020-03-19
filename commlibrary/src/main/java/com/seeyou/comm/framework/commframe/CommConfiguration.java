package com.seeyou.comm.framework.commframe;


import com.seeyou.comm.framework.commframe.makermanager.MakerManagerBase;
/**
 * Create by seeyou
 * seeyou1052@foxmail.com
 */
public class CommConfiguration {
	private int mPort;
	private int mBaudrate;
	private String mPath;
//	private ICMDHandler mCmdHandler;
	private MakerManagerBase mCmdMaker;
	private int mFramelength;
	private int mRwCacheLength;
	private IProtocolHandler mProtocolHandler;
	
	public MakerManagerBase getCmdMaker() {
		return mCmdMaker;
	}

	public void setCmdMaker(MakerManagerBase maker) {
		this.mCmdMaker = maker;
	}

	public int getDevicePort() {
		return mPort;
	}
	
	public void setDevicePort(int mPort) {
		this.mPort = mPort;
	}
	public int getBaudrate() {
		return mBaudrate;
	}
	public void setBaudrate(int mBaudrate) {
		this.mBaudrate = mBaudrate;
	}
	public String getDevicePath() {
		return mPath;
	}
	public void setDevicePath(String mPath) {
		this.mPath = mPath;
	}
	
	public int getFramelength() {
		return mFramelength;
	}
	public void setFramelength(int mFramelength) {
		this.mFramelength = mFramelength;
	}
	
	public int getCacheLength() {
		return mRwCacheLength;
	}
	public void setCacheLength(int len) {
		this.mRwCacheLength = len;
	}
	
	public IProtocolHandler getProtocolHandler() {
		return mProtocolHandler;
	}
	public void setProtocolHandler(IProtocolHandler handler) {
		this.mProtocolHandler = handler;
	}
}
