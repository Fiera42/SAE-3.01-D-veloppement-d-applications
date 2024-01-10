package treulo.src.view.appview.display;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import treulo.src.model.TaskList;
import treulo.src.model.TreuloTask;

import java.util.ArrayList;
import java.util.LinkedList;

//Affichage par défaut sous forme de texte brute
//Classe : Adrien
public class TextDisplay implements Display {

    private LinkedList<TaskList> taskLists;

    public TextDisplay(LinkedList<TaskList> taskLists) {
        this.taskLists = taskLists;
    }

    @Override
    public Node getDisplay() {
        VBox vBox = new VBox();

        for(TaskList taskList : taskLists) {
            VBox listVbox = new VBox(new Label("-" + taskList.getName()));
            for(TreuloTask task : taskList) {
                listVbox.getChildren().add(getTaskDisplay(task, new VBox()));
            }
            vBox.getChildren().add(listVbox);
        }

        return vBox;
    }

    @Override
    public Node getTaskDisplay(TreuloTask task, Node parentNode) {
        VBox parent = (VBox)parentNode;
        VBox vBox = new VBox();

        Insets insets = parent.getPadding();
        vBox.setPadding(new Insets(insets.getTop(), insets.getRight(), insets.getBottom(), insets.getLeft() + 5));

        vBox.getChildren().add(new Label("-" + task.getName() + " (" + task.getDescription() + ")"));
        for(TreuloTask child : task.getSubtasks()) {
            vBox.getChildren().add(getTaskDisplay(child, vBox));
        }

        return vBox;
    }
}
