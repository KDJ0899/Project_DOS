package application;

import java.awt.Image;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * @author 양태일
 *	게임화면에서의 노트를 음악에 맞게 떨어트리는 클래스
 */

public class Game extends Thread {
	
	Scene sc;
	AnchorPane pane;
	Music music;
	String title;
	Image image;
	VoiceKeyListener voice;
	Boolean isVoiceMode;
	public static int combo=0;
	public static int score=0;
	
	public static synchronized void addScore(int addScore) {
		score = score+addScore+combo*10;
	}
	
	public static synchronized void addCombo() {
		combo++;
	}
	
	public static synchronized void resetCombo() {
		combo=0;
	}
	
	static ArrayList<Note> noteList = new ArrayList<>();
	
	public Game(String title, AnchorPane pane, Scene sc) {
		this.pane = pane;
		this.title = title;
		this.sc = sc;
		DOSApplicationController.introMusic.close();
		
		Text text1 = new Text();
		text1.setX(285);
		text1.setY(29);
		text1.setLayoutX(150);
		text1.setLayoutY(300);
		pane.getChildren().add(text1);
		
		Text text2 = new Text();
		text2.setX(285);
		text2.setY(29);
		text2.setLayoutX(150);
		text2.setLayoutY(335);
		pane.getChildren().add(text2);
		
		Text text3 = new Text();
		text3.setX(285);
		text3.setY(29);
		text3.setLayoutX(150);
		text3.setLayoutY(370);
		pane.getChildren().add(text3);
		
		Text text4 = new Text();
		text4.setX(285);
		text4.setY(29);
		text4.setLayoutX(150);
		text4.setLayoutY(405);
		pane.getChildren().add(text4);
		
		sc.setOnKeyPressed(new KeyListener(pane, noteList));
		sc.setOnKeyReleased(new NoteEffectKeyListener(pane));
		voice=new VoiceKeyListener(pane);
	}
	
	public void playGame() {
		dropNotes();
	}
	
	public void startMusic() {
		music = new Music(title, false);
		music.changeFlag();
		music.start();
	}

	public void dropNotes(){
		boolean flag =false;
		int startTime = 1000-Main.REACH_TIME*1000;
		music = new Music(title, false);
		Thread t = new Thread(new MakeGameTask(music,pane,noteList));
		t.setDaemon(true);
		t.start();
		Thread voiceT = new Thread(voice);
		voiceT.start();
	}

	@Override
	public void run() {
		try {
			Task<Void> task = new Task<Void>() {
			    public Void call() throws Exception {
					Platform.runLater(()->playGame());
			        return null;
			    }
			};
			 
			Thread thread = new Thread(task);
			thread.setDaemon(true);
			thread.start();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AnchorPane getPane() {
		return pane;
	}

	public void setPane(AnchorPane pane) {
		this.pane = pane;
	}

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public ArrayList<Note> getNoteList() {
		return noteList;
	}

	public void setNoteList(ArrayList<Note> noteList) {
		this.noteList = noteList;
	}

}
