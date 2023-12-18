package treulo.src.view.appview.display;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import treulo.src.Controler.EditTaskListControl;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

import java.util.LinkedList;

public class DeskDisplay implements Display {

    private LinkedList<TaskList> taskLists;
    private Treulo model;


    public DeskDisplay(Treulo treulo, LinkedList<TaskList> taskLists) {
        this.model = treulo;
        this.taskLists = taskLists;
    }

    @Override
    public Node getDisplay() {
        HBox hBox = new HBox();

        for(TaskList taskList : taskLists) {
            hBox.getChildren().add(getTaskListDisplay(taskList));
        }

        

        return hBox;
    }

    public Node getTaskListDisplay(TaskList taskList) {
        VBox vBox = new VBox();
        TextField listName = new TextField(taskList.getName());
        listName.setOnAction(new EditTaskListControl(model, taskList));

        for(TreuloTask task : taskList) {
            listVbox.getChildren().add(getTaskDisplay(task, new VBox()));
        }
        return null;
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
