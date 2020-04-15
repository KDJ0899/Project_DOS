package com.dos.item;

import java.io.IOException;

import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class ItemFactory {

	static Popup pop;
	
	public ItemFactory(Stage stage) {
		if(pop == null) {
			pop = new Popup();
			try {
				AnchorPane second = FXMLLoader.load(Class.forName("com.dos.main.MultiThreadClient").getResource("/view/Block.fxml"));
				pop.getContent().add(second);
		    	pop.setOpacity(0);
		    	pop.show(stage,551,300);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			pop.setOpacity(0);
			pop.show(stage,551,300);
		}
		
		Silence.getSilence().initialization();
		BlockScreen.getBlockScreen().initialization();
		SpeedUp.getSpeedUp().initialization();
	}
	
	public static void useItem(int i) {
		Task<Void> task = new Task<Void>() {
			public Void call() throws Exception {
				Item item = null;
				if(i==0) 
					item = Silence.getSilence();
				else if(i==1) 
					item = BlockScreen.getBlockScreen();
				else if(i==2) 
					item = SpeedUp.getSpeedUp();
				
				if(item.used)
					item.index++;
				else {
					item.used = true;
					item.useItem();
				}
				return null;
			}
		};
		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();
	}
}
