package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author 팀원 전체 해당 클래스는 메인디능을 담당하는 JavaFX Application클래스를 상속받는 클래스입니다.
 */
public class DOSApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
			Font f = Font.loadFont(getClass().getResourceAsStream("/images/210 옴니고딕020.ttf"), 50);

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
