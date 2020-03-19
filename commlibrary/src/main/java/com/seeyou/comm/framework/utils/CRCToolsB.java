package com.seeyou.comm.framework.utils;

/**
 * Create by seeyou
 * seeyou1052@foxmail.com
 */
public class CRCToolsB implements ICrcTools {
	@Override
	public int handle(byte[] dat, int len) {
		// TODO Auto-generated method stub
		int shift, val;           
		int i,j;                   
	    shift = 0xffff;              
	    for (i = 0; i < len; i++)      
	    {
	        val = dat[i]<<8;           
	        for ( j = 0; j < 8; j++)   
	        {
	            if ((short)(shift ^ val)<0)
	                shift = (shift << 1)^ 0x8005;        
	            else                   
	                shift <<= 1;       
	            val <<= 1;             
	         }
	    }
	    return shift;
	}

	
}
