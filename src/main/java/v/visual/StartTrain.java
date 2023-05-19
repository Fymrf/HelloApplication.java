package v.visual;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;

public class StartTrain {
    @FXML private javafx.scene.control.Button closeButtonNew;
    @FXML private javafx.scene.control.Button closeButtonBack;
    @FXML private CheckBox yes;
    @FXML private CheckBox no;

    @FXML
    protected void start() throws IOException {
        Stage oldStage = (Stage) closeButtonNew.getScene().getWindow();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(StartWindow.class.getResource("lessonTrainWin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), oldStage.getWidth(),oldStage.getHeight());
        LessonTrainWin lessonTrainWin = fxmlLoader.getController();

        stage.setMinWidth(700);
        stage.setMinHeight(400);
        stage.setTitle("Название занятия");
        stage.setScene(scene);
        VideoRec view = new VideoRec(lessonTrainWin);
        lessonTrainWin.setVr(view);

        if (!yes.isSelected() && !no.isSelected()) {

        }
        else if (yes.isSelected())
        {
            lessonTrainWin.setButtonName("Остановить запись экрана");
            view.startRecording();
            stage.show();
            oldStage.close();
        } else {
            lessonTrainWin.setButtonName("Начать запись экрана");
            stage.show();
            oldStage.close();
        }
    }

    @FXML
    protected void back() throws IOException {
        Stage oldStage = (Stage) closeButtonBack.getScene().getWindow();
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

    @FXML
    protected  void noCheck() {
        if (no.isSelected()) {
            yes.setSelected(false);
        }
    }

    @FXML
    protected  void yesCheck() {
        if (yes.isSelected()) {
            no.setSelected(false);
        }
    }
}
