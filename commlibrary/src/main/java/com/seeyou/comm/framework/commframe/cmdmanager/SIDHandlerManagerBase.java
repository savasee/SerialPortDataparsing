package com.seeyou.comm.framework.commframe.cmdmanager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import android.content.Context;

import com.seeyou.comm.framework.commframe.ICMDHandler;

/**
 * Create by seeyou
 * seeyou1052@foxmail.com
 */
public class SIDHandlerManagerBase{
	private final static byte FID_DEFAULT_CMD = (byte)(0xFF);		//
	protected Context mContext;
	private List<HandlerItem>				mList;
    
    protected SIDHandlerManagerBase(Context context){
    	this.mContext = context;    	 
    	mList = new ArrayList<HandlerItem>();    	
    }
    
    protected void addItem(int fid, int sid, ICMDHandler handler){
    	HandlerItem item = new HandlerItem();
    	//item.setID(id);
    	item.setFID(fid);
    	item.setSID(sid);
    	item.setHandler(handler);
    	mList.add(item);
    }
    
    protected void addItem(int cmd, ICMDHandler handler){
    	HandlerItem item = new HandlerItem();
    	//item.setID(id);
    	item.setCmd(cmd);    	
    	item.setHandler(handler);
    	mList.add(item);
    }
    
    public ICMDHandler getHandler(int fid, int sid){
    	ICMDHandler handler = searchHandler(fid, sid);
    	if(handler == null){
    		handler = loadDefaultCmdHandler(fid, sid);
    	}
    	
    	return handler;
    }
	
    private ICMDHandler searchHandler(int fid, int sid){
    	Iterator<HandlerItem> sListIterator = mList.iterator();
		while (sListIterator.hasNext()){
			HandlerItem item = (HandlerItem) sListIterator.next();
			int cmd = ((fid&0xff)<<8) + (sid&0xff);
			if(cmd == item.getCmd()){
				return item.getHandler();
			}
//			if ((fid == item.getFId()) && (sid == item.getSId())){
//				return item.getHandler();
//			}
		}
		return null;   
    }
    
    private ICMDHandler loadDefaultCmdHandler(int fid, int sid){
    	return searchHandler(FID_DEFAULT_CMD, FID_DEFAULT_CMD);
    }
}
