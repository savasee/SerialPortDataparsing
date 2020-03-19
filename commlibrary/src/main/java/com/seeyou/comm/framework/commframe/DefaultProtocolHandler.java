package com.seeyou.comm.framework.commframe;


public class DefaultProtocolHandler implements IProtocolHandler{

	@Override
	public void parseCmdPkg(byte[] src, int pkglen) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMinFrameLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean checkCommFrameHead(byte[] src, int startIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkFrameCrc(byte[] pkgData, int crcFramelen) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkFrameTail(byte[] src, int startIndex, int pkglen) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkPkgLength(byte[] src, int startIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkFrameSourceAdd(byte[] src, int startIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkFrameTarget(byte[] src, int startIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getPkgLength(byte[] src, int startIndex) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPkgLengthNot(byte[] src, int startIndex) {
		// TODO Auto-generated method stub
		return 0;
	}

}
