package com.seeyou.comm.framework.commframe;

public interface IProtocolHandler {
	public void parseCmdPkg(byte[] src, int pkglen);    
	public int getMinFrameLength();
	public boolean checkCommFrameHead(byte[] src, int startIndex);
	public boolean checkFrameCrc(byte[] pkgData, int crcFramelen);    
	public boolean checkFrameTail(byte[] src, int startIndex, int pkglen);
	public boolean checkPkgLength(byte[] src, int startIndex);    
	public boolean checkFrameSourceAdd(byte[] src, int startIndex);    
	public boolean checkFrameTarget(byte[] src, int startIndex);
	public int getPkgLength(byte[] src, int startIndex);    
	public int getPkgLengthNot(byte[] src, int startIndex);
}
