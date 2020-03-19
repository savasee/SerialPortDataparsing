package com.seeyou.comm.framework.commframe;

import java.nio.ByteBuffer;

public interface IReadwriter {
	void start(String uartPath, int port, int baudrate);
	void stop();
	ByteBuffer read(byte[] bytes);
	void sendData(byte[] bytes);
}
