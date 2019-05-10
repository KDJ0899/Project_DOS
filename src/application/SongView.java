package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SongView {
	private AnchorPane pane;
	private ImageView Background;
	private ImageView right;
	private ImageView left;
	private Circle[] circleList;
	private ArrayList<Image> albumImgList;
	private int selectCircleIndex = 1;
	private ImageView selectCircle;
	private boolean twinkleStop = false;

	public SongView(AnchorPane pane) {
		this.pane = pane;

		Image backGroundImage = (new ImageParser("Select_bg.png").getImage());
		Background = new ImageView(backGroundImage);
		pane.getChildren().add(Background);// 로비 배경

		
		Text text = new Text("BLANK");

		text.setFill(Color.WHITE);
		text.setFont(Font.font("210 OmniGothic 020", 70));
		

		pane.getChildren().add(text);
		text.setX(830);
		text.setY(940);// 위치 바꾸기

		albumImgList = new ArrayList<Image>();
		albumImgList.add(new ImageParser("happyrock.jpg").getImage());
		albumImgList.add(new ImageParser("BLANK.jpg").getImage());
		albumImgList.add(new ImageParser("Invincible.jpg").getImage());
		albumImgList.add(new ImageParser("NEKOZILLA.png").getImage());
		albumImgList.add(new ImageParser("SKY HIGH.jpg").getImage());

		circleList = new Circle[5];
		String st[] = new String[5];
		st[0] = "HAPPY ROCK";
		st[1] = "BLANK";
		st[2] = "INVINCIBLE";
		st[3] = "NEKOZILLA";
		st[4] = "SKY HIGH";

		HashMap s = new HashMap<Integer, String>();

		for (int i = 0; i < circleList.length; i++) {
			circleList[i] = new Circle();
			s.put(i, st[i]);
			circleList[i].setRadius(250);
			circleList[i].setFill(new ImagePattern(albumImgList.get(i)));
			pane.getChildren().add(circleList[i]);
		}

		circleList[0].setCenterX(1250);
		circleList[0].setCenterY(450);
		circleList[0].setOnMouseClicked(e -> startBtnClick0());

		circleList[1].setCenterX(950);
		circleList[1].setCenterY(600);
		circleList[1].setOnMouseClicked(e -> startBtnClick1());

		circleList[2].setCenterX(650);
		circleList[2].setCenterY(450);
		circleList[2].setOnMouseClicked(e -> startBtnClick2());

		circleList[3].setCenterX(950);
		circleList[3].setCenterY(-800);
		circleList[3].setOnMouseClicked(e -> startBtnClick3());

		circleList[4].setCenterX(950);
		circleList[4].setCenterY(-800);
		circleList[4].setOnMouseClicked(e -> startBtnClick4());

		circleList[1].toFront();

		Image leftImage = (new ImageParser("left.png").getImage());
		left = new ImageView(leftImage);
		left.setLayoutX(100);
		left.setLayoutY(510);
		left.setOnMouseClicked(e -> {
			int k = (selectCircleIndex - 1 + circleList.length) % circleList.length;
			String z = (String) s.get(k);
			text.setText(z);
			rightToCenter(circleList[k]);

			centerToLeft(circleList[selectCircleIndex]);
			leftToUp(circleList[(selectCircleIndex + 1 + circleList.length) % circleList.length]);

			selectCircleIndex = (selectCircleIndex - 1 + circleList.length) % circleList.length;

			newRightToDown(circleList[(selectCircleIndex - 1 + circleList.length) % circleList.length]);
			newRightToDown2(circleList[(selectCircleIndex - 1 + circleList.length) % circleList.length]);

			circleList[selectCircleIndex].toFront();

		});
		pane.getChildren().add(left);

		Image rightImage = (new ImageParser("right.png").getImage());
		right = new ImageView(rightImage);
		right.setLayoutX(1680);
		right.setLayoutY(510);
		right.setOnMouseClicked(e -> {

			int k = (selectCircleIndex + 1) % circleList.length;
			String z = (String) s.get(k);
			text.setText(z);
			rightToCenter(circleList[k]);

			rightToUp(circleList[(selectCircleIndex - 1 + circleList.length) % circleList.length]);// 오른 ->왼

			centerToRight(circleList[selectCircleIndex]);// 가운데 ->오른
			leftToCenter(circleList[(selectCircleIndex + 1) % circleList.length]);// 왼 ->가운데

			selectCircleIndex = (selectCircleIndex + 1) % circleList.length;

			newLeftToDown(circleList[(selectCircleIndex + 1) % circleList.length]);

			newLeftToDown2(circleList[(selectCircleIndex + 1) % circleList.length]);

			circleList[selectCircleIndex].toFront();
		});
		pane.getChildren().add(right);

		Image selectCircleImage = (new ImageParser("Select_circle.png").getImage());
		selectCircle = new ImageView(selectCircleImage);
		selectCircle.setLayoutX(692);
		selectCircle.setLayoutY(343);
		pane.getChildren().add(selectCircle);
		selectCircle.toFront();
	}

	public void rightToUp(Circle cir) {// 오 위

		cir.setCenterX(1250);
		cir.setCenterY(450);
		TranslateTransition t1 = new TranslateTransition();
		t1.setDuration(Duration.seconds(1));
		t1.setFromX(0);
		t1.setFromY(0);
		t1.setToX(300);
		t1.setToY(-800);
		t1.setNode(cir);
		t1.play();
		selectCircle.toFront();

	}

	public void centerToRight(Circle cir) {// 가운 오
		cir.setCenterX(950);
		cir.setCenterY(600);
		TranslateTransition t1 = new TranslateTransition();
		t1.setDuration(Duration.seconds(1));
		t1.setFromX(0);
		t1.setFromY(0);
		t1.setToX(300);
		t1.setToY(-150);
		t1.setNode(cir);
		t1.play();
		selectCircle.toFront();

	}

	public void leftToCenter(Circle cir) {// 왼 가운
		cir.setCenterX(650);
		cir.setCenterY(450);
		TranslateTransition t1 = new TranslateTransition();
		t1.setDuration(Duration.seconds(1));
		t1.setFromX(0);
		t1.setFromY(0);
		t1.setToX(300);
		t1.setToY(150);
		t1.setNode(cir);
		t1.play();
		selectCircle.toFront();
	}

	public void newLeftToDown(Circle cir) {// 새로운 왼 아래

		cir.setCenterX(650);
		cir.setCenterY(450);
		TranslateTransition t1 = new TranslateTransition();
		t1.setDuration(Duration.seconds(1));
		t1.setFromX(-300);
		t1.setFromY(-800);
		t1.setToX(0);
		t1.setToY(0);
		t1.setNode(cir);
		t1.play();
		selectCircle.toFront();
	}

	public void newLeftToDown2(Circle cir) {// 새로운 왼 아래2

		cir.setCenterX(650);
		cir.setCenterY(450);
		TranslateTransition t1 = new TranslateTransition();
		t1.setDuration(Duration.seconds(1));
		t1.setFromX(-300);
		t1.setFromY(-800);
		t1.setToX(0);
		t1.setToY(0);
		t1.setNode(cir);
		t1.play();
		selectCircle.toFront();
	}

/////////////////////////////////////////////왼쪽 버튼////////////////////////////////////////////////
	public void rightToCenter(Circle cir) { // 오 가운
		cir.setCenterX(1250);
		cir.setCenterY(450);
		TranslateTransition t1 = new TranslateTransition();
		t1.setDuration(Duration.seconds(1));
		t1.setFromX(0);
		t1.setFromY(0);
		t1.setToX(-300);
		t1.setToY(150);
		t1.setNode(cir);
		t1.play();
		selectCircle.toFront();

	}

	public void centerToLeft(Circle cir) {// 가운 왼
		cir.setCenterX(950);
		cir.setCenterY(600);
		TranslateTransition t1 = new TranslateTransition();
		t1.setDuration(Duration.seconds(1));
		t1.setFromX(0);
		t1.setFromY(0);
		t1.setToX(-300);
		t1.setToY(-150);
		t1.setNode(cir);
		t1.play();
		selectCircle.toFront();
	}

	public void leftToUp(Circle cir) {// 왼 위
		cir.setCenterX(650);
		cir.setCenterY(450);
		TranslateTransition t1 = new TranslateTransition();
		t1.setDuration(Duration.seconds(1));
		t1.setFromX(0);
		t1.setFromY(0);
		t1.setToX(-300);
		t1.setToY(-800);
		t1.setNode(cir);
		t1.play();
		selectCircle.toFront();
	}

	public void newRightToDown(Circle cir) {// 새로운 위 아래
		cir.setCenterX(1250);
		cir.setCenterY(450);
		TranslateTransition t1 = new TranslateTransition();
		t1.setDuration(Duration.seconds(1));
		t1.setFromX(300);
		t1.setFromY(-800);
		t1.setToX(0);
		t1.setToY(0);
		t1.setNode(cir);
		t1.play();
		selectCircle.toFront();
	}

	public void newRightToDown2(Circle cir) {// 새로운 위 아래
		cir.setCenterX(1250);
		cir.setCenterY(450);
		TranslateTransition t1 = new TranslateTransition();
		t1.setDuration(Duration.seconds(1));
		t1.setFromX(300);
		t1.setFromY(-800);
		t1.setToX(0);
		t1.setToY(0);
		t1.setNode(cir);
		t1.play();
		selectCircle.toFront();
	}

	private void startBtnClick0() {
		Stage stage = (Stage) circleList[0].getScene().getWindow();

		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));

			// 씬에 레이아웃 추가
			Scene sc = new Scene(pane);
			stage.setScene(sc);
			Game game = new Game("bensound-happyrock.mp3", pane, sc);
			stage.show();
			Task<Void> task = new Task<Void>() {
				public Void call() throws Exception {
					game.run();
					return null;
				}
			};
			Thread t = new Thread(task);
			t.setDaemon(true);
			t.run();
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	private void startBtnClick1() {
		Stage stage = (Stage) circleList[1].getScene().getWindow();

		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));

			// 씬에 레이아웃 추가
			Scene sc = new Scene(pane);
			stage.setScene(sc);
			Game game = new Game("Disfigure - Blank.mp3", pane, sc);
			stage.show();
			Task<Void> task = new Task<Void>() {
				public Void call() throws Exception {
					game.run();
					return null;
				}
			};
			Thread t = new Thread(task);
			t.setDaemon(true);
			t.run();
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	private void startBtnClick2() {
		Stage stage = (Stage) circleList[2].getScene().getWindow();

		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));

			// 씬에 레이아웃 추가
			Scene sc = new Scene(pane);
			stage.setScene(sc);
			Game game = new Game("Invincible - Deaf Kev.mp3", pane, sc);
			stage.show();
			Task<Void> task = new Task<Void>() {
				public Void call() throws Exception {
					game.run();
					return null;
				}
			};
			Thread t = new Thread(task);
			t.setDaemon(true);
			t.run();
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	private void startBtnClick3() {
		Stage stage = (Stage) circleList[3].getScene().getWindow();

		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));

			// 씬에 레이아웃 추가
			Scene sc = new Scene(pane);
			stage.setScene(sc);
			Game game = new Game("Different Heaven - Nekozilla.mp3", pane, sc);
			stage.show();
			Task<Void> task = new Task<Void>() {
				public Void call() throws Exception {
					game.run();
					return null;
				}
			};
			Thread t = new Thread(task);
			t.setDaemon(true);
			t.run();
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	private void startBtnClick4() {
		Stage stage = (Stage) circleList[4].getScene().getWindow();

		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));

			// 씬에 레이아웃 추가
			Scene sc = new Scene(pane);
			stage.setScene(sc);
			Game game = new Game("Elektronomia - Sky High.mp3", pane, sc);
			stage.show();
			Task<Void> task = new Task<Void>() {
				public Void call() throws Exception {
					game.run();
					return null;
				}
			};
			Thread t = new Thread(task);
			t.setDaemon(true);
			t.run();
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

}