package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;


/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setMinHeight(Region.USE_PREF_SIZE);
        displayPicture.setPreserveRatio(true);
        displayPicture.setImage(img);
        Circle circle = new Circle(30, 30, 30);
        displayPicture.setClip(circle);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {

        DialogBox db = new DialogBox(text, img);
        return setDialogBoxBG(db, Color.web("#e6d7ff"));
    }

    public static DialogBox getResponseDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return setDialogBoxBG(db, Color.web("#FFF8DC"));
    }

    private static DialogBox setDialogBoxBG(DialogBox db, Paint color) {
        BackgroundFill fill = new BackgroundFill(color, new CornerRadii(5.0),
                new Insets(5.0));
        Background bg = new Background(fill);
        db.setBackground(bg);
        return db;
    }
}
