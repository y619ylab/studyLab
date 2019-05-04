package application;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import org.apache.commons.lang3.time.StopWatch;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import tetris.MyRunnable;
import tetris.RankingDAO;
import tetris.RankingDTO;
import tetris.TetrisBlock;
import tetris.TetrisStaticNumbers;

public class TetrisMain extends Application {

	Clip clip = WavPlayMusic
			.createClip(new File("C:\\Users\\yuji2\\Desktop\\tetris\\Tetris - bgm 2 (online-audio-converter.com).wav"));
	Clip clip2 = WavPlayMusic
			.createClip(new File("C:\\Users\\yuji2\\Desktop\\tetris\\_ - Beginning (online-audio-converter.com).wav"));
	Clip clip3 = WavPlayMusic
			.createClip(new File(
					"C:\\Users\\yuji2\\Desktop\\tetris\\_ _ _BGM_Aquarius 4V8_ (online-audio-converter.com).wav"));

	AudioClip soundEffect001 = new AudioClip(
			new File("C:\\Users\\yuji2\\Desktop\\tetris\\button70.mp3").toURI().toString());
	AudioClip soundEffect002 = new AudioClip(
			new File("C:\\Users\\yuji2\\Desktop\\tetris\\button42.mp3").toURI().toString());
	AudioClip soundEffect003 = new AudioClip(
			new File("C:\\Users\\yuji2\\Desktop\\tetris\\button55.mp3").toURI().toString());

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

	TetrisBlock tetrisBlock = new TetrisBlock();

