package application;

import java.io.File;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import tetris.TetrisStaticNumbers;

public class TetrisNextStage extends Application {



	AudioClip soundEffect001 = new AudioClip(new File("C:\\Users\\yuji2\\Desktop\\button70.mp3").toURI().toString());
	AudioClip soundEffect002 = new AudioClip(new File("C:\\Users\\yuji2\\Desktop\\button42.mp3").toURI().toString());
	AudioClip soundEffect003 = new AudioClip(new File("C:\\Users\\yuji2\\Desktop\\button55.mp3").toURI().toString());

	Canvas canvas = new Canvas(900, 750);
	GraphicsContext gc = canvas.getGraphicsContext2D();

	final int blockWidth = TetrisStaticNumbers.CANVASWIDTH / TetrisStaticNumbers.COLS;
	final int blockHeight = TetrisStaticNumbers.CANVASHEIGHT / TetrisStaticNumbers.ROWS;

	int[] array = { 10, 20, 30, 40, 50 };
	static int currentX = 3;
	static int currentY = 0;
	static int field[][] = new int[TetrisStaticNumbers.ROWS][TetrisStaticNumbers.COLS];
	static int currentBlock[][] = new int[4][4];
	static int nextBlock[][] = new int[4][4];
	static int score = 0;
	static int level = 0;




	@Override
	public void start(Stage stage) {

		currentX = TetrisMain.currentX;
		currentY = TetrisMain.currentY;
		level = TetrisMain.level;
		score = TetrisMain.score;
		currentBlock = TetrisMain.currentBlock;
		nextBlock = TetrisMain.nextBlock;


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


		stage.setScene(new Scene(root));
		stage.show();








	}




}
