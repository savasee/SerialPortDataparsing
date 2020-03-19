package com.seeyou.comm.framework.commframe;

public class KAChecker extends Thread {    	    	
	private long katime = (15*1000);  //心跳检测时间为15秒	
	private boolean mIsRunning = false;	
	private long lastkatimestamp = 0;    	
	@Override
	public void run() {			
		while( mIsRunning ) {
			try {
				if ( getLastkatimestamp() == 0 ) {
					setLastkatimestamp( System.currentTimeMillis() );    					
					try {
						Thread.sleep(katime);					
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					continue;
				}
				
				if ( kaTimeout() ) {
					mIsRunning = false;
					break;
					
				} else{
					try {
						Thread.sleep(katime);					
					} catch (InterruptedException e) {
						e.printStackTrace();
					}    				
				}
				
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}		
		//stopCommProcess();
	}	    		
		
	/**
	 * @return 
	 * @exception心跳超时
	 */
	public boolean kaTimeout() {
		try {
			if ( mIsRunning == false ) {
				throw new Exception("user interrupt");
			}			
			if( ( System.currentTimeMillis() - getLastkatimestamp() )> ( katime*3L) ) {
				return true;				
			}
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return false;		
	}

	public void startWork() {
		try {
			mIsRunning = true;
			this.start();			
		} catch ( Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void stopWork() {		
		try {
			if ( mIsRunning ) {
				mIsRunning = false;
				this.interrupt();
			}			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	} 
	
	private long getLastkatimestamp() {
		return lastkatimestamp;
	}

    private void setLastkatimestamp(long lastkatimestamp) {
		this.lastkatimestamp = lastkatimestamp;
	}
}
