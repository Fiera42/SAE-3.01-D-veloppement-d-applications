package treulo.src.Controler.dragNdrop;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import treulo.src.model.Model;
import treulo.src.model.TaskList;
import treulo.src.model.TreuloTask;


//Classe chargée de définir si oui ou non un composant peut être ciblé par le drop du drag and drop
//Handler d'événement (OnDragOver)
//Créée par : Adrien
public class DragOverControl implements EventHandler<DragEvent> {

    private Model model;

    //Assigné pour les liste, null sinon
    private TaskList taskList;
    //Assigné pour les tâches, null sinon
    private TreuloTask treuloTask;

    public DragOverControl(Model m, TaskList taskList) {
        this.taskList = taskList;
        model=m;
    }

    public DragOverControl(Model m, TreuloTask treuloTask) {
        this.treuloTask = treuloTask;
        model=m;
    }

    @Override
    public void handle(DragEvent event) {
        Dragboard dragboard = event.getDragboard();

        //Compliqué pour rien, durant le développement il fallait des autorisations différentes
        //pour les différents composant, mais ce n'est plus le cas et ils ont tous la même chose
        if(dragboard.hasString()) {
            String[] info = dragboard.getString().split(" ");

            if (treuloTask != null) {
                taskHandle(event, info);
            } else {
                taskListHandle(event, info);
            }
        }
        event.consume();
    }

    //Règles de dragOver pour les tâches
    public void taskHandle(DragEvent event, String[] info) {
        switch (info[0]) {
            case "task" :
            case "list" :
                event.acceptTransferModes(TransferMode.ANY);
                break;
            default:
        }
    }

    //Règles de dragOver pour les listes
    public void taskListHandle(DragEvent event, String[] info) {
        switch (info[0]) {
            case "task" :
            case "list" :
                event.acceptTransferModes(TransferMode.ANY);
                break;
            default:
        }
    }
}