	@Override
	public void start(Stage stage) {

		stage.setTitle("Tetris");

		clip.setLoopPoints(0, 2426500);
		clip.loop(Clip.LOOP_CONTINUOUSLY);

		ScheduledExecutorService service = null;
		service = Executors.newSingleThreadScheduledExecutor();

		Group root = new Group();

		Image nextBlockImage = new Image(
				Paths.get("C:\\Users\\yuji2\\Desktop\\tetris\\nextBlock.png").toUri().toString());
		ImageView iv = new ImageView(nextBlockImage);

		iv.setLayoutX(350);
		iv.setLayoutY(20);
		iv.setScaleX(0.5);
		iv.setScaleY(0.5);

		Image nextGameImage = new Image(Paths
				.get("C:\\Users\\yuji2\\Desktop\\tetris\\e7b81a7694a68f77fa1fafa0543389f8.gif").toUri().toString());
		ImageView iv2 = new ImageView(nextGameImage);
		iv2.setLayoutX(200);
		iv2.setLayoutY(120);
		iv2.setScaleX(1.8);
		iv2.setScaleY(1.5);

		Group root2 = new Group();
		root2.getChildren().add(iv2);

		gc.setLineWidth(5.0);
		gc.setStroke(Color.WHITE);
		gc.strokeRect(299, 98, 301, 600);

		//NEXTラベル
		Label nextLabelLiteral = new Label("NEXT");

		//Levelラベル
		Label levelLabel = new Label("0");
		Label levelLabelLiteral = new Label("LEVEL:");

		//scoreラベル
		Label scoreLabel = new Label("0");
		Label scoreLabelLiteral = new Label("SCORE:");

		//timeラベル
		Label timeLabel = new Label("00:00");
		Label timeLabelLiteral = new Label("TIME:");

		//Rankingラベル
		Label rankingLabelNo1 = new Label("1位:");
		Label rankingLabelNo2 = new Label("2位:");
		Label rankingLabelNo3 = new Label("3位:");
		Label rankingLabelLiteral = new Label("Ranking");

		//過去のRankingのスコアを取得
		List<RankingDTO> rankingList = new ArrayList<RankingDTO>();
		RankingDAO rankingDAO = new RankingDAO();
		rankingList = rankingDAO.findRanking();

		for (int i = 0; i < rankingList.size(); i++) {

			//			String name = rankingList.get(i).getName();
			Integer temp = rankingList.get(i).getScore();
			String score = temp.toString();

			//			System.out.println(name);
			//			System.out.println(score);

			if (i == 3)
				break;

			switch (i) {
			case 0:
				rankingLabelNo1.setText(String.format("1st:  %s", score));
				break;
			case 1:
				rankingLabelNo2.setText(String.format("2nd: %s", score));
				break;
			case 2:
				rankingLabelNo3.setText(String.format("3rd:  %s", score));
				break;
			}
		}

		StopWatch sw = new StopWatch();
		sw.start();

		Timeline timer = new Timeline(
				new KeyFrame(Duration.millis(1000),
						new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {

								long ms = sw.getTime();
								//分時刻取得
								long mm = ms / 1000 / 60;
								//秒時刻取得
								long ss = ms / 1000 % 60;
								//ミリ秒2桁取得
								//long ms2 = ms - mm * 1000 * 60 - ss * 1000;
								String time = String.format("%02d:%02d", mm, ss);
								timeLabel.setText(time);
							}
						}),
				new KeyFrame(Duration.millis(1000),
						new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {

								String totalScore = String.format("%02d", score);
								scoreLabel.setText(totalScore);

							}

						}),
				new KeyFrame(Duration.millis(1000),
						new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {

								String totalLevel = String.format("%02d", level);
								levelLabel.setText(totalLevel);
							}

						}));

		//nextラベルの設定
		nextLabelLiteral.setLayoutX(690);
		nextLabelLiteral.setLayoutY(100);
		nextLabelLiteral.setFont(new Font(30));

		//scoreラベルの設定
		scoreLabelLiteral.setLayoutX(30);
		scoreLabelLiteral.setLayoutY(50);
		scoreLabelLiteral.setFont(new Font(30));
		scoreLabel.setLayoutX(140);
		scoreLabel.setLayoutY(50);
		scoreLabel.setFont(new Font(30));

		//timeラベルの設定
		timeLabelLiteral.setLayoutX(30);
		timeLabelLiteral.setLayoutY(100);
		timeLabelLiteral.setFont(new Font(30));
		timeLabel.prefWidth(200);
		timeLabel.setLayoutX(140);
		timeLabel.setLayoutY(100);
		timeLabel.setFont(new Font(30));

		//scoreラベルの設定
		levelLabelLiteral.setLayoutX(30);
		levelLabelLiteral.setLayoutY(150);
		levelLabelLiteral.setFont(new Font(30));
		levelLabel.setLayoutX(140);
		levelLabel.setLayoutY(150);
		levelLabel.setFont(new Font(30));

		//rankingラベルの設定
		rankingLabelLiteral.setLayoutX(40);
		rankingLabelLiteral.setLayoutY(350);
		rankingLabelLiteral.setFont(new Font(30));

		rankingLabelNo1.setLayoutX(30);
		rankingLabelNo1.setLayoutY(400);
		rankingLabelNo1.setFont(new Font(30));
		rankingLabelNo2.setLayoutX(30);
		rankingLabelNo2.setLayoutY(450);
		rankingLabelNo2.setFont(new Font(30));
		rankingLabelNo3.setLayoutX(30);
		rankingLabelNo3.setLayoutY(500);
		rankingLabelNo3.setFont(new Font(30));

		//timerの設定
		timer.setCycleCount(Timeline.INDEFINITE);
		//アニメーションの実行
		timer.play();

		root.getChildren().addAll(scoreLabelLiteral, scoreLabel, timeLabelLiteral, timeLabel, levelLabel,
				levelLabelLiteral, nextLabelLiteral, rankingLabelLiteral, rankingLabelNo1, rankingLabelNo2,
				rankingLabelNo3, iv, canvas);

		Scene scene = new Scene(root, 900, 750, Color.BLACK);

		stage.setScene(scene);
		stage.show();

		//盤面の初期化
		for (int y = 0; y < TetrisStaticNumbers.ROWS; y++) {
			for (int x = 0; x < TetrisStaticNumbers.COLS; x++) {
				field[y][x] = 0;
			}
		}

		//初期ブロック
		currentBlock = tetrisBlock.newBlock();
		//nextブロック
		nextBlock = tetrisBlock.newBlock();
		//初期ブロックとnextブロックの描画
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				drawBlock(currentX + x, currentY + y, currentBlock[y][x]);
				nextBlock(13 + x, 3 + y, nextBlock[y][x]);
			}
		}

		MyRunnable task = new MyRunnable(array2 -> {

			//TODO: タスクを任意の場所で終了できない

			if (score >= 5000 && score < 20000) {
				clip.stop();
				clip.close();
				clip2.start();

				//				root.getChildren().addAll(scoreLabelLiteral, scoreLabel, timeLabelLiteral, timeLabel, levelLabel,
				//						levelLabelLiteral, nextLabelLiteral, iv2, canvas);

				//				Main main = new Main();
				//				main.start(stage);

				//				root.getChildren().addAll(iv2);

				//				stage.setScene(new Scene(root2, 900, 750));

				//
				//				stage.setScene(scene2);
				//				stage.show();
				//

			}
			if (score >= 20000) {
				clip2.stop();
				clip3.start();
				FloatControl ctrl = (FloatControl) clip3.getControl(FloatControl.Type.MASTER_GAIN);
				ctrl.setValue((float) Math.log10((float) 30 / 100) * 20);
			}

			//盤面消去
			gc.clearRect(300, 100, TetrisStaticNumbers.CANVASWIDTH, TetrisStaticNumbers.CANVASHEIGHT);

			//盤面の再描画
			for (int y = 0; y < TetrisStaticNumbers.ROWS; y++) {
				for (int x = 0; x < TetrisStaticNumbers.COLS; x++) {
					drawBlock(x, y, field[y][x]);
				}
			}

			//テトリスブロックの移動可能性を判定
			if (canMove(currentX, currentY, 0, 1, field, currentBlock)) {

				++currentY;
				//新規ブロック生成
				for (int y = 0; y < 4; y++) {
					for (int x = 0; x < 4; x++) {
						drawBlock(currentX + x, currentY + y, currentBlock[y][x]);
					}
				}

			} else {

				//ブロックを固定する
				fix(currentX, currentY, currentBlock);
				//一列に行が埋まっていれば行の消去
				clearRows();
				//初期位置にテトリスブロックを再度生成する
				currentX = 3;
				currentY = 0;
				currentBlock = nextBlock;
				nextBlock = tetrisBlock.newBlock();

				gc.clearRect(650, 80, 200, 200);

				for (int y = 0; y < 4; y++) {
					for (int x = 0; x < 4; x++) {
						//ゲームオーバー処理
						if (currentBlock[y][x] > 0 && field[y][x] > 0) {
							stopGame();
							throw new Error();
						}
						drawBlock(currentX + x, currentY + y, currentBlock[y][x]);
						nextBlock(13 + x, 3 + y, nextBlock[y][x]);
					}
				}

			}

		}, array);

		//taskのループ
		service.scheduleAtFixedRate(task, 0, 200, TimeUnit.MILLISECONDS);

		///ブロックのキー操作
		scene.setOnKeyPressed(e -> {
			String keyValue = (e.getCode().toString());

			//右矢印で右移動
			if ("RIGHT".equals(keyValue)) {
				if (canMove(currentX, currentY, 1, 0, field, currentBlock))
					++currentX;
			}
			//左矢印で左移動
			if ("LEFT".equals(keyValue)) {
				if (canMove(currentX, currentY, -1, 0, field, currentBlock))
					--currentX;

			}
			//下矢印で下移動
			if ("DOWN".equals(keyValue)) {
				if (canMove(currentX, currentY, 0, 1, field, currentBlock))
					++currentY;
			}
			//ENTERでブロック最下段移動
			if ("ENTER".equals(keyValue)) {
				for (int y = 0; y < TetrisStaticNumbers.ROWS; y++) {
					if (canMove(currentX, currentY, 0, 1, field, currentBlock))
						++currentY;
				}
			}
			//上矢印で左回転
			if ("UP".equals(keyValue)) {
				if (canMove(currentX, currentY, 0, 0, field, rotated())) {
					currentBlock = rotated();
					soundEffect003.play();
				}
			}

		});

	}

	//テトリスブロックの移動可能性を判定
	public boolean canMove(int currentX, int currentY, int moveX, int moveY, int field[][], int currentBlock[][]) {

		//		System.out.println("canMove: " + "currentX : " + currentX + "currentY : " + currentY);

		int nextY = currentY + moveY;
		int nextX = currentX + moveX;

		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (currentBlock[y][x] > 0) {
					if (nextY + y >= TetrisStaticNumbers.ROWS
							|| nextX + x >= TetrisStaticNumbers.COLS
							|| nextX + x < 0
							|| field[nextY + y][nextX + x] > 0) {
						return false;
					}
				}
			}
		}
		return true;
	}

	//ブロックが一列揃った行の削除
	public void clearRows() {

		boolean fill = true;

		for (int y = TetrisStaticNumbers.ROWS - 1; y >= 0; y--) {
			fill = true;
			for (int x = 0; x < TetrisStaticNumbers.COLS; x++) {
				if (field[y][x] == 0) {
					fill = false;
					break;
				}
			}
			if (fill) {
				for (int z = y - 1; z >= 0; z--) {
					for (int x = 0; x < TetrisStaticNumbers.COLS; x++) {
						field[z + 1][x] = field[z][x];
					}
				}
				y++;
				score += 1000;
				soundEffect002.play();
			}
		}

	}

	//ブロックを固定する
	public void fix(int currentX, int currentY, int currentBlock[][]) {

		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (currentBlock[y][x] > 0) {
					field[currentY + y][currentX + x] = currentBlock[y][x];
				}
			}
		}
		soundEffect001.play();
	}

	//ブロックの回転させる(左回転)
	public int[][] rotated() {

		int[][] rotatedBlock = new int[4][4];

		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				rotatedBlock[y][x] = currentBlock[x][-y + 3];
			}
		}

		return rotatedBlock;

	}

	//ブロックの描画
	public void drawBlock(int x, int y, int block) {

		if (block > 0) {

			gc.setStroke(Color.BLACK);
			gc.setLineWidth(1.0);
			switch (block) {
			case 1:
				gc.setFill(Color.CYAN);
				break;
			case 2:
				gc.setFill(Color.YELLOW);
				break;
			case 3:
				gc.setFill(Color.GREEN);
				break;
			case 4:
				gc.setFill(Color.RED);
				break;
			case 5:
				gc.setFill(Color.BLUE);
				break;
			case 6:
				gc.setFill(Color.ORANGE);
				break;
			case 7:
				gc.setFill(Color.MAGENTA);
				break;
			default:
			}

			gc.fillRect(x * blockWidth + 300, y * blockHeight + 102, blockWidth, blockHeight);
			gc.strokeRect(x * blockWidth + 300, y * blockHeight + 102, blockWidth, blockHeight);
		}
	}

	public void nextBlock(int x, int y, int block) {

		if (block > 0) {

			gc.setStroke(Color.BLACK);
			gc.setLineWidth(1.0);
			switch (block) {
			case 1:
				gc.setFill(Color.CYAN);
				break;
			case 2:
				gc.setFill(Color.YELLOW);
				break;
			case 3:
				gc.setFill(Color.GREEN);
				break;
			case 4:
				gc.setFill(Color.RED);
				break;
			case 5:
				gc.setFill(Color.BLUE);
				break;
			case 6:
				gc.setFill(Color.ORANGE);
				break;
			case 7:
				gc.setFill(Color.MAGENTA);
				break;
			default:
			}

			gc.fillRect(x * blockWidth + 285, y * blockHeight + 107, blockWidth, blockHeight);
			gc.strokeRect(x * blockWidth + 285, y * blockHeight + 107, blockWidth, blockHeight);
		}
	}

	public boolean stopGame() {

		for (int y = 0; y < TetrisStaticNumbers.ROWS; y++) {
			for (int x = 0; x < TetrisStaticNumbers.COLS; x++) {
				if (field[y][x] > 0) {
					gc.setFill(Color.DARKGRAY);
					gc.fillRect(x * blockWidth + 300, y * blockHeight + 100, blockWidth, blockHeight);
					gc.strokeRect(x * blockWidth + 300, y * blockHeight + 100, blockWidth, blockHeight);
				}
			}
		}

		RankingDAO rankingDAO = new RankingDAO();
		rankingDAO.insertRanking(String.valueOf(score));

		return true;

	}

	public static void main(String[] args) {

		launch(args);
	}
}
