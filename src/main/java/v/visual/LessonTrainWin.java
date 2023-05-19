package v.visual;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class LessonTrainWin {
    @FXML
    private Button closeButtonB;
    @FXML
    private TableView<NowProcedure> nowProcedure ;
    @FXML
    private TableColumn<NowProcedure,String> N;
    @FXML
    private TableColumn<NowProcedure,String> action;
    @FXML
    private TableColumn<NowProcedure,String> time;
    @FXML
    private TableColumn<NowProcedure,String> point;
    @FXML
    private Button recordScreen;
    @FXML
    private Button recordAction;
    @FXML
    private RadioButton radioButAct;
    @FXML
    private RadioButton radioButScr;

    @FXML
    protected void back() throws IOException {
        Stage oldStage = (Stage) closeButtonB.getScene().getWindow();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(StartWindow.class.getResource("startWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), oldStage.getWidth(),oldStage.getHeight());
        stage.setMinWidth(700);
        stage.setMinHeight(400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        oldStage.close();
    }

    private Timeline timeline;

    @FXML
    public void initialize(){
        N.setCellValueFactory(cellData->cellData.getValue().NProperty());
        action.setCellValueFactory(cellData->cellData.getValue().actionProperty());
        time.setCellValueFactory(cellData->cellData.getValue().timeProperty());
        point.setCellValueFactory(cellData->cellData.getValue().pointProperty());
        ObservableList<NowProcedure> list = FXCollections.observableArrayList();
        list.add(new NowProcedure("1","описание действия1 ........","33","0"));
        list.add(new NowProcedure("2","описание действия2 ........","22","1"));
        list.add(new NowProcedure("3","описание действия3 ........","11","2"));
        nowProcedure.setItems(list);

        if(recordAction.getText().equals("Остановить запись занятия")){
            recordAction.setStyle("-fx-text-fill: red;");
            radioButAct.setVisible(true);

            ToggleGroup toggleGroup = new ToggleGroup();
            radioButAct.setToggleGroup(toggleGroup);

            timeline = new Timeline(new KeyFrame(Duration.seconds(0.9), event -> {
                if (radioButAct.isSelected()) {
                    radioButAct.setSelected(false);
                } else {
                    radioButAct.setSelected(true);
                }
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }
        else {
            recordAction.setStyle("-fx-text-fill: green;");
            radioButAct.setVisible(false);
        }
    }

    public void setButtonName(String name) {
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButScr.setToggleGroup(toggleGroup);
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.9), event -> {
            if (radioButScr.isSelected()) {
                radioButScr.setSelected(false);
            } else {
                radioButScr.setSelected(true);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        recordScreen.setText(name);
        if(recordScreen.getText().equals("Остановить запись экрана")){
            recordScreen.setStyle("-fx-text-fill: red;");
            radioButScr.setVisible(true);
        }
        else {
            recordScreen.setStyle("-fx-text-fill: green;");
            radioButScr.setVisible(false);
        }
    }

    private VideoRec vr;
        public void setVr(VideoRec vr){
            this.vr = vr;
        }

    @FXML
    public void recScreen() throws IOException {

        if(recordScreen.getText().equals("Остановить запись экрана")){
            recordScreen.setText("Начать запись экрана");
            recordScreen.setStyle("-fx-text-fill: green;");
            radioButScr.setVisible(false);
            vr.stopRecording();
        }
        else {
            recordScreen.setText("Остановить запись экрана");
            recordScreen.setStyle("-fx-text-fill: red;");
            radioButScr.setVisible(true);
            vr.startRecording();
        }
    }

    @FXML
    public void recAction(){
        if(recordAction.getText().equals("Остановить запись занятия")){
            recordAction.setText("Начать запись занятия");
            recordAction.setStyle("-fx-text-fill: green;");
            radioButAct.setVisible(false);
        }
        else {
            recordAction.setText("Остановить запись занятия");
            recordAction.setStyle("-fx-text-fill: red;");
            radioButAct.setVisible(true);
        }
    }
}
