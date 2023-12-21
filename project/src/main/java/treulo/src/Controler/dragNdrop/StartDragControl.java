package treulo.src.Controler.dragNdrop;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import treulo.src.model.Model;
import treulo.src.model.TreuloTask;

public class StartDragControl implements EventHandler<MouseEvent> {

    Model model;
    TreuloTask treuloTask;

    public StartDragControl(Model m, TreuloTask treuloTask) {
        model=m;
        this.treuloTask = treuloTask;
    }

    @Override
    public void handle(MouseEvent event) {
        Node draggedNode = (Node)event.getSource();
        Dragboard db = draggedNode.startDragAndDrop(TransferMode.ANY);

        Image dragImage = draggedNode.snapshot(null, null);

        ClipboardContent content = new ClipboardContent();
        content.putImage(dragImage);
        content.putString(treuloTask.getId() + "");
        db.setContent(content);
        draggedNode.setVisible(false);
        event.consume();

        System.out.println(treuloTask.getId());
    }
}
