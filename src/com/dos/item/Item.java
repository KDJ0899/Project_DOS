package com.dos.item;

public abstract class Item {
	int index = 1;
	boolean used = false;
	
	protected void initialization() {
		index = 1;
		used = false;
	}
	protected void count() {
		long end,start = System.currentTimeMillis();
		while(true) {
			end = System.currentTimeMillis();
			if(end-start>3000*index)
				break;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public abstract void useItem();
}
