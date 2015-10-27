/**
 * Created by Robert on 12/10/2015.
 */

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;


public class MainWindow extends Application {

    Stage window;
    Button btnEncrypt;
    Button btnDecrypt;
    Button btnRemoveEn;
    Button btnRemoveDe;
    ListView<String> eFiles;
    ListView<String> dFiles;
    FileChooser fileChooser;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Encryption App");
        window.setResizable(false); // window is not resizable
        GridPane layout = new GridPane();
        Label lblEn = new Label("File(s) encrypted:");
        Label lblDe = new Label("File(s) decrypted:");
        btnEncrypt = new Button("Encrypt");
        btnDecrypt = new Button("Decrypt");
        btnRemoveEn = new Button("Remove");
        btnRemoveDe = new Button("Remove");

        // list controls where selected files will be displayed
        eFiles = new ListView<>();
        dFiles = new ListView<>();

        eFiles.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        dFiles.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        HBox hBox = new HBox(layout);
        hBox.getChildren().addAll(btnEncrypt, btnRemoveEn, btnDecrypt, btnRemoveDe);
        hBox.setSpacing(50);
        hBox.setPadding(new Insets(5, 10, 10, 10));
        hBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(layout, hBox);
        //set padding top - right - bottom - left for the grid pane
        layout.setPadding(new Insets(12, 20, 12, 20));
        GridPane.setConstraints(lblEn, 0, 0);
        GridPane.setConstraints(lblDe, 2, 0);
        GridPane.setConstraints(eFiles, 0, 1);
        GridPane.setConstraints(dFiles, 2 , 1);

        btnEncrypt.setOnAction( e -> {
            runHashAlgorithm(eFiles, "Choose files to encrypt", "-e");
        });

        btnRemoveEn.setOnAction( e->{
            remove(eFiles);
        });

        btnDecrypt.setOnAction( e -> {
            runHashAlgorithm(dFiles, "Choose files to decrypt", "-d");
        });

        btnRemoveDe.setOnAction( e->{
            remove(dFiles);
        });

        // Add labels and list view to the grid pane layout
        layout.getChildren().addAll(lblEn, lblDe, eFiles, dFiles);
        layout.setVgap(5); // set vertical margin of 5px
        layout.setHgap(1); // set horizontal margin of 1 px

        //set the scene to the stage and show the app
        Scene scene = new Scene(vBox, 500, 400);
        window.setScene(scene);
        window.show();
    }

    void runHashAlgorithm(ListView<String> lv, String title, String option){
        fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        // Set the FileChooser to be able to select get multiple selection
        List<File> selection = fileChooser.showOpenMultipleDialog(window);
        if(selection != null){
            int size = selection.size();
            String[] filename = new String[size];
            //insert each file selected to the filename array
            for(int i = 0; i < size; i++) {
                filename[i] = selection.get(i).toString();
                lv.getItems().add(filename[i]);
            }
            // Open dialog to get password from user
            String userPass = Password.getPassword();

            if(userPass != null) {
                // Call the python parser to run the script
                PythonParser pp = PythonParser.getPythonParser();
                //run the python parser for each item in the filename array
                for(int i = 0; i < size; i++){
                    pp.hash(option, filename[i], userPass);
                }
                // Show user action was completed successfully
                showActionComplete(option);
            }else{
                // remove all items from the ListView
                remove(lv);
            }
        }
    }

    void remove(ListView<String> lv){
        ObservableList<String> items = lv.getItems();
        if(items != null)
            items.clear();
    }

    void showActionComplete(String option) {
        Alert completed = new Alert(Alert.AlertType.INFORMATION);
        completed.setTitle("Operation successful");
        String msg = (option == "-e") ? "encryption" : "decryption";
        completed.setContentText("The " + msg + " has been completed");
        completed.showAndWait();
    }
}
