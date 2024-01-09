package treulo.src.Controler.tasklist;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

public class ArchiveTaskListControl implements EventHandler<ActionEvent> {
    private Treulo model;
    private TaskList taskList;

    public ArchiveTaskListControl(Treulo model, TaskList taskList) {
        this.model = model;
        this.taskList = taskList;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if(!(source instanceof CheckBox)) return;

        CheckBox checkBox = (CheckBox) source;
        taskList.setArchived(checkBox.isSelected());
    }
}
