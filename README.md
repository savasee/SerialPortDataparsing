# SerialPortDataparsing
基于串口通讯的通讯层框架，封装了数据缓冲池和数据解析等，可更改拓展于蓝牙，wifi通讯等

#用法

拷贝commlibrary
根据app源码参照
实现handler类 协议层类 maker发送数据层类 和manger类
因为协议的不同，故需要对协议的定义进行修改，通信原理不变

#原理

定义LoopBuffer字节缓冲池，所有串口接收数据存入缓冲池中，按照数据协议包格式进行读取。
