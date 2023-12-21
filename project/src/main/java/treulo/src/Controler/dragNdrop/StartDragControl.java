package treulo.src.Controler.dragNdrop;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import treulo.src.model.Model;
import treulo.src.model.TaskList;
import treulo.src.model.TreuloTask;

public class StartDragControl implements EventHandler<MouseEvent> {

    private Model model;
    private TaskList taskList;
    private TreuloTask treuloTask;

    public StartDragControl(Model m, TaskList taskList) {
        this.taskList = taskList;
        model=m;
    }

    public StartDragControl(Model m, TreuloTask treuloTask) {
        this.treuloTask = treuloTask;
        model=m;
    }

    @Override
    public void handle(MouseEvent event) {
        Node draggedNode = (Node)event.getSource();
        Dragboard db = draggedNode.startDragAndDrop(TransferMode.ANY);

        Image dragImage = draggedNode.snapshot(null, null);

        ClipboardContent content = new ClipboardContent();
        content.putImage(dragImage);
        if(treuloTask != null) {
            content.putString("task " + treuloTask.getId());
        }

        else {
            content.putString("list " + taskList.getId());
        }

        db.setContent(content);
        draggedNode.setVisible(false);
        event.consume();
    }
}
