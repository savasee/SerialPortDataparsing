package cn.seeyou.serialport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import cn.seeyou.serialport.comm.maker.DatasMaker;
import cn.seeyou.serialport.comm.manager.CommManager;
import cn.seeyou.serialport.comm.manager.SystemManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化串口配置
        SystemManager.getInstance(this).init();
        SendDatas();
    }

    /**
     * 打包发送串口数据
     */
    public void SendDatas(){
        DatasMaker maker = new DatasMaker();
        maker.SetCmd((byte) 0xff);
        maker.SetData(new byte[5]);
        CommManager.getInstance(this).sendDirect((byte[]) maker.make());
    }
}
