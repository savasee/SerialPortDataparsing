package com.seeyou.comm.framework.commframe.cmdmanager;


import com.seeyou.comm.framework.commframe.ICMDHandler;

/**
 * Create by seeyou
 * seeyou1052@foxmail.com
 */
public class HandlerItem {
	int mCmd;
	int mFid;
	int mSid;
	ICMDHandler mHandler;
	
	public void setCmd(int id){
		this.mCmd = id;
	}
	public int getCmd(){
		return this.mCmd;		
	}
	
	public void setFID(int fid){
		this.mFid = fid;
	}
	public int getFId(){
		return this.mFid;		
	}
	
	public void setSID(int sid){
		this.mSid = sid;
	}
	public int getSId(){
		return this.mSid;		
	}
	
	public void setHandler(ICMDHandler mHandler){
		this.mHandler = mHandler;
	}
	public ICMDHandler getHandler(){
		return mHandler;
	}
}
