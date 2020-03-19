package com.seeyou.comm.framework.commframe.makermanager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import android.content.Context;
/**
 * Create by seeyou
 * seeyou1052@foxmail.com
 */
public class MakerManagerBase {
	private List<MakerItem>				sendCmdMakerList;
	protected Context mContext;
    
    protected MakerManagerBase(Context context){
    	this.mContext = context; 
    	sendCmdMakerList = new ArrayList<MakerItem>();    	
    }
    

    
    protected void addItem(int fid, int sid, IMaker maker){
    	MakerItem item = new MakerItem();
    	item.setFid(fid);
    	item.setSid(sid);
    	item.setMaker(maker);
    	sendCmdMakerList.add(item);
    }
    protected void addItem(int cmd, IMaker maker){
    	MakerItem item = new MakerItem();
    	item.setFid(cmd);
    	item.setMaker(maker);
    	sendCmdMakerList.add(item);
    }
    
    public IMaker getMaker(int fid, int sid){
    	Iterator<MakerItem> sListIterator = sendCmdMakerList.iterator();
		while (sListIterator.hasNext()){
			MakerItem item = (MakerItem) sListIterator.next();

			if ((fid == item.getFid())
					&& (sid == item.getSid())){
				return item.getMaker();
			}
		}
		return null;    	
    }
}
