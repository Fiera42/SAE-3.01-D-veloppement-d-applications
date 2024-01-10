package treulo.src.Controler.dragNdrop;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import treulo.src.model.Model;
import treulo.src.model.TaskList;
import treulo.src.model.TreuloTask;

//Classe chargée de débuté l'action de drag and drop
//Handler d'événement (OnDragDetected)
//Créée par : Adrien
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
        //active le drag and drop
        Node draggedNode = (Node)event.getSource();
        Dragboard db = draggedNode.startDragAndDrop(TransferMode.ANY);

        //génération de l'image de drag and drop
        Image dragImage = draggedNode.snapshot(null, null);
        ClipboardContent content = new ClipboardContent();
        content.putImage(dragImage);

        //Génére une signature pour reconnaître l'élément qui est dragged
        if(treuloTask != null) {
            content.putString("task " + treuloTask.getId());
        }

        else {
            content.putString("list " + taskList.getId());
        }

        //commit
        db.setContent(content);
        draggedNode.setVisible(false);
        event.consume();
    }
}
