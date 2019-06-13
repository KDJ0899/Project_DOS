package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ThemeView {

	private AnchorPane pane;
	private ImageView Background;
	private Button btn1;
	private static ArrayList<Button> buttons1;
	private static ArrayList<String> fxmlList1;
	private static ArrayList<String> screenList1;
	private static ArrayList<Button> buttons;
	private boolean isFirst;

	public ThemeView(AnchorPane pane, boolean isFirst) {
		this.isFirst = isFirst;
		this.pane = pane;
		if(isFirst) {
			Image backGroundImage = (new ImageParser("Theme_bg.png").getImage());
			Background = new ImageView(backGroundImage);
			pane.getChildren().add(Background);// 로비 배경
			for (int a = 0; a < 6; a++) {
				Button btn = new Button();
				if(a<3) {
					btn.setPrefSize(472, 372);
					btn.setLayoutX(443 + a * 470);
					btn.setLayoutY(215);
					btn.setOpacity(1);
				}else {
					btn.setPrefSize(472, 372);
					btn.setLayoutX(443 + (a-3) * 470);
					btn.setLayoutY(590);
					btn.setOpacity(1);
				}
				pane.getChildren().add(btn);

			}
		}
		buttons1 = new ArrayList<Button>();

		for (int a = 0; a < 3; a++) {
			btn1 = new Button();
			btn1.setPrefSize(287, 87);

			btn1.setLayoutX(96);
			btn1.setLayoutY(398 + a * 160);
			btn1.setOpacity(0);
			buttons1.add(btn1);

			pane.getChildren().add(btn1);
			
		}
		
		buttons = new ArrayList<Button>();

		buttons1.get(0).setOnMouseClicked(e -> btnClick1(0));
		buttons1.get(1).setOnMouseClicked(e -> btnClick1(1));
		buttons1.get(2).setOnMouseClicked(e -> btnClick1(2));

		
		fxmlList1 = new ArrayList<String>();
		fxmlList1.add("ThemeIgmScreen.fxml");
		fxmlList1.add("ThemeGameScreen.fxml");
		fxmlList1.add("ThemeNoteScreen.fxml");

		screenList1 = new ArrayList<String>();
		screenList1.add("ThemeIgmView");
		screenList1.add("ThemeGameView");
		screenList1.add("ThemeNoteView");
	}

	private void btnClick1(int i) {

		Stage stage = (Stage) buttons1.get(i).getScene().getWindow();

		try {

			if (fxmlList1.get(i) == "ThemeIgmScreen.fxml") {
				AnchorPane nextScreen1 = FXMLLoader.load(getClass().getResource("ThemeIgmScreen.fxml"));
				new ThemeIgmView(nextScreen1);
				new Menubar(nextScreen1, 2);
				new ThemeView(nextScreen1, false);
				Scene sc = new Scene(nextScreen1);

				stage.setScene(sc);

				stage.show();
			} else if (fxmlList1.get(i) == "ThemeGameScreen.fxml") {
				AnchorPane nextScreen1 = FXMLLoader.load(getClass().getResource("ThemeGameScreen.fxml"));
				new ThemeGameView(nextScreen1);
				new Menubar(nextScreen1, 2);
				new ThemeView(nextScreen1, false);
				Scene sc = new Scene(nextScreen1);

				stage.setScene(sc);

				stage.show();
			} else {
				AnchorPane nextScreen1 = FXMLLoader.load(getClass().getResource("ThemeNoteScreen.fxml"));
				new ThemeNoteView(nextScreen1);
				new Menubar(nextScreen1, 2);
				new ThemeView(nextScreen1, false);
				Scene sc = new Scene(nextScreen1);

				stage.setScene(sc);

				stage.show();
			}

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

}
