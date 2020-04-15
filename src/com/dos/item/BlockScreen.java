package com.dos.item;

import javafx.application.Platform;

public class BlockScreen extends Item{
	
	private static BlockScreen blockScreen = new BlockScreen();
	
	public static BlockScreen getBlockScreen() {
		return blockScreen;
	}

	@Override
	public void useItem() {
		Platform.runLater(()->ItemFactory.pop.setOpacity(1));
		count();
		index=1;
		used=false;
		Platform.runLater(()->ItemFactory.pop.setOpacity(0));
		
	}

}
