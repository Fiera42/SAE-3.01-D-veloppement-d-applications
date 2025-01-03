package treulo.src.Controler.tasklist;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import treulo.src.model.Model;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;

//Controlleur chargé de supprimé une liste de tâche
//Handler d'événement (bouton de supression de liste, onAction)
//Créée par : Doryann
public class DeleteTaskListControl implements EventHandler<ActionEvent> {
    Treulo model;
    TaskList taskList;

    public DeleteTaskListControl(Model m , TaskList tl)
    {
        model= (Treulo) m;
        this.taskList = tl ;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        taskList.destroy();
    }
}
