package com.dos.item;

import com.dos.main.DOSApplication;

public class SpeedUp extends Item {
	
	private static SpeedUp speedUp = new SpeedUp();
	
	public static SpeedUp getSpeedUp() {
		return speedUp;
	}

	@Override
	public void useItem() {
		DOSApplication.NOTE_SPEED=16;
		count();
		index=1;
		used=false;
		DOSApplication.NOTE_SPEED=8;
	}

}
