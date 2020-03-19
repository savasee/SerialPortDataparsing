package cn.seeyou.serialport.comm.maker;



import java.util.HashMap;
import java.util.Map;

/**
 * Create by seeyou
 * on 2019/10/7
 */
public class DatasMaker extends ProtocolMaker {
    private byte cmd;
    private byte[] data;
    public DatasMaker(){

    }

    @Override
    public byte[] createData() {
        return data;
    }

    @Override
    public byte createCmd() {
        return cmd;
    }
    public void SetCmd(byte Ncmd){
        cmd = Ncmd;
    }
    public void SetData(byte[] Ndata){
        data = Ndata;
    }
}
