package pj2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;

import java.io.*;
import java.util.Optional;

import javafx.util.Duration;


public class pj2 extends Application {

    static double height = Screen.getPrimary().getVisualBounds().getHeight();
    static double width = Screen.getPrimary().getVisualBounds().getWidth();
    static char tetrisAll[][][] = new char[7][5][9];
    static char[][] arr = new char[26][23];
    static char[][] tetris1 = new char[5][9];
    static char[][] tetris2 = new char[5][9];
    static char[][] tetris3 = new char[5][9];
    static boolean[][] f1 = new boolean[26][23];
    static int sum = 0, b = 0, y = 0, lie, hang, x2, dur, r, mode;
    static double a = 0, x = 0, time = 0;
    static boolean f, f2, f4 = false, f3 = false, f5 = true;
    static Scene scene2;
    static Canvas canvas;
    static GraphicsContext gc;
    static Text text2 = new Text();
    static Text text4 = new Text();
    static String name = "";
    static int vis[][] = new int[5][9];
    static int mx[] = {-1, 0, 1, 0};
    static int my[] = {0, 2, 0, -2};
    static int count;
    static int rank[][] = new int[3][10];
    static String ranks[][] = new String[3][10];
    static Text texts[][] = new Text[3][10];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tetris");
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);

        Media media1 = new Media(getClass().getResource("music/bgm1.mp3").toString());
        Media media12 = new Media(getClass().getResource("music/bgm2.mp3").toString());
        Media media13 = new Media(getClass().getResource("music/bgm3.mp3").toString());
        MediaPlayer mediaPlayer1 = new MediaPlayer(media1);
        MediaPlayer mediaPlayer12 = new MediaPlayer(media12);
        MediaPlayer mediaPlayer13 = new MediaPlayer(media13);
        mediaPlayer1.setAutoPlay(true);
        mediaPlayer1.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer1.setVolume(0.7);
        mediaPlayer12.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer12.setVolume(0.7);
        mediaPlayer13.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer13.setVolume(0.7);
        Media media2 = new Media(getClass().getResource("music/click.mp3").toString());
        Media media3 = new Media(getClass().getResource("music/substract3.mp3").toString());
        Media media4 = new Media(getClass().getResource("music/promptDrop2.mp3").toString());
        Media media5 = new Media(getClass().getResource("music/substract1.mp3").toString());
        Media media6 = new Media(getClass().getResource("music/substract2.mp3").toString());
        Media media7 = new Media(getClass().getResource("music/substract4.mp3").toString());
        Media media8 = new Media(getClass().getResource("music/rotate3.mp3").toString());
        Media media9 = new Media(getClass().getResource("music/lose.mp3").toString());

        Pane pane = new Pane();
        Scene scene = new Scene(pane, width, height);
        Text text = new Text("俄罗斯方块");
        Blend blend = new Blend();
        blend.setMode(BlendMode.MULTIPLY);

        DropShadow ds = new DropShadow();
        ds.setColor(Color.rgb(254, 235, 66, 0.3));
        ds.setOffsetX(5);
        ds.setOffsetY(5);
        ds.setRadius(5);
        ds.setSpread(0.2);

        blend.setBottomInput(ds);

        DropShadow ds1 = new DropShadow();
        ds1.setColor(Color.web("#f13a00"));
        ds1.setRadius(20);
        ds1.setSpread(0.2);

        Blend blend2 = new Blend();
        blend2.setMode(BlendMode.MULTIPLY);

        InnerShadow is = new InnerShadow();
        is.setColor(Color.web("#feeb42"));
        is.setRadius(9);
        is.setChoke(0.8);
        blend2.setBottomInput(is);

        InnerShadow is1 = new InnerShadow();
        is1.setColor(Color.web("#f13a00"));
        is1.setRadius(5);
        is1.setChoke(0.4);
        blend2.setTopInput(is1);

        Blend blend1 = new Blend();
        blend1.setMode(BlendMode.MULTIPLY);
        blend1.setBottomInput(ds1);
        blend1.setTopInput(blend2);

        blend.setTopInput(blend1);

        text.setEffect(blend);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Verdana", 60));
        text.setLayoutX(width / 2 - 150);
        text.setLayoutY(height / 2 - 200);
        Button btn11 = new Button("开始游戏");
        btn11.setStyle("-fx-background-color: black");
        btn11.setTextFill(Color.WHITE);
        btn11.setFont(Font.font("Verdana", 30));
        Timeline timeline = new Timeline();
        Pane pane1 = new Pane();
        Pane pane2 = new Pane();
        Pane pane3 = new Pane();
        Pane pane4 = new Pane();
        Pane pane5 = new Pane();
        Pane pane6 = new Pane();
        Pane pane7 = new Pane();
        Pane pane8 = new Pane();
        pane5.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        pane4.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        pane6.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        pane7.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        pane8.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        Scene scene1 = new Scene(pane2, width, height);
        Scene scene3 = new Scene(pane3, width, height);
        Scene scene4 = new Scene(pane4, width, height);
        Scene scene5 = new Scene(pane5, width, height);
        Scene scene6 = new Scene(pane6, width, height);
        Scene scene7 = new Scene(pane7, width, height);
        Scene scene8 = new Scene(pane8, width, height);
        Text text1 = new Text("分数");
        text1.setLayoutX(width / 2 + 173);
        text1.setLayoutY(height - 210);
        text1.setFont(Font.font("Verdana", 20));
        text1.setFill(Color.WHITE);

        Canvas canvas1 = new Canvas(width, height);
        GraphicsContext gc1 = canvas1.getGraphicsContext2D();
        gc1.setFill(Color.WHITE);
        gc1.fillRect(width / 2 - 200, height - 600, 350, 50);
        Canvas canvas2 = new Canvas(width, height);
        GraphicsContext gc2 = canvas2.getGraphicsContext2D();

        text2.setLayoutX(width / 2 + 200);
        text2.setLayoutY(height - 197 + 10);
        text2.setFont(Font.font("Verdana", 20));
        text2.setFill(Color.WHITE);

        Text text3 = new Text("时间");
        text3.setLayoutX(width / 2 + 173);
        text3.setLayoutY(height - 830);
        text3.setFont(Font.font("Verdana", 20));
        text3.setFill(Color.WHITE);

        Text text5 = new Text("下个方块：");
        text5.setLayoutX(width / 2 - 320);
        text5.setLayoutY(height - 830);
        text5.setFont(Font.font("Verdana", 20));
        text5.setFill(Color.WHITE);
        Text text6 = new Text("请输入您的昵称：");
        Text text7 = new Text();
        Text text8 = new Text("排行榜");
        Text text9 = new Text("普通模式");
        Text text10 = new Text("随机方块");
        Text text11 = new Text("穿越边界");
        Text text12 = new Text("Game Over");
        Text text13 = new Text();
        Text text14 = new Text("游戏帮助");
        text14.setEffect(blend);
        text14.setFill(Color.WHITE);
        text14.setFont(Font.font("Verdana", 60));
        text14.setLayoutX(width / 2 - 150);
        text14.setLayoutY(height / 2 - 200);
        Text text15 = new Text("W:旋转");
        text15.setFill(Color.WHITE);
        text15.setFont(Font.font("Verdana", 40));
        text15.setLayoutX(width / 2 - 250);
        text15.setLayoutY(height / 2 - 100);
        Text text16 = new Text("S:快速下落");
        text16.setFill(Color.WHITE);
        text16.setFont(Font.font("Verdana", 40));
        text16.setLayoutX(width / 2 + 150);
        text16.setLayoutY(height / 2 - 100);
        Text text17 = new Text("A:左移");
        text17.setFill(Color.WHITE);
        text17.setFont(Font.font("Verdana", 40));
        text17.setLayoutX(width / 2 - 250);
        text17.setLayoutY(height / 2);
        Text text18 = new Text("D:右移");
        text18.setFill(Color.WHITE);
        text18.setFont(Font.font("Verdana", 40));
        text18.setLayoutX(width / 2 + 150);
        text18.setLayoutY(height / 2);
        Text text19 = new Text("X:下落至底");
        text19.setFill(Color.WHITE);
        text19.setFont(Font.font("Verdana", 40));
        text19.setLayoutX(width / 2 - 250);
        text19.setLayoutY(height / 2 + 100);
        Text text20 = new Text("ESC:返回/暂停");
        text20.setFill(Color.WHITE);
        text20.setFont(Font.font("Verdana", 40));
        text20.setLayoutX(width / 2 + 150);
        text20.setLayoutY(height / 2 + 100);
        Text text21 = new Text("游戏设置");
        text21.setEffect(blend);
        text21.setFill(Color.WHITE);
        text21.setFont(Font.font("Verdana", 60));
        text21.setLayoutX(width / 2 - 150);
        text21.setLayoutY(height / 2 - 200);
        Text text22 = new Text("BGM选择：");
        text22.setFill(Color.WHITE);
        text22.setFont(Font.font("Verdana", 40));
        text22.setLayoutX(width / 2 - 350);
        text22.setLayoutY(height / 2 - 100);
        Text text23 = new Text("BGM音量：");
        text23.setFill(Color.WHITE);
        text23.setFont(Font.font("Verdana", 40));
        text23.setLayoutX(width / 2 - 350);
        text23.setLayoutY(height / 2);
        Text text24 = new Text("音效：");
        text24.setFill(Color.WHITE);
        text24.setFont(Font.font("Verdana", 40));
        text24.setLayoutX(width / 2 - 350);
        text24.setLayoutY(height / 2 + 100);

        Button btn41 = new Button("再来一局");
        Button btn42 = new Button("退出游戏");
        btn41.setStyle("-fx-background-color: black");
        btn41.setTextFill(Color.WHITE);
        btn41.setFont(Font.font("Verdana", 30));
        btn42.setStyle("-fx-background-color: black");
        btn42.setTextFill(Color.WHITE);
        btn42.setFont(Font.font("Verdana", 30));
        btn41.setLayoutX(width / 2 - 400);
        btn42.setLayoutX(width / 2 + 200);
        btn41.setLayoutY(height - 300);
        btn42.setLayoutY(height - 300);
        Button btn51 = new Button("斗地主");
        btn51.setStyle("-fx-background-color: black");
        btn51.setTextFill(Color.WHITE);
        btn51.setFont(Font.font("Verdana", 30));
        btn51.setLayoutX(width / 2 - 100);
        btn51.setLayoutY(height / 2 - 140);
        gc2.setFill(Color.RED);
        gc2.fillOval(width / 2 - 130, height / 2 - 125, 30, 30);
        Button btn52 = new Button("Sky");
        btn52.setStyle("-fx-background-color: black");
        btn52.setTextFill(Color.WHITE);
        btn52.setFont(Font.font("Verdana", 30));
        btn52.setLayoutX(width / 2 + 100);
        btn52.setLayoutY(height / 2 - 140);
        gc2.setStroke(Color.RED);
        gc2.strokeOval(width / 2 + 70, height / 2 - 125, 30, 30);
        Button btn53 = new Button("Epic Sax Guy");
        btn53.setStyle("-fx-background-color: black");
        btn53.setTextFill(Color.WHITE);
        btn53.setFont(Font.font("Verdana", 30));
        btn53.setLayoutX(width / 2 + 300);
        btn53.setLayoutY(height / 2 - 140);
        gc2.setStroke(Color.RED);
        gc2.strokeOval(width / 2 + 270, height / 2 - 125, 30, 30);
        Button btn54 = new Button("静音");
        btn54.setStyle("-fx-background-color: black");
        btn54.setTextFill(Color.WHITE);
        btn54.setFont(Font.font("Verdana", 30));
        btn54.setLayoutX(width / 2 - 100);
        btn54.setLayoutY(height / 2 - 35);
        gc2.setStroke(Color.RED);
        gc2.strokeOval(width / 2 - 130, height / 2 - 20, 30, 30);
        Button btn55 = new Button("低");
        btn55.setStyle("-fx-background-color: black");
        btn55.setTextFill(Color.WHITE);
        btn55.setFont(Font.font("Verdana", 30));
        btn55.setLayoutX(width / 2 + 50);
        btn55.setLayoutY(height / 2 - 35);
        gc2.setStroke(Color.RED);
        gc2.strokeOval(width / 2 + 20, height / 2 - 20, 30, 30);
        Button btn56 = new Button("中");
        btn56.setStyle("-fx-background-color: black");
        btn56.setTextFill(Color.WHITE);
        btn56.setFont(Font.font("Verdana", 30));
        btn56.setLayoutX(width / 2 + 200);
        btn56.setLayoutY(height / 2 - 35);
        gc2.setFill(Color.RED);
        gc2.fillOval(width / 2 + 170, height / 2 - 20, 30, 30);
        Button btn57 = new Button("高");
        btn57.setStyle("-fx-background-color: black");
        btn57.setTextFill(Color.WHITE);
        btn57.setFont(Font.font("Verdana", 30));
        btn57.setLayoutX(width / 2 + 350);
        btn57.setLayoutY(height / 2 - 35);
        gc2.setStroke(Color.RED);
        gc2.strokeOval(width / 2 + 320, height / 2 - 20, 30, 30);
        Button btn58 = new Button("开");
        btn58.setStyle("-fx-background-color: black");
        btn58.setTextFill(Color.WHITE);
        btn58.setFont(Font.font("Verdana", 30));
        btn58.setLayoutX(width / 2 - 100);
        btn58.setLayoutY(height / 2 + 65);
        gc2.setFill(Color.RED);
        gc2.fillOval(width / 2 - 130, height / 2 + 85, 30, 30);
        Button btn59 = new Button("关");
        btn59.setStyle("-fx-background-color: black");
        btn59.setTextFill(Color.WHITE);
        btn59.setFont(Font.font("Verdana", 30));
        btn59.setLayoutX(width / 2 + 50);
        btn59.setLayoutY(height / 2 + 65);
        gc2.setStroke(Color.RED);
        gc2.strokeOval(width / 2 + 20, height / 2 + 85, 30, 30);
        btn58.setOnAction(event -> {
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            if (f5) mediaPlayer2.play();
            gc2.setFill(Color.RED);
            gc2.fillOval(width / 2 - 130, height / 2 + 85, 30, 30);
            gc2.setFill(Color.BLACK);
            gc2.fillOval(width / 2 + 20, height / 2 + 85, 30, 30);
            gc2.setStroke(Color.RED);
            gc2.strokeOval(width / 2 + 20, height / 2 + 85, 30, 30);
            f5 = true;
        });
        btn59.setOnAction(event -> {
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            if (f5) mediaPlayer2.play();
            gc2.setFill(Color.BLACK);
            gc2.fillOval(width / 2 - 130, height / 2 + 85, 30, 30);
            gc2.setStroke(Color.RED);
            gc2.strokeOval(width / 2 - 130, height / 2 + 85, 30, 30);
            gc2.setFill(Color.RED);
            gc2.fillOval(width / 2 + 20, height / 2 + 85, 30, 30);
            f5 = false;
        });
        btn54.setOnAction(event -> {
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            if (f5) mediaPlayer2.play();
            gc2.setFill(Color.RED);
            gc2.fillOval(width / 2 - 130, height / 2 - 20, 30, 30);
            gc2.setFill(Color.BLACK);
            gc2.fillOval(width / 2 + 20, height / 2 - 20, 30, 30);
            gc2.setStroke(Color.RED);
            gc2.strokeOval(width / 2 + 20, height / 2 - 20, 30, 30);
            gc2.setFill(Color.BLACK);
            gc2.fillOval(width / 2 + 170, height / 2 - 20, 30, 30);
            gc2.setStroke(Color.RED);
            gc2.strokeOval(width / 2 + 170, height / 2 - 20, 30, 30);
            gc2.setFill(Color.BLACK);
            gc2.fillOval(width / 2 + 320, height / 2 - 20, 30, 30);
            gc2.setStroke(Color.RED);
            gc2.strokeOval(width / 2 + 320, height / 2 - 20, 30, 30);
            mediaPlayer1.setVolume(0);
            mediaPlayer12.setVolume(0);
            mediaPlayer13.setVolume(0);
        });
        btn55.setOnAction(event -> {
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            if (f5) mediaPlayer2.play();
            gc2.setFill(Color.RED);
            gc2.fillOval(width / 2 + 20, height / 2 - 20, 30, 30);
            gc2.setFill(Color.BLACK);
            gc2.fillOval(width / 2 - 130, height / 2 - 20, 30, 30);
            gc2.setStroke(Color.RED);
            gc2.strokeOval(width / 2 - 130, height / 2 - 20, 30, 30);
            gc2.setFill(Color.BLACK);
            gc2.fillOval(width / 2 + 170, height / 2 - 20, 30, 30);
            gc2.setStroke(Color.RED);
            gc2.strokeOval(width / 2 + 170, height / 2 - 20, 30, 30);
            gc2.setFill(Color.BLACK);
            gc2.fillOval(width / 2 + 320, height / 2 - 20, 30, 30);
            gc2.setStroke(Color.RED);
            gc2.strokeOval(width / 2 + 320, height / 2 - 20, 30, 30);
            mediaPlayer1.setVolume(0.3);
            mediaPlayer12.setVolume(0.3);
            mediaPlayer13.setVolume(0.3);
        });
        btn56.setOnAction(event -> {
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            if (f5) mediaPlayer2.play();
            gc2.setFill(Color.RED);
            gc2.fillOval(width / 2 + 170, height / 2 - 20, 30, 30);
            gc2.setFill(Color.BLACK);
            gc2.fillOval(width / 2 - 130, height / 2 - 20, 30, 30);
            gc2.setStroke(Color.RED);
            gc2.strokeOval(width / 2 - 130, height / 2 - 20, 30, 30);
            gc2.setFill(Color.BLACK);
            gc2.fillOval(width / 2 + 20, height / 2 - 20, 30, 30);
            gc2.setStroke(Color.RED);
            gc2.strokeOval(width / 2 + 20, height / 2 - 20, 30, 30);
            gc2.setFill(Color.BLACK);
            gc2.fillOval(width / 2 + 320, height / 2 - 20, 30, 30);
            gc2.setStroke(Color.RED);
            gc2.strokeOval(width / 2 + 320, height / 2 - 20, 30, 30);
            mediaPlayer1.setVolume(0.7);
            mediaPlayer12.setVolume(0.7);
            mediaPlayer13.setVolume(0.7);
        });
        btn57.setOnAction(event -> {
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            if (f5) mediaPlayer2.play();
            gc2.setFill(Color.RED);
            gc2.fillOval(width / 2 + 320, height / 2 - 20, 30, 30);
            gc2.setFill(Color.BLACK);
            gc2.fillOval(width / 2 - 130, height / 2 - 20, 30, 30);
            gc2.setStroke(Color.RED);
            gc2.strokeOval(width / 2 - 130, height / 2 - 20, 30, 30);
            gc2.setFill(Color.BLACK);
            gc2.fillOval(width / 2 + 170, height / 2 - 20, 30, 30);
            gc2.setStroke(Color.RED);
            gc2.strokeOval(width / 2 + 170, height / 2 - 20, 30, 30);
            gc2.setFill(Color.BLACK);
            gc2.fillOval(width / 2 + 20, height / 2 - 20, 30, 30);
            gc2.setStroke(Color.RED);
            gc2.strokeOval(width / 2 + 20, height / 2 - 20, 30, 30);
            mediaPlayer1.setVolume(1);
            mediaPlayer12.setVolume(1);
            mediaPlayer13.setVolume(1);
        });
        btn51.setOnAction(event -> {
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            if (f5) mediaPlayer2.play();
            gc2.setFill(Color.RED);
            gc2.fillOval(width / 2 - 130, height / 2 - 125, 30, 30);
            gc2.setFill(Color.BLACK);
            gc2.fillOval(width / 2 + 70, height / 2 - 125, 30, 30);
            gc2.setStroke(Color.RED);
            gc2.strokeOval(width / 2 + 70, height / 2 - 125, 30, 30);
            gc2.setFill(Color.BLACK);
            gc2.fillOval(width / 2 + 270, height / 2 - 125, 30, 30);
            gc2.setStroke(Color.RED);
            gc2.strokeOval(width / 2 + 270, height / 2 - 125, 30, 30);
            mediaPlayer1.play();
            mediaPlayer13.stop();
            mediaPlayer12.stop();
        });
        btn52.setOnAction(event -> {
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            if (f5) mediaPlayer2.play();
            gc2.setFill(Color.RED);
            gc2.fillOval(width / 2 + 70, height / 2 - 125, 30, 30);
            gc2.setFill(Color.BLACK);
            gc2.fillOval(width / 2 - 130, height / 2 - 125, 30, 30);
            gc2.setStroke(Color.RED);
            gc2.strokeOval(width / 2 - 130, height / 2 - 125, 30, 30);
            gc2.setFill(Color.BLACK);
            gc2.fillOval(width / 2 + 270, height / 2 - 125, 30, 30);
            gc2.setStroke(Color.RED);
            gc2.strokeOval(width / 2 + 270, height / 2 - 125, 30, 30);
            mediaPlayer1.stop();
            mediaPlayer13.stop();
            mediaPlayer12.play();
        });
        btn53.setOnAction(event -> {
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            if (f5) mediaPlayer2.play();
            gc2.setFill(Color.RED);
            gc2.fillOval(width / 2 + 270, height / 2 - 125, 30, 30);
            gc2.setFill(Color.BLACK);
            gc2.fillOval(width / 2 - 130, height / 2 - 125, 30, 30);
            gc2.setStroke(Color.RED);
            gc2.strokeOval(width / 2 - 130, height / 2 - 125, 30, 30);
            gc2.setFill(Color.BLACK);
            gc2.fillOval(width / 2 + 70, height / 2 - 125, 30, 30);
            gc2.setStroke(Color.RED);
            gc2.strokeOval(width / 2 + 70, height / 2 - 125, 30, 30);
            mediaPlayer1.stop();
            mediaPlayer13.play();
            mediaPlayer12.stop();
        });
        btn41.setOnAction(event -> {
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            if (f5) mediaPlayer2.play();
            name = "";
            primaryStage.setScene(scene);
        });
        btn42.setOnAction(event -> {
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            if (f5) mediaPlayer2.play();
            System.exit(0);
        });

        Button btn14 = new Button("排行榜");
        Button btn21 = new Button("新游戏");
        Button btn22 = new Button("载入游戏");
        Button btn23 = new Button("返回");

        Button btn31 = new Button("简单（0.5倍速）");
        Button btn32 = new Button("正常（1倍速）");
        Button btn33 = new Button("困难（2.5倍速）");
        Button btn34 = new Button("随机方块");
        Button btn35 = new Button("方块穿越边界");
        Button btn36 = new Button("返回");
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 10; i++) ranks[j][i] = "";
            for (int i = 0; i < 10; i++) rank[j][i] = 0;

            try {
                FileInputStream fin = new FileInputStream("./rank" + j + ".txt");
                for (int i = 0; i < 10; i++) {
                    int c = fin.read();
                    while (c != 13) {
                        ranks[j][i] = ranks[j][i] + (char) c;
                        c = fin.read();
                    }

                    rank[j][i] = fin.read() * 10;
                    rank[j][i] = rank[j][i] + fin.read();
                    texts[j][i] = new Text(i + 1 + " " + ranks[j][i] + " " + rank[j][i]);
                }
                fin.close();
            } catch (FileNotFoundException e) {
                for (int i = 0; i < 10; i++) {
                    ranks[j][i] = "null";
                    texts[j][i] = new Text(i + 1 + " null 0");
                }
            } catch (IOException e) {
                for (int i = 0; i < 10; i++) {
                    ranks[j][i] = "null";
                    texts[j][i] = new Text(i + 1 + " null 0");
                }
            }
        }

        pane1.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        scene2 = new Scene(pane1, width, height);
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        pane1.getChildren().add(canvas);
        pane1.getChildren().add(text1);
        pane1.getChildren().add(text2);
        pane1.getChildren().add(text3);
        pane1.getChildren().add(text5);
        text4.setLayoutX(width / 2 + 173);
        text4.setLayoutY(height - 830 + 27);
        text4.setFont(Font.font("Verdana", 20));
        text4.setFill(Color.WHITE);
        pane1.getChildren().add(text4);
        btn11.setOnAction(event -> {
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            if (f5) mediaPlayer2.play();
            primaryStage.setScene(scene1);
        });
        btn14.setOnAction(event -> {
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            if (f5) mediaPlayer2.play();
            primaryStage.setScene(scene4);
        });
        btn21.setOnAction(event -> {
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            if (f5) mediaPlayer2.play();
            primaryStage.setScene(scene5);
        });
        btn22.setOnAction(event -> {
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            if (f5) mediaPlayer2.play();
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            Stage s = new Stage();
            File file = fileChooser.showOpenDialog(s);
            if (file == null)
                return;
            String exportFilePath = file.getAbsolutePath();
            try {
                FileInputStream fin = new FileInputStream(exportFilePath);
                reset();
                for (int i = 0; i < 26; i++)
                    for (int j = 0; j <= 22; j++) arr[i][j] = (char) (fin.read());
                for (int i = 0; i < 26; i++)
                    for (int j = 0; j <= 22; j++)
                        if (fin.read() == 1) f1[i][j] = true;
                        else f1[i][j] = false;
                for (int i = 0; i < 5; i++)
                    for (int j = 0; j < 9; j++) tetris1[i][j] = (char) (fin.read());
                hang = getHang(tetris1);
                lie = getLie(tetris1);
                for (int i = 0; i < 5; i++)
                    for (int j = 0; j < 9; j++) {
                        gc.setFill(Color.BLACK);
                        gc.fillRect(width / 2 - 300 + (j / 2 - 1) * 30, height - 800 + (i) * 30, 30, 30);
                    }
                for (int i = 0; i < 5; i++)
                    for (int j = 0; j < 9; j++) {
                        tetris3[i][j] = (char) (fin.read());
                        if (tetris3[i][j] == '*') {
                            gc.setFill(Color.RED);
                            gc.fillRoundRect(width / 2 - 300 + (j / 2 - 1) * 30, height - 800 + (i) * 30, 30, 30, 10, 10);
                        }
                    }
                for (int k = 0; k < 7; k++)
                    for (int i = 0; i < 5; i++)
                        for (int j = 0; j < 9; j++)
                            tetrisAll[k][i][j] = (char) (fin.read());
                a = fin.read() / 2.0;
                b = fin.read();
                x = fin.read() / 2.0;
                y = fin.read();
                sum = fin.read() * 100;
                sum = sum + fin.read();
                if (fin.read() == 1) f4 = true;
                else f4 = false;
                mode = fin.read();
                dur = fin.read() * 100;
                int min, sec;
                min = fin.read();
                sec = fin.read();
                time = min * 60 + sec;
                int c = fin.read();
                while (c != -1) {
                    name = name + (char) (c);
                    c = fin.read();
                }
                fin.close();
                f3 = false;
                r = dur - 100;

                if ((min < 10) & (sec < 10)) text4.setText("0" + min + ":0" + sec);
                if ((min >= 10) & (sec < 10)) text4.setText(min + ":0" + sec);
                if ((min < 10) & (sec >= 10)) text4.setText("0" + min + ":" + sec);
                if ((min >= 10) & (sec >= 10)) text4.setText(min + ":" + sec);
                text2.setText("" + sum);
                file.delete();
                primaryStage.setScene(scene2);
                timeline.play();
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            }
        });
        f = true;
        scene4.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ESCAPE) primaryStage.setScene(scene);
            }
        });
        scene7.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ESCAPE) {
                    timeline.play();
                    primaryStage.setScene(scene2);
                }
            }
        });
        scene8.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ESCAPE) primaryStage.setScene(scene);
            }
        });
        scene5.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                f = true;
                if (ke.getCode() == KeyCode.ESCAPE) {
                    name = "";
                    text7.setText("");
                    primaryStage.setScene(scene1);
                }
                if (ke.getCode() == KeyCode.ENTER) if (!"".equals(name)) {
                    text7.setText("");
                    primaryStage.setScene(scene3);
                }
                if (ke.getCode() == KeyCode.BACK_SPACE) {
                    f = false;
                    if (!"".equals(name)) {
                        name = name.substring(0, name.length() - 1);
                        text7.setText(name);
                    }
                }

            }
        });
        scene5.setOnKeyTyped(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {

                if ((f) & (name.length() <= 10)) {
                    name = name + ke.getCharacter();
                    text7.setText(name);
                }

            }
        });
        btn23.setOnAction(event -> {
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            if (f5) mediaPlayer2.play();
            primaryStage.setScene(scene);
        });
        btn36.setOnAction(event -> {
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            if (f5) mediaPlayer2.play();
            name = "";
            primaryStage.setScene(scene1);
        });
        btn32.setOnAction(event -> {
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            if (f5) mediaPlayer2.play();
            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 9; j++)
                    for (int k = 0; k < 7; k++) tetrisAll[k][i][j] = ' ';
            tetrisAll[0][0][0] = tetrisAll[0][1][0] = tetrisAll[0][1][2] = tetrisAll[0][1][4] = '*';
            tetrisAll[1][0][4] = tetrisAll[1][1][0] = tetrisAll[1][1][2] = tetrisAll[1][1][4] = '*';
            tetrisAll[2][0][2] = tetrisAll[2][1][0] = tetrisAll[2][1][2] = tetrisAll[2][0][4] = '*';
            tetrisAll[3][0][2] = tetrisAll[3][1][0] = tetrisAll[3][1][2] = tetrisAll[3][1][4] = '*';
            tetrisAll[4][0][0] = tetrisAll[4][1][2] = tetrisAll[4][1][4] = tetrisAll[4][0][2] = '*';
            tetrisAll[5][0][0] = tetrisAll[5][0][2] = tetrisAll[5][0][4] = tetrisAll[5][0][6] = '*';
            tetrisAll[6][0][0] = tetrisAll[6][0][2] = tetrisAll[6][1][2] = tetrisAll[6][1][0] = '*';
            reset();
            mode = 0;
            dur = 500;
            f4 = false;
            primaryStage.setScene(scene2);
            timeline.play();
        });
        btn31.setOnAction(event -> {
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            if (f5) mediaPlayer2.play();
            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 9; j++)
                    for (int k = 0; k < 7; k++) tetrisAll[k][i][j] = ' ';
            tetrisAll[0][0][0] = tetrisAll[0][1][0] = tetrisAll[0][1][2] = tetrisAll[0][1][4] = '*';
            tetrisAll[1][0][4] = tetrisAll[1][1][0] = tetrisAll[1][1][2] = tetrisAll[1][1][4] = '*';
            tetrisAll[2][0][2] = tetrisAll[2][1][0] = tetrisAll[2][1][2] = tetrisAll[2][0][4] = '*';
            tetrisAll[3][0][2] = tetrisAll[3][1][0] = tetrisAll[3][1][2] = tetrisAll[3][1][4] = '*';
            tetrisAll[4][0][0] = tetrisAll[4][1][2] = tetrisAll[4][1][4] = tetrisAll[4][0][2] = '*';
            tetrisAll[5][0][0] = tetrisAll[5][0][2] = tetrisAll[5][0][4] = tetrisAll[5][0][6] = '*';
            tetrisAll[6][0][0] = tetrisAll[6][0][2] = tetrisAll[6][1][2] = tetrisAll[6][1][0] = '*';
            reset();
            dur = 1000;
            mode = 0;
            f4 = false;
            primaryStage.setScene(scene2);
            timeline.play();
        });
        btn33.setOnAction(event -> {
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            if (f5) mediaPlayer2.play();
            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 9; j++)
                    for (int k = 0; k < 7; k++) tetrisAll[k][i][j] = ' ';
            tetrisAll[0][0][0] = tetrisAll[0][1][0] = tetrisAll[0][1][2] = tetrisAll[0][1][4] = '*';
            tetrisAll[1][0][4] = tetrisAll[1][1][0] = tetrisAll[1][1][2] = tetrisAll[1][1][4] = '*';
            tetrisAll[2][0][2] = tetrisAll[2][1][0] = tetrisAll[2][1][2] = tetrisAll[2][0][4] = '*';
            tetrisAll[3][0][2] = tetrisAll[3][1][0] = tetrisAll[3][1][2] = tetrisAll[3][1][4] = '*';
            tetrisAll[4][0][0] = tetrisAll[4][1][2] = tetrisAll[4][1][4] = tetrisAll[4][0][2] = '*';
            tetrisAll[5][0][0] = tetrisAll[5][0][2] = tetrisAll[5][0][4] = tetrisAll[5][0][6] = '*';
            tetrisAll[6][0][0] = tetrisAll[6][0][2] = tetrisAll[6][1][2] = tetrisAll[6][1][0] = '*';
            reset();
            dur = 200;
            mode = 0;
            f4 = false;
            primaryStage.setScene(scene2);
            timeline.play();
        });
        btn34.setOnAction(event -> {
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            if (f5) mediaPlayer2.play();
            for (int k = 0; k < 7; k++) {
                while (true) {
                    int hang = (int) (Math.random() * 5);
                    int lie = (int) (Math.random() * 5) * 2;
                    if ((hang >= 3) & (lie >= 6)) continue;
                    for (int i = 0; i < 5; i++)
                        for (int j = 0; j < 9; j++) tetrisAll[k][i][j] = ' ';
                    int c = 0;
                    for (int i = 0; i <= hang; i++)
                        for (int j = 0; j <= lie; j = j + 2)
                            if ((int) (Math.random() * 2) == 1) {
                                tetrisAll[k][i][j] = '*';
                                c++;
                            }
                    if (tetrisAll[k][0][0] != '*') {
                        tetrisAll[k][0][0] = '*';
                        c++;
                    }
                    count = 0;
                    for (int i = 0; i < 5; i++)
                        for (int j = 0; j < 9; j++) vis[i][j] = 0;

                    start(tetrisAll[k], 0, 0);


                    if (c == count) {
                        boolean f = false;
                        if (k == 0) f = true;
                        for (int k1 = 0; k1 < k; k1++) {
                            f = false;
                            for (int i = 0; i < 5; i++)
                                for (int j = 0; j < 9; j++)
                                    if (tetrisAll[k][i][j] != tetrisAll[k1][i][j]) {
                                        f = true;
                                        break;
                                    }
                            if (!f) break;
                        }
                        if (f) break;
                    }
                }
            }
            reset();
            dur = 500;
            mode = 1;
            f4 = false;
            primaryStage.setScene(scene2);
            timeline.play();
        });
        btn35.setOnAction(event -> {
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            if (f5) mediaPlayer2.play();
            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 9; j++)
                    for (int k = 0; k < 7; k++) tetrisAll[k][i][j] = ' ';
            tetrisAll[0][0][0] = tetrisAll[0][1][0] = tetrisAll[0][1][2] = tetrisAll[0][1][4] = '*';
            tetrisAll[1][0][4] = tetrisAll[1][1][0] = tetrisAll[1][1][2] = tetrisAll[1][1][4] = '*';
            tetrisAll[2][0][2] = tetrisAll[2][1][0] = tetrisAll[2][1][2] = tetrisAll[2][0][4] = '*';
            tetrisAll[3][0][2] = tetrisAll[3][1][0] = tetrisAll[3][1][2] = tetrisAll[3][1][4] = '*';
            tetrisAll[4][0][0] = tetrisAll[4][1][2] = tetrisAll[4][1][4] = tetrisAll[4][0][2] = '*';
            tetrisAll[5][0][0] = tetrisAll[5][0][2] = tetrisAll[5][0][4] = tetrisAll[5][0][6] = '*';
            tetrisAll[6][0][0] = tetrisAll[6][0][2] = tetrisAll[6][1][2] = tetrisAll[6][1][0] = '*';
            reset();
            dur = 500;
            mode = 2;
            f4 = true;
            primaryStage.setScene(scene2);
            timeline.play();
        });
        r = 0;
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                time = time + 0.1;
                int min, sec;
                min = (int) (Math.floor(time) / 60);
                sec = (int) (Math.floor(time) % 60);
                if ((min < 10) & (sec < 10)) text4.setText("0" + min + ":0" + sec);
                if ((min >= 10) & (sec < 10)) text4.setText(min + ":0" + sec);
                if ((min < 10) & (sec >= 10)) text4.setText("0" + min + ":" + sec);
                if ((min >= 10) & (sec >= 10)) text4.setText(min + ":" + sec);
                r = r + 100;
                if (r == dur) {
                    r = 0;
                    if ((b + lie - y - 1 < 22) & (b - y > 0))
                        for (int i = (int) (a - x); i <= a + hang - x - 1; i++)
                            for (int j = b - y; j <= b + lie - y - 1; j++) {
                                if ((arr[i][j] == '*') & (!f1[i][j])) {
                                    arr[i][j] = ' ';
                                    gc.setFill(Color.BLACK);
                                    gc.fillRect(width / 2 - 150 + (j / 2 - 1) * 30, height - 830 + (i - 4) * 30, 30, 30);
                                }
                                if (arr[i + x2][j] == '+') {
                                    arr[i + x2][j] = ' ';
                                    gc.setFill(Color.BLACK);
                                    gc.fillRect(width / 2 - 150 + (j / 2 - 1) * 30 - 1, height - 830 + (i - 4 + x2) * 30 - 1, 32, 32);
                                }
                            }
                    if (f4) {
                        if (b + lie - y - 1 >= 22)
                            for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                                for (int j = b - y; j <= 20; j++) {
                                    if ((arr[i][j] == '*') & (!f1[i][j])) {
                                        arr[i][j] = ' ';
                                        gc.setFill(Color.BLACK);
                                        gc.fillRect(width / 2 - 150 + (j / 2 - 1) * 30, height - 830 + (i - 4) * 30, 30, 30);
                                    }
                                    if (arr[i + x2][j] == '+') {
                                        arr[i + x2][j] = ' ';
                                        gc.setFill(Color.BLACK);
                                        gc.fillRect(width / 2 - 150 + (j / 2 - 1) * 30 - 1, height - 830 + (i - 4 + x2) * 30 - 1, 32, 32);
                                    }
                                }
                                for (int j = 2; j <= b + lie - y - 1 - 20; j++) {
                                    if ((arr[i][j] == '*') & (!f1[i][j])) {
                                        arr[i][j] = ' ';
                                        gc.setFill(Color.BLACK);
                                        gc.fillRect(width / 2 - 150 + (j / 2 - 1) * 30, height - 830 + (i - 4) * 30, 30, 30);
                                    }
                                    if (arr[i + x2][j] == '+') {
                                        arr[i + x2][j] = ' ';
                                        gc.setFill(Color.BLACK);
                                        gc.fillRect(width / 2 - 150 + (j / 2 - 1) * 30 - 1, height - 830 + (i - 4 + x2) * 30 - 1, 32, 32);
                                    }
                                }
                            }
                        if (b - y <= 0)
                            for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                                for (int j = 2; j <= b + lie - y - 1; j++) {
                                    if ((arr[i][j] == '*') & (!f1[i][j])) {
                                        arr[i][j] = ' ';
                                        gc.setFill(Color.BLACK);
                                        gc.fillRect(width / 2 - 150 + (j / 2 - 1) * 30, height - 830 + (i - 4) * 30, 30, 30);
                                    }
                                    if (arr[i + x2][j] == '+') {
                                        arr[i + x2][j] = ' ';
                                        gc.setFill(Color.BLACK);
                                        gc.fillRect(width / 2 - 150 + (j / 2 - 1) * 30 - 1, height - 830 + (i - 4 + x2) * 30 - 1, 32, 32);
                                    }
                                }
                                for (int j = b - y + 20; j <= 20; j++) {
                                    if ((arr[i][j] == '*') & (!f1[i][j])) {
                                        arr[i][j] = ' ';
                                        gc.setFill(Color.BLACK);
                                        gc.fillRect(width / 2 - 150 + (j / 2 - 1) * 30, height - 830 + (i - 4) * 30, 30, 30);
                                    }
                                    if (arr[i + x2][j] == '+') {
                                        arr[i + x2][j] = ' ';
                                        gc.setFill(Color.BLACK);
                                        gc.fillRect(width / 2 - 150 + (j / 2 - 1) * 30 - 1, height - 830 + (i - 4 + x2) * 30 - 1, 32, 32);
                                    }
                                }
                            }
                    }
                    f = true;
                    if ((b + lie - y - 1 < 22) & (b - y > 0))
                        for (int i = 0; i <= hang - 1; i++)
                            for (int j = 0; j <= lie - 1; j = j + 2)
                                if ((arr[(int) (a + i + 1 - x)][b - y + j] != ' ') & (tetris1[i][j] != ' ')) {
                                    f = false;
                                    break;
                                }
                    if (f4) for (int j = 0; j <= hang - 1; j++) {
                        if (b + lie - y - 1 >= 22) {
                            for (int i = 0; i <= 20 - b + y; i = i + 2)
                                if ((arr[(int) (a + j + 1 - x)][b - y + i] != ' ') & (tetris1[j][i] != ' ')) {
                                    f = false;
                                    break;
                                }
                            for (int i = 22 - b + y; i <= lie - 1; i = i + 2)
                                if ((arr[(int) (a + j + 1 - x)][b - y + i - 20] != ' ') & (tetris1[j][i] != ' ')) {
                                    f = false;
                                    break;
                                }
                        }
                        if (b - y <= 0) {
                            for (int i = 2 - b + y; i <= lie - 1; i = i + 2)
                                if ((arr[(int) (a + j + 1 - x)][b - y + i] != ' ') & (tetris1[j][i] != ' ')) {
                                    f = false;
                                    break;
                                }
                            for (int i = 0; i <= y - b; i = i + 2)
                                if ((arr[(int) (a + j + 1 - x)][b - y + i + 20] != ' ') & (tetris1[j][i] != ' ')) {
                                    f = false;
                                    break;
                                }
                        }
                    }
                    if (f) a++;
                    else {
                        int t = 0;
                        if ((b + lie - y - 1 < 22) & (b - y > 0))
                            for (int i = (int) (a - x); i <= a + hang - x - 1; i++)
                                for (int j = b - y; j <= b + lie - y - 1; j = j + 2)
                                    if (arr[i][j] == ' ') {
                                        arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y];
                                        if (arr[i][j] == '*') f1[i][j] = true;
                                    }
                        if (f4) {
                            if (b + lie - y - 1 >= 22)
                                for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                                    for (int j = b - y; j <= 20; j = j + 2)
                                        if (arr[i][j] == ' ') {
                                            arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y];
                                            if (arr[i][j] == '*') f1[i][j] = true;
                                        }
                                    for (int j = 2; j <= b + lie - y - 1 - 20; j = j + 2)
                                        if (arr[i][j] == ' ') {
                                            arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y + 20];
                                            if (arr[i][j] == '*') f1[i][j] = true;
                                        }
                                }
                            if (b - y <= 0)
                                for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                                    for (int j = b - y + 20; j <= 20; j = j + 2)
                                        if (arr[i][j] == ' ') {
                                            arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y - 20];
                                            if (arr[i][j] == '*') f1[i][j] = true;
                                        }
                                    for (int j = 2; j <= b + lie - y - 1; j = j + 2)
                                        if (arr[i][j] == ' ') {
                                            arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y];
                                            if (arr[i][j] == '*') f1[i][j] = true;
                                        }
                                }
                        }
                        for (int i = 4; i < 26; i++) {
                            f = true;
                            for (int j = 2; j < 22; j = j + 2)
                                if (arr[i][j] != '*') {
                                    f = false;
                                    break;
                                }
                            if (f) {
                                for (int m = i; m >= 4; m--)
                                    for (int j = 2; j < 22; j = j + 2) {
                                        arr[m][j] = arr[m - 1][j];
                                        f1[m][j] = f1[m - 1][j];
                                        if (arr[m - 1][j] != '*') {
                                            gc.setFill(Color.BLACK);
                                            gc.fillRect(width / 2 - 150 + (j / 2 - 1) * 30, height - 830 + (m - 4) * 30, 30, 30);
                                        }
                                    }
                                t++;
                            }
                        }
                        if (t == 1) {
                            MediaPlayer mediaPlayer2 = new MediaPlayer(media3);
                            if (f5) mediaPlayer2.play();
                        }
                        if (t == 2) {
                            MediaPlayer mediaPlayer2 = new MediaPlayer(media4);
                            if (f5) mediaPlayer2.play();
                        }
                        if (t == 3) {
                            MediaPlayer mediaPlayer2 = new MediaPlayer(media5);
                            if (f5) mediaPlayer2.play();
                        }
                        if (t == 4) {
                            MediaPlayer mediaPlayer2 = new MediaPlayer(media6);
                            if (f5) mediaPlayer2.play();
                        }
                        if (t >= 5) {
                            MediaPlayer mediaPlayer2 = new MediaPlayer(media7);
                            if (f5) mediaPlayer2.play();
                        }
                        sum = (int) (sum + Math.pow(2, t - 1) * t * 10 * 500 / dur);
                        text2.setText("" + sum);
                        if ((sum >= 10) & (sum < 100)) text2.setLayoutX(width / 2 + 190);
                        if ((sum >= 100) & (sum < 1000)) text2.setLayoutX(width / 2 + 180);
                        if ((sum >= 1000) & (sum < 10000)) text2.setLayoutX(width / 2 + 170);
                        for (int i = 0; i < 5; i++)
                            for (int j = 0; j < 9; j++) tetris1[i][j] = tetris3[i][j];
                        int k = (int) (Math.random() * 7);
                        for (int i = 0; i < 5; i++)
                            for (int j = 0; j < 9; j++) {
                                if (tetris3[i][j] == '*') {
                                    gc.setFill(Color.BLACK);
                                    gc.fillRect(width / 2 - 300 + (j / 2 - 1) * 30, height - 800 + (i) * 30, 30, 30);
                                }
                                tetris3[i][j] = tetrisAll[k][i][j];
                                if (tetris3[i][j] == '*') {
                                    gc.setFill(Color.RED);
                                    gc.fillRoundRect(width / 2 - 300 + (j / 2 - 1) * 30, height - 800 + (i) * 30, 30, 30, 10, 10);
                                }
                            }
                        lie = getLie(tetris1);
                        hang = getHang(tetris1);
                        f2 = false;
                        if ((lie % 4 == 1) & (hang % 2 == 0)) {
                            y = (lie - 1) / 2;
                            x = hang / 2;
                        }
                        if ((lie % 4 == 3) & (hang % 2 == 1)) {
                            x = (hang - 1) / 2;
                            y = (lie - 2) / 2;
                        }
                        if ((lie % 4 == 1) & (hang % 2 == 1)) {
                            y = (lie - 1) / 2;
                            x = hang / 2;
                        }
                        if ((lie % 4 == 3) & (hang % 2 == 0)) {
                            x = (hang - 1) / 2.0;
                            y = (lie - 1) / 2;
                            f2 = true;
                        }
                        if (!f2) {
                            b = 10;
                            a = 4 - (hang - x) + 1;
                        }
                        if (f2) {
                            b = 11;
                            a = 4 - (hang - x) + 1;
                        }
                    }
                    if ((b + lie - y - 1 < 22) & (b - y > 0))
                        for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                            for (int j = b - y; j <= b + lie - y - 1; j++) {
                                if ((arr[i][j] == '*') & (tetris1[(int) (i - (a - x))][j - b + y] == '*')) {
                                    f3 = true;
                                    MediaPlayer mediaPlayer2 = new MediaPlayer(media9);
                                    if (f5) mediaPlayer2.play();
                                    timeline.stop();
                                    text13.setText("你的得分为：" + sum);
                                    primaryStage.setScene(scene6);
                                    f = false;
                                    int i1 = -1;
                                    for (int i2 = 0; i2 < 10; i2++)
                                        if (ranks[mode][i2].equals(name)) {
                                            f = true;
                                            i1 = i2;
                                            break;
                                        }
                                    if (!f)
                                        if (sum > rank[mode][9]) {
                                            int i2 = 0;
                                            while (rank[mode][i2] >= sum) i2++;
                                            for (int j2 = 9; j2 > i2; j2--) {
                                                rank[mode][j2] = rank[mode][j2 - 1];
                                                ranks[mode][j2] = ranks[mode][j2 - 1];
                                            }
                                            rank[mode][i2] = sum;
                                            ranks[mode][i2] = name;
                                            for (int j2 = 0; j2 < 10; j2++)
                                                texts[mode][j2].setText(j2 + 1 + " " + ranks[mode][j2] + " " + rank[mode][j2]);

                                        }
                                    if (f)
                                        if (rank[mode][i1] < sum) {
                                            int i2 = 0;
                                            while (rank[mode][i2] >= sum) i2++;
                                            for (int j2 = i1; j2 > i2; j2--) {
                                                rank[j2] = rank[j2 - 1];
                                                ranks[j2] = ranks[j2 - 1];
                                            }
                                            rank[mode][i2] = sum;
                                            ranks[mode][i2] = name;
                                            for (int j2 = 0; j2 < 10; j2++)
                                                texts[mode][j2].setText(j2 + 1 + " " + ranks[mode][j2] + " " + rank[mode][j2]);
                                        }
                                    try {
                                        FileOutputStream fout = new FileOutputStream("./rank" + mode + ".txt");
                                        for (int i2 = 0; i2 < 10; i2++) {
                                            char[] c = ranks[mode][i2].toCharArray();
                                            for (int j2 = 0; j2 < c.length; j2++) fout.write(c[j2]);
                                            fout.write(13);
                                            fout.write(rank[mode][i2] / 100);
                                            fout.write(rank[mode][i2] % 100);
                                        }
                                        fout.close();
                                    } catch (FileNotFoundException e) {

                                    } catch (IOException e) {

                                    }
                                }
                                if (arr[i][j] == ' ') arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y];
                            }
                        }
                    if (f4) {
                        if (b + lie - y - 1 >= 22)
                            for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                                for (int j = b - y; j <= 20; j++)
                                    if (arr[i][j] == ' ') arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y];
                                for (int j = 2; j <= b + lie - y - 1 - 20; j++)
                                    if (arr[i][j] == ' ') arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y + 20];
                            }
                        if (b - y <= 0)
                            for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                                for (int j = 2; j <= b + lie - y - 1; j++)
                                    if (arr[i][j] == ' ') arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y];
                                for (int j = b - y + 20; j <= 20; j++)
                                    if (arr[i][j] == ' ') arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y - 20];
                            }
                    }
                    x2 = 30;
                    if ((b + lie - y - 1 < 22) & (b - y > 0))
                        for (int j = b - y; j <= b + lie - y - 1; j++) {
                            int x1 = 0;
                            for (int i = (int) (a - x); i <= a + hang - x - 1; i++)
                                if ((arr[i][j] == '*') & (!f1[i][j])) x1 = i;
                            int i = (int) (a - x);
                            while (true) {
                                if ((arr[i][j] != ' ') & (f1[i][j]) & (i - 1 >= x1)) break;
                                i++;
                            }
                            i = i - 1;
                            if (i - x1 < x2) x2 = i - x1;
                        }
                    if (f4) {
                        if (b + lie - y - 1 >= 22) {
                            for (int j = b - y; j <= 20; j++) {
                                int x1 = 0;
                                for (int i = (int) (a - x); i <= a + hang - x - 1; i++)
                                    if ((arr[i][j] == '*') & (!f1[i][j])) x1 = i;
                                int i = (int) (a - x);
                                while (true) {
                                    if ((arr[i][j] != ' ') & (f1[i][j])) break;
                                    i++;
                                }
                                i = i - 1;
                                if (i - x1 < x2) x2 = i - x1;
                            }
                            for (int j = 2; j <= b + lie - y - 1 - 20; j++) {
                                int x1 = 0;
                                for (int i = (int) (a - x); i <= a + hang - x - 1; i++)
                                    if ((arr[i][j] == '*') & (!f1[i][j])) x1 = i;
                                int i = (int) (a - x);
                                while (true) {
                                    if ((arr[i][j] != ' ') & (f1[i][j])) break;
                                    i++;
                                }
                                i = i - 1;
                                if (i - x1 < x2) x2 = i - x1;
                            }
                        }
                        if (b - y <= 0) {
                            for (int j = 2; j <= b + lie - y - 1; j++) {
                                int x1 = 0;
                                for (int i = (int) (a - x); i <= a + hang - x - 1; i++)
                                    if ((arr[i][j] == '*') & (!f1[i][j])) x1 = i;
                                int i = (int) (a - x);
                                while (true) {
                                    if ((arr[i][j] != ' ') & (f1[i][j])) break;
                                    i++;
                                }
                                i = i - 1;
                                if (i - x1 < x2) x2 = i - x1;
                            }
                            for (int j = b - y + 20; j <= 20; j++) {
                                int x1 = 0;
                                for (int i = (int) (a - x); i <= a + hang - x - 1; i++)
                                    if ((arr[i][j] == '*') & (!f1[i][j])) x1 = i;
                                int i = (int) (a - x);
                                while (true) {
                                    if ((arr[i][j] != ' ') & (f1[i][j])) break;
                                    i++;
                                }
                                i = i - 1;
                                if (i - x1 < x2) x2 = i - x1;
                            }
                        }
                    }
                    if ((b + lie - y - 1 < 22) & (b - y > 0))
                        for (int i = (int) (a - x); i <= a + hang - x - 1; i++)
                            for (int j = b - y; j <= b + lie - y - 1; j++)
                                if ((arr[i + x2][j] == ' ') & (arr[i][j] == '*')) arr[i + x2][j] = '+';
                    if (f4) {
                        if (b + lie - y - 1 >= 22)
                            for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                                for (int j = b - y; j <= 20; j++)
                                    if ((arr[i + x2][j] == ' ') & (arr[i][j] == '*')) arr[i + x2][j] = '+';
                                for (int j = 2; j <= b + lie - y - 1 - 20; j++)
                                    if ((arr[i + x2][j] == ' ') & (arr[i][j] == '*')) arr[i + x2][j] = '+';

                            }
                        if (b - y <= 0)
                            for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                                for (int j = 2; j <= b + lie - y - 1; j++)
                                    if ((arr[i + x2][j] == ' ') & (arr[i][j] == '*')) arr[i + x2][j] = '+';
                                for (int j = b - y + 20; j <= 20; j++)
                                    if ((arr[i + x2][j] == ' ') & (arr[i][j] == '*')) arr[i + x2][j] = '+';

                            }
                    }
                    for (int i = 4; i < 26; i++)
                        for (int j = 0; j < 23; j = j + 2) {
                            if ((arr[i][j] == '*') & (!f1[i][j])) {
                                gc.setFill(Color.RED);
                                gc.fillRoundRect(width / 2 - 150 + (j / 2 - 1) * 30, height - 830 + (i - 4) * 30, 30, 30, 10, 10);
                            }
                            if (arr[i][j] == '+') {
                                gc.setStroke(Color.RED);
                                gc.strokeRoundRect(width / 2 - 150 + (j / 2 - 1) * 30, height - 830 + (i - 4) * 30, 30, 30, 10, 10);
                            }
                            if ((arr[i][j] == '*') & (f1[i][j])) {
                                gc.setFill(Color.WHITE);
                                gc.fillRoundRect(width / 2 - 150 + (j / 2 - 1) * 30, height - 830 + (i - 4) * 30, 30, 30, 10, 10);
                            }
                        }
                }
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        scene2.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {

                if (ke.getCode() == KeyCode.ESCAPE) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("暂停");
                    alert.setHeaderText("游戏暂停");
                    alert.setContentText("请选择：");
                    timeline.pause();

                    ButtonType buttonTypeOne = new ButtonType("重新开始");
                    ButtonType buttonTypeTwo = new ButtonType("游戏帮助");
                    ButtonType buttonTypeThree = new ButtonType("退出游戏");
                    ButtonType buttonTypeCancel = new ButtonType("继续游戏", ButtonBar.ButtonData.CANCEL_CLOSE);
                    alert.getButtonTypes().setAll(buttonTypeCancel, buttonTypeOne, buttonTypeTwo, buttonTypeThree);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonTypeThree) {
                        timeline.stop();
                        f = false;
                        int i1 = -1;
                        for (int i = 0; i < 10; i++)
                            if (ranks[mode][i].equals(name)) {
                                f = true;
                                i1 = i;
                                break;
                            }
                        if (!f)
                            if (sum > rank[mode][9]) {
                                int i = 0;
                                while (rank[mode][i] >= sum) i++;
                                for (int j = 9; j > i; j--) {
                                    rank[mode][j] = rank[mode][j - 1];
                                    ranks[mode][j] = ranks[mode][j - 1];
                                }
                                rank[mode][i] = sum;
                                ranks[mode][i] = name;
                                for (int j = 0; j < 10; j++)
                                    texts[mode][j].setText(j + 1 + " " + ranks[mode][j] + " " + rank[mode][j]);

                            }
                        if (f)
                            if (rank[mode][i1] < sum) {
                                int i = 0;
                                while (rank[mode][i] >= sum) i++;
                                for (int j = i1; j > i; j--) {
                                    rank[j] = rank[j - 1];
                                    ranks[j] = ranks[j - 1];
                                }
                                rank[mode][i] = sum;
                                ranks[mode][i] = name;
                                for (int j = 0; j < 10; j++)
                                    texts[mode][j].setText(j + 1 + " " + ranks[mode][j] + " " + rank[mode][j]);
                            }
                        try {
                            FileOutputStream fout = new FileOutputStream("./rank" + mode + ".txt");
                            for (int i = 0; i < 10; i++) {
                                char[] c = ranks[mode][i].toCharArray();
                                for (int j = 0; j < c.length; j++) fout.write(c[j]);
                                fout.write(13);
                                fout.write(rank[mode][i] / 100);
                                fout.write(rank[mode][i] % 100);
                            }
                            fout.close();
                        } catch (FileNotFoundException e) {

                        } catch (IOException e) {

                        }
                        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "游戏未结束，是否保存?");
                        confirmation.setTitle("保存？");
                        confirmation.setHeaderText("游戏退出");
                        Optional<ButtonType> result1 = confirmation.showAndWait();
                        if (result.isPresent() && result1.get() == ButtonType.OK) {
                            FileChooser fileChooser = new FileChooser();
                            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                            fileChooser.getExtensionFilters().add(extFilter);
                            Stage s = new Stage();
                            File file = fileChooser.showSaveDialog(s);
                            if (file == null) {
                                name = "";
                                primaryStage.setScene(scene);
                                return;
                            }
                            if (file.exists()) file.delete();
                            String exportFilePath = file.getAbsolutePath();
                            try {
                                FileOutputStream fout = new FileOutputStream(exportFilePath);
                                for (int i = 0; i < 26; i++)
                                    for (int j = 0; j <= 22; j++) fout.write(arr[i][j]);
                                for (int i = 0; i < 26; i++)
                                    for (int j = 0; j <= 22; j++)
                                        if (f1[i][j]) fout.write(1);
                                        else fout.write(0);
                                for (int i = 0; i < 5; i++)
                                    for (int j = 0; j < 9; j++) fout.write(tetris1[i][j]);
                                for (int i = 0; i < 5; i++)
                                    for (int j = 0; j < 9; j++) fout.write(tetris3[i][j]);
                                for (int k = 0; k < 7; k++)
                                    for (int i = 0; i < 5; i++)
                                        for (int j = 0; j < 9; j++)
                                            fout.write(tetrisAll[k][i][j]);
                                fout.write((int) (2 * a));
                                fout.write(b);
                                fout.write((int) (2 * x));
                                fout.write(y);
                                fout.write(sum / 100);
                                fout.write(sum % 100);
                                if (f4) fout.write(1);
                                else fout.write(0);
                                fout.write(mode);
                                fout.write(dur / 100);
                                fout.write((int) (Math.floor(time) / 60));
                                fout.write((int) (Math.floor(time) % 60));
                                char[] c = name.toCharArray();
                                for (int i = 0; i < name.length(); i++) fout.write(c[i]);
                                fout.close();
                            } catch (FileNotFoundException e) {

                            } catch (IOException e) {

                            }
                        }
                        name = "";
                        primaryStage.setScene(scene);
                    } else if (result.get() == buttonTypeOne) {
                        f = false;
                        int i1 = -1;
                        for (int i = 0; i < 10; i++)
                            if (ranks[mode][i].equals(name)) {
                                f = true;
                                i1 = i;
                                break;
                            }
                        if (!f)
                            if (sum > rank[mode][9]) {
                                int i = 0;
                                while (rank[mode][i] >= sum) i++;
                                for (int j = 9; j > i; j--) {
                                    rank[mode][j] = rank[mode][j - 1];
                                    ranks[mode][j] = ranks[mode][j - 1];
                                }
                                rank[mode][i] = sum;
                                ranks[mode][i] = name;
                                for (int j = 0; j < 10; j++)
                                    texts[mode][j].setText(j + 1 + " " + ranks[mode][j] + " " + rank[mode][j]);

                            }
                        if (f)
                            if (rank[mode][i1] < sum) {
                                int i = 0;
                                while (rank[mode][i] >= sum) i++;
                                for (int j = i1; j > i; j--) {
                                    rank[j] = rank[j - 1];
                                    ranks[j] = ranks[j - 1];
                                }
                                rank[mode][i] = sum;
                                ranks[mode][i] = name;
                                for (int j = 0; j < 10; j++)
                                    texts[mode][j].setText(j + 1 + " " + ranks[mode][j] + " " + rank[mode][j]);
                            }
                        try {
                            FileOutputStream fout = new FileOutputStream("./rank" + mode + ".txt");
                            for (int i = 0; i < 10; i++) {
                                char[] c = ranks[mode][i].toCharArray();
                                for (int j = 0; j < c.length; j++) fout.write(c[j]);
                                fout.write(13);
                                fout.write(rank[mode][i]);
                            }
                            fout.close();
                        } catch (FileNotFoundException e) {

                        } catch (IOException e) {

                        }
                        reset();
                        timeline.play();
                    } else if (result.get() == buttonTypeTwo) {
                        timeline.pause();
                        primaryStage.setScene(scene7);
                    } else {
                        timeline.play();
                    }
                } else if (!f3) {
                    if ((b + lie - y - 1 < 22) & (b - y > 0))
                        for (int i = (int) (a - x); i <= a + hang - x - 1; i++)
                            for (int j = b - y; j <= b + lie - y - 1; j++) {
                                if ((arr[i][j] == '*') & (!f1[i][j])) {
                                    arr[i][j] = ' ';
                                    gc.setFill(Color.BLACK);
                                    gc.fillRect(width / 2 - 150 + (j / 2 - 1) * 30, height - 830 + (i - 4) * 30, 30, 30);
                                }
                                if (arr[i + x2][j] == '+') {
                                    arr[i + x2][j] = ' ';
                                    gc.setFill(Color.BLACK);
                                    gc.fillRect(width / 2 - 150 + (j / 2 - 1) * 30 - 1, height - 830 + (i - 4 + x2) * 30 - 1, 32, 32);
                                }
                            }
                    if (f4) {
                        if (b + lie - y - 1 >= 22)
                            for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                                for (int j = b - y; j <= 20; j++) {
                                    if ((arr[i][j] == '*') & (!f1[i][j])) {
                                        arr[i][j] = ' ';
                                        gc.setFill(Color.BLACK);
                                        gc.fillRect(width / 2 - 150 + (j / 2 - 1) * 30, height - 830 + (i - 4) * 30, 30, 30);
                                    }
                                    if (arr[i + x2][j] == '+') {
                                        arr[i + x2][j] = ' ';
                                        gc.setFill(Color.BLACK);
                                        gc.fillRect(width / 2 - 150 + (j / 2 - 1) * 30 - 1, height - 830 + (i - 4 + x2) * 30 - 1, 32, 32);
                                    }
                                }
                                for (int j = 2; j <= b + lie - y - 1 - 20; j++) {
                                    if ((arr[i][j] == '*') & (!f1[i][j])) {
                                        arr[i][j] = ' ';
                                        gc.setFill(Color.BLACK);
                                        gc.fillRect(width / 2 - 150 + (j / 2 - 1) * 30, height - 830 + (i - 4) * 30, 30, 30);
                                    }
                                    if (arr[i + x2][j] == '+') {
                                        arr[i + x2][j] = ' ';
                                        gc.setFill(Color.BLACK);
                                        gc.fillRect(width / 2 - 150 + (j / 2 - 1) * 30 - 1, height - 830 + (i - 4 + x2) * 30 - 1, 32, 32);
                                    }
                                }
                            }
                        if (b - y <= 0)
                            for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                                for (int j = 2; j <= b + lie - y - 1; j++) {
                                    if ((arr[i][j] == '*') & (!f1[i][j])) {
                                        arr[i][j] = ' ';
                                        gc.setFill(Color.BLACK);
                                        gc.fillRect(width / 2 - 150 + (j / 2 - 1) * 30, height - 830 + (i - 4) * 30, 30, 30);
                                    }
                                    if (arr[i + x2][j] == '+') {
                                        arr[i + x2][j] = ' ';
                                        gc.setFill(Color.BLACK);
                                        gc.fillRect(width / 2 - 150 + (j / 2 - 1) * 30 - 1, height - 830 + (i - 4 + x2) * 30 - 1, 32, 32);
                                    }
                                }
                                for (int j = b - y + 20; j <= 20; j++) {
                                    if ((arr[i][j] == '*') & (!f1[i][j])) {
                                        arr[i][j] = ' ';
                                        gc.setFill(Color.BLACK);
                                        gc.fillRect(width / 2 - 150 + (j / 2 - 1) * 30, height - 830 + (i - 4) * 30, 30, 30);
                                    }
                                    if (arr[i + x2][j] == '+') {
                                        arr[i + x2][j] = ' ';
                                        gc.setFill(Color.BLACK);
                                        gc.fillRect(width / 2 - 150 + (j / 2 - 1) * 30 - 1, height - 830 + (i - 4 + x2) * 30 - 1, 32, 32);
                                    }
                                }
                            }
                    }
                    String letter = ke.getText();
                    if (letter.equals("s")) {
                        f = true;
                        if ((b + lie - y - 1 < 22) & (b - y > 0))
                            for (int i = 0; i <= hang - 1; i++)
                                for (int j = 0; j <= lie - 1; j = j + 2)
                                    if ((arr[(int) (a + i + 1 - x)][b - y + j] != ' ') & (tetris1[i][j] != ' ')) {
                                        f = false;
                                        break;
                                    }
                        if (f4) for (int j = 0; j <= hang - 1; j++) {
                            if (b + lie - y - 1 >= 22) {
                                for (int i = 0; i <= 20 - b + y; i = i + 2)
                                    if ((arr[(int) (a + j + 1 - x)][b - y + i] != ' ') & (tetris1[j][i] != ' ')) {
                                        f = false;
                                        break;
                                    }
                                for (int i = 22 - b + y; i <= lie - 1; i = i + 2)
                                    if ((arr[(int) (a + j + 1 - x)][b - y + i - 20] != ' ') & (tetris1[j][i] != ' ')) {
                                        f = false;
                                        break;
                                    }
                            }
                            if (b - y <= 0) {
                                for (int i = 2 - b + y; i <= lie - 1; i = i + 2)
                                    if ((arr[(int) (a + j + 1 - x)][b - y + i] != ' ') & (tetris1[j][i] != ' ')) {
                                        f = false;
                                        break;
                                    }
                                for (int i = 0; i <= y - b; i = i + 2)
                                    if ((arr[(int) (a + j + 1 - x)][b - y + i + 20] != ' ') & (tetris1[j][i] != ' ')) {
                                        f = false;
                                        break;
                                    }
                            }
                        }
                        if (f) a++;
                    }
                    if (letter.equals("a")) {
                        f = true;
                        for (int i = 0; i <= hang - 1; i++)
                            for (int j = 0; j <= lie - 1; j = j + 2) {
                                if ((!f4) | ((f4) & (b - y + j > 2)))
                                    if ((arr[(int) (a + i - x)][b - y - 2 + j] != ' ') & (tetris1[i][j] != ' ')) {
                                        f = false;
                                        break;
                                    }
                                if ((f4) & (b - y + j <= 2))
                                    if ((arr[(int) (a + i - x)][b - y - 2 + 20 + j] != ' ') & (tetris1[i][j] != ' ')) {
                                        f = false;
                                        break;
                                    }
                            }
                        if (f) b = b - 2;
                        if (b <= 0) b = b + 20;
                    }
                    if (letter.equals("d")) {
                        f = true;
                        for (int i = 0; i <= hang - 1; i++)
                            for (int j = 0; j <= lie - 1; j = j + 2) {
                                if ((!f4) | ((f4) & (b + j - y <= 18)))
                                    if ((arr[(int) (a + i - x)][b - y + 2 + j] != ' ') & (tetris1[i][j] != ' ')) {
                                        f = false;
                                        break;
                                    }
                                if ((f4) & (b + j - y > 18))
                                    if ((arr[(int) (a + i - x)][b + j - y + 2 - 20] != ' ') & (tetris1[i][j] != ' ')) {
                                        f = false;
                                        break;
                                    }
                            }
                        if (f) b = b + 2;
                        if (b >= 22) b = b - 20;
                    }
                    if (letter.equals("w")) {
                        for (int i = 0; i < 5; i++)
                            for (int j = 0; j < 9; j++) tetris2[i][j] = ' ';
                        for (int i = 0; i <= hang - 1; i++)
                            for (int j = 0; j <= lie - 1; j = j + 2)
                                tetris2[(int) (x - (y - j) / 2.0 + 2 - x)][(int) (y + 2 * (x - i) + 4 - y)] = tetris1[i][j];
                        int lie1 = -1;
                        f = true;
                        while (f) {
                            lie1++;
                            for (int i = 0; i < 5; i++)
                                if (tetris2[i][lie1] == '*') {
                                    f = false;
                                    break;
                                }
                        }
                        int hang1 = -1;
                        f = true;
                        while (f) {
                            hang1++;
                            for (int i = 0; i < 9; i++)
                                if (tetris2[hang1][i] == '*') {
                                    f = false;
                                    break;
                                }
                        }
                        int hang2, lie2, y1;
                        double x1;
                        lie2 = hang * 2 - 1;
                        hang2 = (lie + 1) / 2;
                        if (f2) {
                            x1 = 1.5 - hang1;
                            y1 = 4 - lie1;
                        } else {
                            x1 = 2 - hang1;
                            y1 = 4 - lie1;
                        }
                        f = true;
                        if (!f4)
                            if ((b + lie2 - y1 - 1 <= 22) & (b - y1 >= 0))
                                for (int i = (int) (a - x1); i <= a + hang2 - x1 - 1; i++)
                                    for (int j = b - y1; j <= b + lie2 - y1 - 1; j = j + 2)
                                        if ((arr[i][j] != ' ') & (tetris2[(int) (i - (a - x1) + hang1)][j - b + y1 + lie1] != ' ')) {
                                            f = false;
                                            break;
                                        }
                        if ((!f4) & ((b + lie2 - y1 - 1 > 22) | (b - y1 < 0))) f = false;
                        if (f4) {
                            if (b + lie2 - y1 - 1 >= 22)
                                for (int i = (int) (a - x1); i <= a + hang2 - x1 - 1; i++) {
                                    for (int j = b - y1; j <= 20; j = j + 2)
                                        if ((arr[i][j] != ' ') & (tetris2[(int) (i - (a - x1) + hang1)][j - b + y1 + lie1] != ' ')) {
                                            f = false;
                                            break;
                                        }
                                    for (int j = 2; j <= b + lie2 - y1 - 1 - 20; j = j + 2)
                                        if ((arr[i][j] != ' ') & (tetris2[(int) (i - (a - x1) + hang1)][j - b + y1 + lie1 + 20] != ' ')) {
                                            f = false;
                                            break;
                                        }
                                }
                            if (b - y1 <= 0)
                                for (int i = (int) (a - x1); i <= a + hang2 - x1 - 1; i++) {
                                    for (int j = b - y1 + 20; j <= 20; j = j + 2)
                                        if ((arr[i][j] != ' ') & (tetris2[(int) (i - (a - x1) + hang1)][j - b + y1 + lie1 - 20] != ' ')) {
                                            f = false;
                                            break;
                                        }
                                    for (int j = 2; j <= b + lie2 - y1 - 1; j = j + 2)
                                        if ((arr[i][j] != ' ') & (tetris2[(int) (i - (a - x1) + hang1)][j - b + y1 + lie1] != ' ')) {
                                            f = false;
                                            break;
                                        }
                                }
                        }
                        if (f) {
                            hang = hang2;
                            lie = lie2;
                            for (int i = 0; i < 5; i++)
                                for (int j = 0; j < 9; j++) tetris1[i][j] = ' ';
                            for (int i = 0; i < hang; i++)
                                for (int j = 0; j < lie; j = j + 2) tetris1[i][j] = tetris2[i + hang1][j + lie1];
                            x = x1;
                            y = y1;
                        }
                    }
                    if (letter.equals("x")) {
                        MediaPlayer mediaPlayer3 = new MediaPlayer(media8);
                        if (f5) mediaPlayer3.play();
                        a = a + x2;
                        int t = 0;
                        if ((b + lie - y - 1 < 22) & (b - y > 0))
                            for (int i = (int) (a - x); i <= a + hang - x - 1; i++)
                                for (int j = b - y; j <= b + lie - y - 1; j = j + 2)
                                    if (arr[i][j] == ' ') {
                                        arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y];
                                        if (arr[i][j] == '*') f1[i][j] = true;
                                    }
                        if (f4) {
                            if (b + lie - y - 1 >= 22)
                                for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                                    for (int j = b - y; j <= 20; j = j + 2)
                                        if (arr[i][j] == ' ') {
                                            arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y];
                                            if (arr[i][j] == '*') f1[i][j] = true;
                                        }
                                    for (int j = 2; j <= b + lie - y - 1 - 20; j = j + 2)
                                        if (arr[i][j] == ' ') {
                                            arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y + 20];
                                            if (arr[i][j] == '*') f1[i][j] = true;
                                        }
                                }
                            if (b - y <= 0)
                                for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                                    for (int j = b - y + 20; j <= 20; j = j + 2)
                                        if (arr[i][j] == ' ') {
                                            arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y - 20];
                                            if (arr[i][j] == '*') f1[i][j] = true;
                                        }
                                    for (int j = 2; j <= b + lie - y - 1; j = j + 2)
                                        if (arr[i][j] == ' ') {
                                            arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y];
                                            if (arr[i][j] == '*') f1[i][j] = true;
                                        }
                                }
                        }
                        for (int i = 4; i < 26; i++) {
                            f = true;
                            for (int j = 2; j < 22; j = j + 2)
                                if (arr[i][j] != '*') {
                                    f = false;
                                    break;
                                }
                            if (f) {
                                for (int m = i; m >= 4; m--)
                                    for (int j = 2; j < 22; j = j + 2) {
                                        arr[m][j] = arr[m - 1][j];
                                        f1[m][j] = f1[m - 1][j];
                                        if (arr[m - 1][j] != '*') {
                                            gc.setFill(Color.BLACK);
                                            gc.fillRect(width / 2 - 150 + (j / 2 - 1) * 30, height - 830 + (m - 4) * 30, 30, 30);
                                        }
                                    }
                                t++;
                            }
                        }
                        if (t == 1) {
                            MediaPlayer mediaPlayer2 = new MediaPlayer(media3);
                            if (f5) mediaPlayer2.play();
                        }
                        if (t == 2) {
                            MediaPlayer mediaPlayer2 = new MediaPlayer(media4);
                            if (f5) mediaPlayer2.play();
                        }
                        if (t == 3) {
                            MediaPlayer mediaPlayer2 = new MediaPlayer(media5);
                            if (f5) mediaPlayer2.play();
                        }
                        if (t == 4) {
                            MediaPlayer mediaPlayer2 = new MediaPlayer(media6);
                            if (f5) mediaPlayer2.play();
                        }
                        if (t >= 5) {
                            MediaPlayer mediaPlayer2 = new MediaPlayer(media7);
                            if (f5) mediaPlayer2.play();
                        }
                        sum = (int) (sum + Math.pow(2, t - 1) * t * 10 * 500 / dur);
                        text2.setText("" + sum);
                        if ((sum >= 10) & (sum < 100)) text2.setLayoutX(width / 2 + 190);
                        if ((sum >= 100) & (sum < 1000)) text2.setLayoutX(width / 2 + 180);
                        if ((sum >= 1000) & (sum < 10000)) text2.setLayoutX(width / 2 + 170);
                        for (int i = 0; i < 5; i++)
                            for (int j = 0; j < 9; j++) tetris1[i][j] = tetris3[i][j];
                        int k = (int) (Math.random() * 7);
                        for (int i = 0; i < 5; i++)
                            for (int j = 0; j < 9; j++) {
                                if (tetris3[i][j] == '*') {
                                    gc.setFill(Color.BLACK);
                                    gc.fillRect(width / 2 - 300 + (j / 2 - 1) * 30, height - 800 + (i) * 30, 30, 30);
                                }
                                tetris3[i][j] = tetrisAll[k][i][j];
                                if (tetris3[i][j] == '*') {
                                    gc.setFill(Color.RED);
                                    gc.fillRoundRect(width / 2 - 300 + (j / 2 - 1) * 30, height - 800 + (i) * 30, 30, 30, 10, 10);
                                }
                            }

                        lie = getLie(tetris1);
                        hang = getHang(tetris1);
                        f2 = false;
                        if ((lie % 4 == 1) & (hang % 2 == 0)) {
                            y = (lie - 1) / 2;
                            x = hang / 2;
                        }
                        if ((lie % 4 == 3) & (hang % 2 == 1)) {
                            x = (hang - 1) / 2;
                            y = (lie - 2) / 2;
                        }
                        if ((lie % 4 == 1) & (hang % 2 == 1)) {
                            y = (lie - 1) / 2;
                            x = hang / 2;
                        }
                        if ((lie % 4 == 3) & (hang % 2 == 0)) {
                            x = (hang - 1) / 2.0;
                            y = (lie - 1) / 2;
                            f2 = true;
                        }
                        if (!f2) {
                            b = 10;
                            a = 4 - (hang - x) + 1;
                        }
                        if (f2) {
                            b = 11;
                            a = 4 - (hang - x) + 1;
                        }
                    }
                    if ((b + lie - y - 1 < 22) & (b - y > 0))
                        for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                            for (int j = b - y; j <= b + lie - y - 1; j++) {
                                if ((arr[i][j] == '*') & (tetris1[(int) (i - (a - x))][j - b + y] == '*')) {
                                    f3 = true;
                                    MediaPlayer mediaPlayer2 = new MediaPlayer(media9);
                                    if (f5) mediaPlayer2.play();
                                    timeline.stop();
                                    text13.setText("你的得分为：" + sum);
                                    primaryStage.setScene(scene6);
                                    f = false;
                                    int i1 = -1;
                                    for (int i2 = 0; i2 < 10; i2++)
                                        if (ranks[mode][i2].equals(name)) {
                                            f = true;
                                            i1 = i2;
                                            break;
                                        }
                                    if (!f)
                                        if (sum > rank[mode][9]) {
                                            int i2 = 0;
                                            while (rank[mode][i2] >= sum) i2++;
                                            for (int j2 = 9; j2 > i2; j2--) {
                                                rank[mode][j2] = rank[mode][j2 - 1];
                                                ranks[mode][j2] = ranks[mode][j2 - 1];
                                            }
                                            rank[mode][i2] = sum;
                                            ranks[mode][i2] = name;
                                            for (int j2 = 0; j2 < 10; j2++)
                                                texts[mode][j2].setText(j2 + 1 + " " + ranks[mode][j2] + " " + rank[mode][j2]);

                                        }
                                    if (f)
                                        if (rank[mode][i1] < sum) {
                                            int i2 = 0;
                                            while (rank[mode][i2] >= sum) i2++;
                                            for (int j2 = i1; j2 > i2; j2--) {
                                                rank[j2] = rank[j2 - 1];
                                                ranks[j2] = ranks[j2 - 1];
                                            }
                                            rank[mode][i2] = sum;
                                            ranks[mode][i2] = name;
                                            for (int j2 = 0; j2 < 10; j2++)
                                                texts[mode][j2].setText(j2 + 1 + " " + ranks[mode][j2] + " " + rank[mode][j2]);
                                        }
                                    try {
                                        FileOutputStream fout = new FileOutputStream("./rank" + mode + ".txt");
                                        for (int i2 = 0; i2 < 10; i2++) {
                                            char[] c = ranks[mode][i2].toCharArray();
                                            for (int j2 = 0; j2 < c.length; j2++) fout.write(c[j2]);
                                            fout.write(13);
                                            fout.write(rank[mode][i2] / 100);
                                            fout.write(rank[mode][i2] % 100);
                                        }
                                        fout.close();
                                    } catch (FileNotFoundException e) {

                                    } catch (IOException e) {

                                    }
                                }
                                if (arr[i][j] == ' ') arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y];
                            }
                        }
                    if (f4) {
                        if (b + lie - y - 1 >= 22)
                            for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                                for (int j = b - y; j <= 20; j++)
                                    if (arr[i][j] == ' ') arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y];
                                for (int j = 2; j <= b + lie - y - 1 - 20; j++)
                                    if (arr[i][j] == ' ') arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y + 20];
                            }
                        if (b - y <= 0)
                            for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                                for (int j = 2; j <= b + lie - y - 1; j++)
                                    if (arr[i][j] == ' ') arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y];
                                for (int j = b - y + 20; j <= 20; j++)
                                    if (arr[i][j] == ' ') arr[i][j] = tetris1[(int) (i - (a - x))][j - b + y - 20];
                            }
                    }
                    x2 = 30;
                    if ((b + lie - y - 1 < 22) & (b - y > 0))
                        for (int j = b - y; j <= b + lie - y - 1; j++) {
                            int x1 = 0;
                            for (int i = (int) (a - x); i <= a + hang - x - 1; i++)
                                if ((arr[i][j] == '*') & (!f1[i][j])) x1 = i;
                            int i = (int) (a - x);
                            while (true) {
                                if ((arr[i][j] != ' ') & (f1[i][j]) & (i - 1 >= x1)) break;
                                i++;
                            }
                            i = i - 1;
                            if (i - x1 < x2) x2 = i - x1;
                        }
                    if (f4) {
                        if (b + lie - y - 1 >= 22) {
                            for (int j = b - y; j <= 20; j++) {
                                int x1 = 0;
                                for (int i = (int) (a - x); i <= a + hang - x - 1; i++)
                                    if ((arr[i][j] == '*') & (!f1[i][j])) x1 = i;
                                int i = (int) (a - x);
                                while (true) {
                                    if ((arr[i][j] != ' ') & (f1[i][j])) break;
                                    i++;
                                }
                                i = i - 1;
                                if (i - x1 < x2) x2 = i - x1;
                            }
                            for (int j = 2; j <= b + lie - y - 1 - 20; j++) {
                                int x1 = 0;
                                for (int i = (int) (a - x); i <= a + hang - x - 1; i++)
                                    if ((arr[i][j] == '*') & (!f1[i][j])) x1 = i;
                                int i = (int) (a - x);
                                while (true) {
                                    if ((arr[i][j] != ' ') & (f1[i][j])) break;
                                    i++;
                                }
                                i = i - 1;
                                if (i - x1 < x2) x2 = i - x1;
                            }
                        }
                        if (b - y <= 0) {
                            for (int j = 2; j <= b + lie - y - 1; j++) {
                                int x1 = 0;
                                for (int i = (int) (a - x); i <= a + hang - x - 1; i++)
                                    if ((arr[i][j] == '*') & (!f1[i][j])) x1 = i;
                                int i = (int) (a - x);
                                while (true) {
                                    if ((arr[i][j] != ' ') & (f1[i][j])) break;
                                    i++;
                                }
                                i = i - 1;
                                if (i - x1 < x2) x2 = i - x1;
                            }
                            for (int j = b - y + 20; j <= 20; j++) {
                                int x1 = 0;
                                for (int i = (int) (a - x); i <= a + hang - x - 1; i++)
                                    if ((arr[i][j] == '*') & (!f1[i][j])) x1 = i;
                                int i = (int) (a - x);
                                while (true) {
                                    if ((arr[i][j] != ' ') & (f1[i][j])) break;
                                    i++;
                                }
                                i = i - 1;
                                if (i - x1 < x2) x2 = i - x1;
                            }
                        }
                    }
                    if ((b + lie - y - 1 < 22) & (b - y > 0))
                        for (int i = (int) (a - x); i <= a + hang - x - 1; i++)
                            for (int j = b - y; j <= b + lie - y - 1; j++)
                                if ((arr[i + x2][j] == ' ') & (arr[i][j] == '*')) arr[i + x2][j] = '+';
                    if (f4) {
                        if (b + lie - y - 1 >= 22)
                            for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                                for (int j = b - y; j <= 20; j++)
                                    if ((arr[i + x2][j] == ' ') & (arr[i][j] == '*')) arr[i + x2][j] = '+';
                                for (int j = 2; j <= b + lie - y - 1 - 20; j++)
                                    if ((arr[i + x2][j] == ' ') & (arr[i][j] == '*')) arr[i + x2][j] = '+';

                            }
                        if (b - y <= 0)
                            for (int i = (int) (a - x); i <= a + hang - x - 1; i++) {
                                for (int j = 2; j <= b + lie - y - 1; j++)
                                    if ((arr[i + x2][j] == ' ') & (arr[i][j] == '*')) arr[i + x2][j] = '+';
                                for (int j = b - y + 20; j <= 20; j++)
                                    if ((arr[i + x2][j] == ' ') & (arr[i][j] == '*')) arr[i + x2][j] = '+';

                            }
                    }
                    for (int i = 4; i < 26; i++)
                        for (int j = 0; j < 23; j = j + 2) {
                            if ((arr[i][j] == '*') & (!f1[i][j])) {
                                gc.setFill(Color.RED);
                                gc.fillRoundRect(width / 2 - 150 + (j / 2 - 1) * 30, height - 830 + (i - 4) * 30, 30, 30, 10, 10);
                            }
                            if (arr[i][j] == '+') {
                                gc.setStroke(Color.RED);
                                gc.strokeRoundRect(width / 2 - 150 + (j / 2 - 1) * 30, height - 830 + (i - 4) * 30, 30, 30, 10, 10);
                            }
                            if ((arr[i][j] == '*') & (f1[i][j])) {
                                gc.setFill(Color.WHITE);
                                gc.fillRoundRect(width / 2 - 150 + (j / 2 - 1) * 30, height - 830 + (i - 4) * 30, 30, 30, 10, 10);
                            }
                        }
                }
            }
        });
        btn11.setPrefSize(500, 50);
        btn11.setLayoutX(width / 2 - 250);
        btn11.setLayoutY(height / 2 - 100);

        btn14.setLayoutX(width / 2 - 250);
        btn14.setLayoutY(height / 2);
        btn14.setPrefSize(500, 50);
        btn14.setStyle("-fx-background-color: black");
        btn14.setTextFill(Color.WHITE);
        btn14.setFont(Font.font("Verdana", 30));
        Button btn12 = new Button("游戏设置");
        btn12.setLayoutX(width / 2 - 250);
        btn12.setLayoutY(height / 2 + 100);
        btn12.setPrefSize(500, 50);
        btn12.setStyle("-fx-background-color: black");
        btn12.setTextFill(Color.WHITE);
        btn12.setFont(Font.font("Verdana", 30));
        Button btn13 = new Button("退出游戏");
        btn13.setLayoutX(width / 2 - 250);
        btn13.setLayoutY(height / 2 + 200);
        btn13.setPrefSize(500, 50);
        btn13.setStyle("-fx-background-color: black");
        btn13.setTextFill(Color.WHITE);
        btn13.setFont(Font.font("Verdana", 30));
        btn13.setOnAction(event -> {
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            if (f5) mediaPlayer2.play();
            System.exit(0);
        });
        btn12.setOnAction(event -> {
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            if (f5) mediaPlayer2.play();
            primaryStage.setScene(scene8);
        });
        pane.getChildren().addAll(text, btn11, btn12, btn13, btn14);
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        btn21.setPrefSize(500, 50);
        btn21.setLayoutX(width / 2 - 250);
        btn21.setLayoutY(height / 2);
        btn21.setStyle("-fx-background-color: black");
        btn21.setTextFill(Color.WHITE);
        btn21.setFont(Font.font("Verdana", 30));
        btn22.setPrefSize(500, 50);
        btn22.setLayoutX(width / 2 - 250);
        btn22.setLayoutY(height / 2 + 100);
        btn22.setStyle("-fx-background-color: black");
        btn22.setTextFill(Color.WHITE);
        btn22.setFont(Font.font("Verdana", 30));
        btn23.setLayoutX(width / 2 - 250);
        btn23.setLayoutY(height / 2 + 200);
        btn23.setPrefSize(500, 50);
        btn23.setStyle("-fx-background-color: black");
        btn23.setTextFill(Color.WHITE);
        btn23.setFont(Font.font("Verdana", 30));
        pane2.getChildren().addAll(btn21, btn22, btn23);
        pane2.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        btn31.setPrefSize(500, 50);
        btn31.setLayoutX(width / 2 - 250);
        btn31.setLayoutY(height / 2 - 200);
        btn31.setStyle("-fx-background-color: black");
        btn31.setTextFill(Color.WHITE);
        btn31.setFont(Font.font("Verdana", 30));
        btn32.setPrefSize(500, 50);
        btn32.setLayoutX(width / 2 - 250);
        btn32.setLayoutY(height / 2 - 100);
        btn32.setStyle("-fx-background-color: black");
        btn32.setTextFill(Color.WHITE);
        btn32.setFont(Font.font("Verdana", 30));
        btn33.setLayoutX(width / 2 - 250);
        btn33.setLayoutY(height / 2);
        btn33.setPrefSize(500, 50);
        btn33.setStyle("-fx-background-color: black");
        btn33.setTextFill(Color.WHITE);
        btn33.setFont(Font.font("Verdana", 30));
        btn34.setPrefSize(500, 50);
        btn34.setLayoutX(width / 2 - 250);
        btn34.setLayoutY(height / 2 + 100);
        btn34.setStyle("-fx-background-color: black");
        btn34.setTextFill(Color.WHITE);
        btn34.setFont(Font.font("Verdana", 30));
        btn35.setPrefSize(500, 50);
        btn35.setLayoutX(width / 2 - 250);
        btn35.setLayoutY(height / 2 + 200);
        btn35.setStyle("-fx-background-color: black");
        btn35.setTextFill(Color.WHITE);
        btn35.setFont(Font.font("Verdana", 30));
        btn36.setLayoutX(width / 2 - 250);
        btn36.setLayoutY(height / 2 + 300);
        btn36.setPrefSize(500, 50);
        btn36.setStyle("-fx-background-color: black");
        btn36.setTextFill(Color.WHITE);
        btn36.setFont(Font.font("Verdana", 30));
        pane3.getChildren().addAll(btn31, btn32, btn33, btn34, btn35, btn36);
        pane3.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        pane4.getChildren().addAll(text8, text9, text10, text11);
        text8.setFont(Font.font("Verdana", 60));
        text8.setFill(Color.WHITE);
        text8.setLayoutX(width / 2 - 100);
        text8.setLayoutY(height - 900);
        text9.setFont(Font.font("Verdana", 40));
        text9.setFill(Color.WHITE);
        text9.setLayoutX(width / 2 - 600);
        text9.setLayoutY(height - 800);
        text10.setFont(Font.font("Verdana", 40));
        text10.setFill(Color.WHITE);
        text10.setLayoutX(width / 2 - 50);
        text10.setLayoutY(height - 800);
        text11.setFont(Font.font("Verdana", 40));
        text11.setFill(Color.WHITE);
        text11.setLayoutX(width / 2 + 500);
        text11.setLayoutY(height - 800);
        pane5.getChildren().addAll(canvas1);
        pane5.getChildren().addAll(text6, text7);
        pane5.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        pane6.getChildren().addAll(text12, text13, btn41, btn42);
        text12.setFont(Font.font("Verdana", 60));
        text12.setFill(Color.RED);
        text12.setLayoutX(width / 2 - 200);
        text12.setLayoutY(height - 600);
        text13.setFont(Font.font("Verdana", 40));
        text13.setFill(Color.WHITE);
        text13.setLayoutX(width / 2 - 150);
        text13.setLayoutY(height - 400);
        pane7.getChildren().addAll(text14, text15, text16, text17, text18, text19, text20);
        pane8.getChildren().addAll(canvas2, text21, text22, text23, text24, btn51, btn52, btn53, btn54, btn55, btn56, btn57, btn58, btn59);
        for (int i = 0; i < 10; i++) {
            texts[0][i].setLayoutX(width / 2 - 600);
            texts[0][i].setLayoutY(height - 750 + i * 60);
            texts[0][i].setFont(Font.font("Verdana", 35));
            texts[0][i].setFill(Color.WHITE);
            pane4.getChildren().add(texts[0][i]);
        }
        for (int i = 0; i < 10; i++) {
            texts[1][i].setLayoutX(width / 2 - 50);
            texts[1][i].setLayoutY(height - 750 + i * 60);
            texts[1][i].setFont(Font.font("Verdana", 35));
            texts[1][i].setFill(Color.WHITE);
            pane4.getChildren().add(texts[1][i]);
        }
        for (int i = 0; i < 10; i++) {
            texts[2][i].setLayoutX(width / 2 + 500);
            texts[2][i].setLayoutY(height - 750 + i * 60);
            texts[2][i].setFont(Font.font("Verdana", 35));
            texts[2][i].setFill(Color.WHITE);
            pane4.getChildren().add(texts[2][i]);
        }
        text6.setFont(Font.font("Verdana", 40));
        text6.setFill(Color.WHITE);
        text6.setLayoutX(width / 2 - 200);
        text6.setLayoutY(height - 700);

        text7.setLayoutX(width / 2 - 200);
        text7.setLayoutY(height - 560);
        text7.setFont(Font.font("Verdana", 50));
        text7.setFill(Color.BLACK);

        primaryStage.setScene(scene);

        primaryStage.show();

    }


    public static int getHang(char a[][]) {
        int hang = 5;
        boolean f = true;
        while (f) {
            hang--;
            for (int i = 0; i < 9; i++)
                if (a[hang][i] == '*') {
                    f = false;
                    break;
                }
        }
        return hang + 1;
    }

    public static int getLie(char a[][]) {
        int lie = 9;
        boolean f = true;
        while (f) {
            lie--;
            for (int i = 0; i < 5; i++)
                if (a[i][lie] == '*') {
                    f = false;
                    break;
                }
        }
        return lie + 1;
    }

    public static void reset() {
        f3 = false;
        r = 0;
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);
        gc.setStroke(Color.WHITE);
        gc.strokeLine(width / 2 - 153, height - 830, width / 2 - 153, height - 197);
        gc.strokeLine(width / 2 - 153, height - 197, width / 2 + 153, height - 197);
        gc.strokeLine(width / 2 + 153, height - 830, width / 2 + 153, height - 197);
        sum = 0;
        time = 0;
        text4.setText("00:00");
        text2.setText("" + sum);
        int k = (int) (Math.random() * 7);
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 9; j++) {
                tetris3[i][j] = tetrisAll[k][i][j];
                if (tetris3[i][j] == '*') {
                    gc.setFill(Color.RED);
                    gc.fillRoundRect(width / 2 - 300 + (j / 2 - 1) * 30, height - 800 + (i) * 30, 30, 30, 10, 10);
                }
            }
        for (int i = 0; i < 26; i++) {
            for (int j = 1; j < 22; j++) {
                arr[i][j] = ' ';
                f1[i][j] = false;
            }
            arr[i][0] = arr[i][22] = '|';
            f1[i][0] = f1[i][22] = true;
        }
        arr[25][0] = arr[25][22] = ' ';
        for (int i = 1; i < 22; i++) {
            arr[25][i] = '-';
            f1[25][i] = true;
        }
        k = (int) (Math.random() * 7);
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 9; j++) tetris1[i][j] = tetrisAll[k][i][j];
        lie = getLie(tetris1);
        hang = getHang(tetris1);
        f2 = false;
        if ((lie % 4 == 1) & (hang % 2 == 0)) {
            y = (lie - 1) / 2;
            x = hang / 2;
        }
        if ((lie % 4 == 3) & (hang % 2 == 1)) {
            x = (hang - 1) / 2;
            y = (lie - 2) / 2;
        }
        if ((lie % 4 == 1) & (hang % 2 == 1)) {
            y = (lie - 1) / 2;
            x = hang / 2;
        }
        if ((lie % 4 == 3) & (hang % 2 == 0)) {
            x = (hang - 1) / 2.0;
            y = (lie - 1) / 2;
            f2 = true;
        }
        if (!f2) {
            b = 10;
            a = 4 - (hang - x) + 1;
        } else {
            b = 11;
            a = 4 - (hang - x) + 1;
        }
        for (k = 0; k < 4; k++)
            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 9; j++)
                    if (tetrisAll[k][i][j] == '*') {
                        gc.setFill(Color.WHITE);
                        gc.fillRoundRect(width / 2 - 500 + (j / 2 - 1) * 30, height - 800 + (i) * 30 + k * 200, 30, 30, 10, 10);
                    }
        for (k = 4; k < 7; k++)
            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 9; j++)
                    if (tetrisAll[k][i][j] == '*') {
                        gc.setFill(Color.WHITE);
                        gc.fillRoundRect(width / 2 + 500 + (j / 2 - 1) * 30, height - 800 + (i) * 30 + (k - 4) * 200, 30, 30, 10, 10);
                    }

    }

    public static void start(char tetris3[][], int j, int k) {
        if (j < 0 || j > 4 || k < 0 || k > 8) return;
        if (tetris3[j][k] == ' ') return;
        if (vis[j][k] == 1) return;
        if (tetris3[j][k] == '*' && vis[j][k] == 0) {
            count++;
            vis[j][k] = 1;
        }
        for (int i = 0; i < 4; i++) {
            j += mx[i];
            k += my[i];
            start(tetris3, j, k);
            j -= mx[i];
            k -= my[i];
        }
    }
}
