package cn.seeyou.serialport.comm.manager;

import android.content.Context;

import com.seeyou.comm.framework.commframe.CommConfiguration;
import com.seeyou.comm.framework.utils.CRCToolsA;

import cn.seeyou.serialport.comm.agreement.ProtocolDefine;
import cn.seeyou.serialport.comm.handler.CmdHandlerManager;
import cn.seeyou.serialport.comm.handler.ProtocolHandler;
import cn.seeyou.serialport.comm.maker.MakerManager;

/**
 * Create by seeyou
 */
public class SystemManager {
    private final static String TAG = "SystemManager";

    private Context mContext;

    private CommManager mCommManager = null;

    private static volatile SystemManager mInstance = null;

    public static SystemManager getInstance(Context context) {
        if (null == mInstance) {
            synchronized (SystemManager.class) {
                if (null == mInstance) {
                    mInstance = new SystemManager(context);
                }
            }
        }
        return mInstance;
    }

    private SystemManager(Context context) {
        this.mContext = context;
    }

    /**
     * @exception初始化数据
     */
    public void init() {
        initInfrareUart();
        return;
    }

    /**
     * 初始化串口配置
     */
    public void initInfrareUart() {
        mCommManager = CommManager.getInstance(mContext);
        CommConfiguration config = new CommConfiguration();
        config.setBaudrate(ProtocolDefine.UartConstant.UART_MCU_BAUDRATE);
        config.setDevicePath(ProtocolDefine.UartConstant.UART_NAME);
        config.setDevicePort(ProtocolDefine.UartConstant.UART_PORT_MCU);
        config.setFramelength(ProtocolDefine.FRAME.FRAME_MC_lENGTH);
        config.setCacheLength(ProtocolDefine.FRAME.FRAME_MC_LENGTH_CACHE_MCUUART);
        config.setProtocolHandler(new ProtocolHandler(
                new CmdHandlerManager(mContext, mCommManager),
                new CRCToolsA()));
        config.setCmdMaker(new MakerManager(mContext));
        mCommManager.init(config);
    }
}
