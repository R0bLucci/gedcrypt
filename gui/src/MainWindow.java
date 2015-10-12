/**
 * Created by Robert on 12/10/2015.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainWindow extends Application {
    Scene scene;
    Button btnEncrypt;
    Button btnDecrypt;
    GridPane layout;
    ListView<String> eFiles;
    ListView<String> dFiles;
    Label lblEn;
    Label lblDe;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) {
        window = new Stage();
        window.setTitle("Encryption App");
        window.setResizable(false); // window is not resizable
        layout = new GridPane();
        lblEn = new Label("File(s) to encrypt:");
        lblDe = new Label("File(s) to decrypt");
        btnEncrypt = new Button("Encrypt");
        btnDecrypt = new Button("Decrypt");
        layout.setPadding(new Insets(12, 20, 12, 20)); // set padding for right, bottom, left, top
        eFiles = new ListView<String>();
        dFiles = new ListView<String>();
        btnEncrypt.setOnAction( e -> {
            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
            dialog.showAndWait();
        });
        btnDecrypt.setOnAction( e -> {
            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
            dialog.showAndWait();
        });
        layout.getChildren().addAll(lblEn, lblDe, btnEncrypt, btnDecrypt, eFiles, dFiles);
        layout.setVgap(5);
        layout.setHgap(5);
        GridPane.setConstraints(lblEn, 0 , 0);
        GridPane.setConstraints(lblDe, 2, 0);
        GridPane.setConstraints(eFiles, 0 , 1);
        GridPane.setConstraints(dFiles, 2 , 1);
        GridPane.setConstraints(btnEncrypt, 0 , 2);
        GridPane.setConstraints(btnDecrypt, 2 , 2);
        scene = new Scene(layout, 500, 400);
        window.setScene(scene);
        window.show();
    }
}
