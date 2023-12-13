package treulo.src.view.appview.display;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import treulo.src.model.TreuloTask;

import java.util.ArrayList;

public class TextDisplay implements Display {

    private ArrayList<TaskList> taskLists;

    public TextDisplay(ArrayList<TaskList> taskLists) {
        this.taskLists = taskLists;
    }

    @Override
    public Node getDisplay() {
        String res = "";

        for(TaskList taskList : taskLists) {
            res += "- " + taskList.getName() + " : \n";
            for(TreuloTask task : taskList) {
                res += getTaskDisplay(task, new TextArea());
            }
        }

        TextArea textArea = new TextArea(res);
        textArea.setEditable(false);
        return textArea;
    }

    @Override
    public Node getTaskDisplay(TreuloTask task, Node parentNode) {
        TextArea textArea = (TextArea)parentNode;
        String res = textArea.getText();

        res += "- " + task.getName() + "(" + task.getDescription() + ")";
        for(TreuloTask child : task.getSubtasks()) {
            String childText = ((TextArea)getTaskDisplay(child, new TextArea())).getText();
            res += " " + childText;
        }
        return
    }
}
