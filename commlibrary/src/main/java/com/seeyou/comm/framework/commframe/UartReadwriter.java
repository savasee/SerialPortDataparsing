package com.seeyou.comm.framework.commframe;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.Queue;

import android.content.Context;
import android.util.Log;


import cn.shorr.serialport.SerialPortConfig;
import cn.shorr.serialport.SerialPortUtil;
import cn.shorr.serialport.SerialRead;
import cn.shorr.serialport.SerialWrite;

/**
 * Create by seeyou
 * seeyou1052@foxmail.com
 */
public class UartReadwriter implements IReadwriter, SerialRead.ReadDataListener,SerialWrite.WriteDataListener {

	private static final String TAG = "UartReadwriter";

	private SerialPortUtil serialPortUtil;
	private SerialRead serialRead; //串口读取数据服务
	private SerialWrite serialWrite;
    private Context mContext;
	private LoopBuffer mLoopBuffer;

    public UartReadwriter(Context mContext, LoopBuffer loopBuffer){
    	this.mContext = mContext;
    	this.mLoopBuffer = loopBuffer;
    }

	@Override
	public void start(String uartPath, int port, int baudrate) {
		//配置串口参数
		serialPortUtil = new SerialPortUtil(mContext, new SerialPortConfig(uartPath+port, baudrate));
		//设置为调试模式，打印收发数据
		serialPortUtil.setDebug(true);
		//绑定串口服务
		serialPortUtil.bindService();
		serialRead = new SerialRead(mContext);
		serialWrite = new SerialWrite(mContext);
		serialRead.registerListener(0/*默认为0，此参数可省略*/, this);
	}

	@Override
	public void onReadData(byte[] data) {
		//Log.e(TAG, "收到数据11："+ FormatUtil.bytesToHexString(data));
		mLoopBuffer.pushData(data, 0 , data.length);
	}

	/**
     * @exception销毁释放串口
     */
	@Override
	public void stop() {
		serialRead.unRegisterListener();
		serialPortUtil.unBindService();
	}

	@Override
	public ByteBuffer read(byte[] bytes) {
		ByteBuffer buf = ByteBuffer.wrap(bytes);
		ByteBuffer.wrap(bytes,0,bytes.length);
		return buf;
	}

	/**
	 * 发送串口数据
	 * @param bytes
	 */
	@Override
	public void sendData(byte[] bytes) {
		serialWrite.sendData(mContext, 0 , bytes);
	}

	@Override
	public void onWriteData(byte[] data) {

	}
}
