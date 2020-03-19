package cn.seeyou.serialport.comm.maker;

import android.content.Context;
import com.seeyou.comm.framework.commframe.makermanager.MakerManagerBase;

import cn.seeyou.serialport.comm.agreement.HandlerConfig;

/**
 * Create by seeyou
 * on 2019/10/7
 */
public class MakerManager extends MakerManagerBase {
    public MakerManager(Context context){
        super(context);
        initMakerList();
    }

    private void initMakerList(){
        addItem(HandlerConfig.HEART_BEAT_PKG.getCmd(),
                new DatasMaker());
    }
}
