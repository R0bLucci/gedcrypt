import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Created by robert on 25/10/15.
 */
public class Password {

    static String pass = null;
    static PasswordField passwordField1;
    static PasswordField passwordField2;
    static Label error;
    static Stage stage;

    public static String getPassword()
    {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        passwordField1 = new PasswordField();
        Label label1 = new Label("Please type a password");
        passwordField1.setPromptText("Type your password...");
        Label label2 = new Label("Retype password");
        passwordField2 = new PasswordField();
        passwordField2.setPromptText("Confirm password");
        Button btnDone = new Button("Done");
        error = new Label();
        error.setTextFill(Color.web("#ff1243"));
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(5);
        vBox.getChildren().addAll(error, label1, passwordField1, label2, passwordField2, btnDone);
        Scene scene = new Scene(vBox, 300, 200);
        stage.setScene(scene);

        btnDone.setOnAction( e -> {
            askPassword();
        });
        stage.showAndWait();

        return pass;
    }

    static void askPassword(){
        if(passwordField1.getText().isEmpty()) {
            error.setText("Password was not provided");
        } else if(!passwordField1.getText().equals(passwordField2.getText())) {
            error.setText("Passwords provided do not match");
        }else{
            pass = passwordField1.getText();
            stage.close();
        }
    }
}
