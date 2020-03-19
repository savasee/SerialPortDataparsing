package com.seeyou.comm.framework.commframe.makermanager;

/**
 * Create by seeyou
 * seeyou1052@foxmail.com
 */
public class MakerItem {
	int mId;
	int mFid;
	int mSid;
	IMaker mMaker;
	
	public void setId(int id){
		this.mId = id;
	}
	public int getId(){
		return this.mId;		
	}
	public void setFid(int fid){
		this.mFid = fid;
	}
	public int getFid(){
		return this.mFid;		
	}
	public void setSid(int sid){
		this.mSid = sid;
	}
	public int getSid(){
		return this.mSid;		
	}
	
	public void setMaker(IMaker maker){
		this.mMaker = maker;
	}
	public IMaker getMaker(){
		return mMaker;
	}
}
