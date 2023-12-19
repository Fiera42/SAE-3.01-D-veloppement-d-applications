package treulo.src.Controler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import treulo.src.model.Model;
import treulo.src.model.Treulo;

public class AddTaskDependencyControl implements EventHandler<ActionEvent> {
    Treulo model;
    TextField collaborator;

    public AddTaskDependencyControl(Model m, TextField t){
        model=(Treulo) m;
        collaborator=t;
    }


    @Override
    public void handle(ActionEvent event) {
        model.addDependencyTempo(collaborator.getText());
        collaborator.setText("");
    }
}
