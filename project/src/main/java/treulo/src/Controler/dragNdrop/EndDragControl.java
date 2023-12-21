package treulo.src.Controler.dragNdrop;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.*;
import treulo.src.model.Model;

public class EndDragControl implements EventHandler<DragEvent> {

    Model model;
    Node task;

    public EndDragControl(Model m, Node task) {
        this.task = task;
        model=m;
    }

    @Override
    public void handle(DragEvent event) {
        task.setVisible(true);
    }
}
