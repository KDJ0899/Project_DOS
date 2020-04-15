package com.dos.item;

import com.dos.game.Game;
import com.dos.login.DOSApplicationController;

public class Silence extends Item {
	private static Silence silence = new Silence();
	
	public static Silence getSilence() {
			return silence;
	}
	@Override
	public void useItem() {
		Game.getMusic().silence();
    	DOSApplicationController.introMusic.normalVolume();
    	count();
    	index=1;
		used=false;
		DOSApplicationController.introMusic.silence();
		Game.getMusic().normalVolume();
	}

}
