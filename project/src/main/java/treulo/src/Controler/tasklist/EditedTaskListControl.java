package treulo.src.Controler.tasklist;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import treulo.src.model.Model;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;

//Controlleur chargé de mettre à jour la liste éditer
//Handler d'événement (mousehover de la liste, onMouseOver)
//Créée par : Tom
public class EditedTaskListControl implements EventHandler<MouseEvent> {

    Treulo model;
    TaskList tL;

    public EditedTaskListControl(Model m, TaskList tl)
    {
        model=(Treulo) m;
        tL=tl;
    }
    
    @Override
    public void handle(MouseEvent mouseEvent) {
        model.setEditedTaskList(tL);
    }
}