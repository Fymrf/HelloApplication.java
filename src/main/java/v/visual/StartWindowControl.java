package v.visual;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class StartWindowControl {
    @FXML
    private javafx.scene.control.Button closeButton;
    @FXML
    private TableView<Lesson> lessonsTable;
    @FXML
    private TableColumn<Lesson,String> nameColumn;
    @FXML
    private TableColumn<Lesson,String> descrtiptionColumn;

    @FXML
    public void initialize(){
        nameColumn.setCellValueFactory(cellData->cellData.getValue().nameProperty());
        descrtiptionColumn.setCellValueFactory(cellData->cellData.getValue().descrProperty());
        ObservableList<Lesson> list = FXCollections.observableArrayList();
        list.add(new Lesson("Название занятия1","Очень детельное описание занятия1..........."));
        list.add(new Lesson("Название занятия2","Очень детельное описание занятия2..........."));
        list.add(new Lesson("Название занятия3","Очень детельное описание занятия3..........."));
        lessonsTable.setItems(list);
    }

    @FXML
    protected void newLes() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(StartWindow.class.getResource("createNewTrain.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setMinWidth(700);
        stage.setMinHeight(400);
        stage.setTitle("Создание нового занятия");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void startNewLes() throws IOException {
        Stage oldStage = (Stage) closeButton.getScene().getWindow();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(StartWindow.class.getResource("startTrain.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), oldStage.getWidth(), oldStage.getHeight());
        stage.setMinWidth(700);
        stage.setMinHeight(400);
        stage.setTitle("Начало занятия");
        stage.setScene(scene);
        stage.show();
        oldStage.close();
    }
}
