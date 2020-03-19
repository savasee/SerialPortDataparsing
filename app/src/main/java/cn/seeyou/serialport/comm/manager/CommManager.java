package cn.seeyou.serialport.comm.manager;

import android.content.Context;

import com.seeyou.comm.framework.commframe.CommManagerBase;


/**
 * Create by seeyou
 * on 2019/10/7
 */
public class CommManager extends CommManagerBase {
    private static volatile CommManager ourInstance = null;

    public static CommManager getInstance(Context context) {
        if ( null == ourInstance ) {
            synchronized (CommManager.class) {
                if (null == ourInstance ) {
                    ourInstance = new CommManager(context);
                }
            }
        }
        return ourInstance;
    }

    protected CommManager(Context context) {
        super(context);
    }
}
