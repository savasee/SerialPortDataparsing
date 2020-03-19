package cn.seeyou.serialport.comm.agreement;

/**
 * Create by seeyou
 */
public class ProtocolDefine {
    public class FRAME {
        //数据包信息定义
        public static final byte FRAME_MC_HEAD = (byte) 0x7e;  //Head域 包头标志1
        public static final byte FRAME_MC_TAIL = (byte) 0x0d;  //Tail域 包尾
        public static final int FRAME_MC_lENGTH = 1036;       //Length域 通信协议包长度
        public static final int FRAME_MC_lENGTH_HEAD = 1;     //包头长度
        public static final int FRAME_MC_lENGTH_TAIL = 3;     //包尾长度

        public static final int FRAME_MC_LENGTH_READ_HEAD = 3; //读取包的前三个字节,包头(byte)+长度(short)
        public static final int FRAME_MC_LENGTH_CACHE_MCUUART = 1036;   //初始化mcu uart缓存数据大小

        public static final int MIN_PACKAGE_LENGTH = 12;  //最小包长度
        public static final int MAX_PACKAGE_LENGTH = 1036;  //最大包长度
        public static final int FRAME_NOTUSE_LENTH = 8;//头和lenth和lenth取非和crc和tail的总长度

        public static final int FRAME_MC_HEAD_INDEX = 0;  //包头字节
        public static final int FRAME_MC_LEN_H_INDEX = 1;  //
        public static final int FRAME_MC_LEN_L_INDEX = 2;  //
        public static final int FRAME_MC_NOTLEN_H_INDEX = 3;  //
        public static final int FRAME_MC_NOTLEN_L_INDEX = 4;  //
        public static final int FRAME_MC_SRCADDR_INDEX = 5;  //
        public static final int FRAME_MC_TAGADDR_INDEX = 6;  //
        public static final int FRAME_MC_PKGID_INDEX = 7;  //
        public static final int FRAME_MC_CMD_INDEX = 8;  // 之前为FID（修改成CMD）
        public static final int FRAME_MC_DATA_INDEX = 9;  //之前为SID（修改成DATA）

        public static final byte COMMINUCATION_SRC_ADDR = 0x01;
        public static final byte COMMINUCAITON_TAR_ADDR = 0x02;
        public static final byte COMMINUCATION_BROADCAST_ADDR = (byte) 0xFF;
    }

    public class FID {
        //FID功能ID声明
        public final static byte FID_BASE_ID = (byte) 0xA0;//注册Handler指定标识
    }
    public class UartConstant {
        public final static String UART_NAME = "/dev/ttyUSB";
        public final static int UART_PORT_MCU = 3;
        public final static int UART_MCU_BAUDRATE = 115200;    //115200
    }
}
