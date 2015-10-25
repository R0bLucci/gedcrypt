/**
 * Created by Robert on 12/10/2015.
 */

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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

        // list controls where selected files will be displayed
        eFiles = new ListView<String>();
        dFiles = new ListView<String>();

        //set padding top - right - bottom - left for the grid pane
        layout.setPadding(new Insets(12, 20, 12, 20));
        GridPane.setConstraints(lblEn, 0, 0);
        GridPane.setConstraints(lblDe, 2, 0);
        GridPane.setConstraints(eFiles, 0, 1);
        GridPane.setConstraints(dFiles, 2 , 1);

        btnEncrypt.setOnAction( e -> {
            //TODO
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
            //TODO
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
            //TODO
            ObservableList<String> allItems, selectedItems;
            allItems= eFiles.getItems();
            selectedItems = eFiles.getSelectionModel().getSelectedItems();

            selectedItems.forEach(allItems::remove);
        });

        btnRemoveDe.setOnAction( e->{
            //TODO
            ObservableList<String> allItems, selectedItems;
            allItems= dFiles.getItems();
            selectedItems = dFiles.getSelectionModel().getSelectedItems();

            selectedItems.forEach(allItems::remove);
        });

        // Add labels and list view to the grid pane layout
        layout.getChildren().addAll(lblEn, lblDe, eFiles, dFiles);
        layout.setVgap(5); // set vertical margin of 5px
        layout.setHgap(1); // set horizontal margin of 1 px

        //Horizontal layout for the 4 action buttons
        HBox hBox = new HBox(btnEncrypt, btnRemoveEn, btnDecrypt, btnRemoveDe);
        hBox.setSpacing(40);// space buttons by 40px
        hBox.setAlignment(Pos.BOTTOM_CENTER);

        //vertical parent layout containing the grid pane and the horizontal layout
        VBox vBox = new VBox();
        vBox.getChildren().addAll(layout, hBox);
        vBox.setPadding(new Insets(10 , 10 , 10 , 10));

        //set the scene to the stage and show the app
        scene = new Scene(vBox, 500, 400);
        window.setScene(scene);
        window.show();
    }
}
