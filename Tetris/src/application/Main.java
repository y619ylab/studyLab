package application;

import java.io.File;
import java.nio.file.Paths;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	@Override
	public void start(Stage stage) {

		//音楽の再生
		AudioClip c = new AudioClip(
				new File("C:\\Users\\yuji2\\Desktop\\tetris\\MOTHER2 ______ OP.mp3").toURI().toString());
		//音楽の無限ループ指定
		c.setCycleCount(AudioClip.INDEFINITE);
		c.play();

		String colorString = "#ffffff";

		stage.setTitle("Tetris");
		stage.setWidth(900);
		stage.setHeight(750);

		//Opening画面の画像取り込み
		Image newGameImage = new Image(
				Paths.get("C:\\Users\\yuji2\\Desktop\\tetris\\Tetris002.jpg").toUri().toString());
		ImageView iv = new ImageView(newGameImage);
		iv.setScaleX(0.35);
		iv.setScaleY(0.35);
//		iv.setLayoutX(-520);
//		iv.setLayoutY(950);


		Group root = new Group();
		Scene scene = new Scene(root, Color.BLACK);
		Label labelMsg = new Label("Press Enter to Start");

		labelMsg.setTranslateX(300);
		labelMsg.setTranslateY(520);
		labelMsg.setFont(new Font(35));


		//Opening画面表示
		stage.setScene(scene);
		stage.show();


		//テトリスのロゴを画面下部から上部に移動するアニメーション作成
		TranslateTransition tt = new TranslateTransition(Duration.millis(3000), iv);

		tt.setFromX(-520);
		tt.setFromY(950);
		tt.setToX(-520);
		tt.setToY(-400);

		//メッセージ「PRESS ENTER TO START」の点滅するアニメーション作成
		FadeTransition ft = new FadeTransition(Duration.millis(2000), labelMsg);
		ft.setFromValue(1.0);
		ft.setToValue(0.1);
		ft.setCycleCount(Timeline.INDEFINITE);

		Timeline timeline = new Timeline(
				new KeyFrame(
						new Duration(0),
						new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								root.getChildren().add(iv);
								tt.play();
							}

						}),
				new KeyFrame(

						new Duration(3000),
						new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {

								labelMsg.setTextFill(Color.web(colorString));
								root.getChildren().add(labelMsg);
								ft.play();
							}

						}));
		//アニメーションの実行
		timeline.play();




		//ENTER押下でテトリス画面に移行
		scene.setOnKeyPressed(e -> {
			String keyValue = (e.getCode().toString());
			if ("ENTER".equals(keyValue)) {
				c.stop();
				TetrisMain tetrisMain = new TetrisMain();
				tetrisMain.start(stage);
			}
		});
	}

	public static void main(String[] args) {

		launch(args);

	}
}
