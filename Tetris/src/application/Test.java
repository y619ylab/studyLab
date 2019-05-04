package application;

import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Test extends Application{


	@Override
	public void start(Stage stage) throws Exception {

		stage.setTitle("Tetris");
		stage.setWidth(900);
		stage.setHeight(750);


		//Opening画面の画像取り込み
		Image nextGameImage = new Image(Paths.get("C:\\Users\\yuji2\\Desktop\\e7b81a7694a68f77fa1fafa0543389f8.gif").toUri().toString());
		ImageView iv = new ImageView(nextGameImage);
		iv.setLayoutX(200);
		iv.setLayoutY(120);
		iv.setScaleX(1.8);
		iv.setScaleY(1.5);

		Group root = new Group();
		root.getChildren().add(iv);


//		RotateTransition rt = new RotateTransition(Duration.millis(1000), iv);
//
//		rt.setByAngle(360);
//		rt.setCycleCount(Animation.INDEFINITE);
//
//
//		Timeline timeline = new Timeline(
//				new KeyFrame(Duration.millis(1000),
//				new EventHandler<ActionEvent>() {
//
//					@Override
//					public void handle(ActionEvent event) {
//
//						root.getChildren().add(iv);
//						rt.play();
//
//
//					}
//
//				}
//
//
//
//						)
//
//
//				);
//
//
//		timeline.play();











		//Opening画面表示
		stage.setScene(new Scene(root));
		stage.show();





/*
		Label label = new Label("00:00");


		StopWatch sw = new StopWatch();
		sw.start();


		Timeline timer = new Timeline(
				new KeyFrame(Duration.millis(100),
				new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {


						long ms = sw.getTime();
						//分時刻取得
						long mm = ms / 1000 / 60;
						//秒時刻取得
						long ss = ms / 1000 % 60;
						//ミリ秒2桁取得
						long ms2 = ms - mm * 1000 * 60 - ss * 1000;

						String time = String.format("%02d:%02d", mm, ss);

						label.setText(time);

					}

				}
				));



		label.prefWidth(200);
		label.setLayoutX(100);
		label.setLayoutY(100);

		label.setFont(new Font(30));
		timer.setCycleCount(Timeline.INDEFINITE);
		timer.play();
*/



/*

		String colorString = "#ffffff";

		Label labelMsg = new Label("Press Enter to Start");
//		labelMsg.setAlignment(Pos.CENTER);
		labelMsg.setTranslateX(300);
		labelMsg.setTranslateY(520);
		labelMsg.setFont(new Font(35));

*/


/*
		//テトリスのロゴを画面下部から上部に移動するアニメーションを作成する
		TranslateTransition tt = new TranslateTransition(Duration.millis(3000), iv);
		tt.setFromX(-520);
		tt.setFromY(950);
		tt.setToX(-520);
		tt.setToY(-400);

		//メッセージ「PRESS ENTER TO START」の点滅するアニメーションの作成
		FadeTransition ft = new FadeTransition(Duration.millis(2000), labelMsg);
		ft.setFromValue(0.1);
		ft.setToValue(1.0);


		ft.setCycleCount(Timeline.INDEFINITE);




		Timeline timeline = new Timeline(
				new KeyFrame(
						new Duration(3000),
						new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								root.getChildren().add(iv);
								tt.play();
							}

						}
						),
				new KeyFrame(

						new Duration(7000),
						new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {

								labelMsg.setTextFill(Color.web(colorString));
								root.getChildren().add(labelMsg);
								ft.play();
							}


						}

						)


				);


		timeline.play();


*/
	}






	public static void main(String[] args) {

		launch(args);

	}

}
