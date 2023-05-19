package v.visual;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateNewTrain {

    @FXML
    protected void save() throws IOException {

    }

    @FXML private javafx.scene.control.Button clos;
    @FXML
    protected void back()  {
        Stage oldStage = (Stage) clos.getScene().getWindow();
        oldStage.close();
    }
}
