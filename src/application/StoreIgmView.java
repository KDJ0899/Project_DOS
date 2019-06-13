package application;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;

public class StoreIgmView {
	private AnchorPane pane;
	private ImageView Background;
	private Menubar menubar;
	private static ArrayList<Button> buttons;
	private Button btn;
	private int[] moneys = {100,400,700,1500,5000,13000};
	public StoreIgmView(AnchorPane pane) {
		this.pane = pane;
		Image backGroundImage = (new ImageParser("Store_IGM.png").getImage());
		Background = new ImageView(backGroundImage);
		pane.getChildren().add(Background);// 로비 배경

		buttons = new ArrayList<Button>();

		for (int a = 0; a < 6; a++) {
			if(a<3) {
				btn = new Button();
				btn.setPrefSize(472, 372);
				btn.setLayoutX(443 + a * 470);
				btn.setLayoutY(215);

				btn.setOpacity(0.5);
			}else {
				btn = new Button();
				btn.setPrefSize(472, 372);
				btn.setLayoutX(443 + (a-3) * 470);
				btn.setLayoutY(590);
				btn.setOpacity(0.5);
			}
			int money = moneys[a];
			btn.setOnMouseClicked(e-> butIGM(money));
			pane.getChildren().add(btn);
		}
		Menubar menubar = new Menubar(pane, 2);
	}
	public void butIGM(int money) {
		int isBuy = JOptionPane.showConfirmDialog(null, "정말 구매하시겠습니까?","확인",JOptionPane.OK_CANCEL_OPTION);
		if(isBuy==0) {
			LoginSession.money= String.valueOf(Integer.parseInt(LoginSession.money)+money);
			AnchorPane nextScreen;
			try {
				nextScreen = FXMLLoader.load(getClass().getResource("StoreIgmScreen.fxml"));
				new StoreIgmView(nextScreen);
				new StoreView(nextScreen, false);
				Scene sc = new Scene(nextScreen);
				Stage stage = (Stage) pane.getScene().getWindow();
				stage.setScene(sc);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
