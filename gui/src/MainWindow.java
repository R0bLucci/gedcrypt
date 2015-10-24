/**
 * Created by Robert on 12/10/2015.
 */

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Observable;

public class MainWindow extends Application {
    Scene scene;
    Stage window;
    Button btnEncrypt;
    Button btnRemoveEn;
    Button btnDecrypt;
    Button btnRemoveDe;
    GridPane layout;
    ListView<String> eFiles;
    ListView<String> dFiles;
    Label lblEn;
    Label lblDe;
    FileChooser fileChooser;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Encryption App");
        window.setResizable(false); // window is not resizable
        layout = new GridPane();
        lblEn = new Label("File(s) to encrypt:");
        lblDe = new Label("File(s) to decrypt:");
        btnEncrypt = new Button("Encrypt");
        btnRemoveEn = new Button("Remove");
        btnDecrypt = new Button("Decrypt");
        btnRemoveDe = new Button("Remove");

        layout.setPadding(new Insets(12, 20, 12, 20)); // set padding for right, bottom, left, top
        eFiles = new ListView<String>();
        dFiles = new ListView<String>();

        btnEncrypt.setOnAction( e -> {
            fileChooser = new FileChooser();
            fileChooser.setTitle("Files to encrypt");
            File selectedFile = fileChooser.showOpenDialog(window);
            if(selectedFile != null){
                eFiles.getItems().add(selectedFile.toString());
            }
            //Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
            //dialog.showAndWait();
        });

        btnDecrypt.setOnAction( e -> {
            fileChooser = new FileChooser();
            fileChooser.setTitle("Files to decrypt");
            File selectedFile = fileChooser.showOpenDialog(window);
            if(selectedFile != null){
                dFiles.getItems().add(selectedFile.toString());
            }
            //Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
            //dialog.showAndWait();
        });

        btnRemoveEn.setOnAction( e->{
            ObservableList<String> allItems, selectedItems;
            allItems= eFiles.getItems();
            selectedItems = eFiles.getSelectionModel().getSelectedItems();

            selectedItems.forEach(allItems::remove);
        });

        btnRemoveDe.setOnAction( e->{
            ObservableList<String> allItems, selectedItems;
            allItems= dFiles.getItems();
            selectedItems = dFiles.getSelectionModel().getSelectedItems();

            selectedItems.forEach(allItems::remove);
        });

        layout.getChildren().addAll(lblEn, lblDe,btnEncrypt, btnDecrypt, btnRemoveEn, btnRemoveDe, eFiles, dFiles);
        layout.setVgap(5);
        layout.setHgap(5);
        GridPane.setConstraints(lblEn, 0 , 0);
        GridPane.setConstraints(lblDe, 2, 0);
        GridPane.setConstraints(eFiles, 0, 1);
        GridPane.setConstraints(dFiles, 2 , 1);
        GridPane.setConstraints(btnEncrypt, 0 , 2);
        GridPane.setConstraints(btnRemoveEn, 0, 3);
        GridPane.setConstraints(btnDecrypt, 2 , 2);
        GridPane.setConstraints(btnRemoveDe, 2, 3);
        scene = new Scene(layout, 500, 400);
        window.setScene(scene);
        window.show();
    }
}
