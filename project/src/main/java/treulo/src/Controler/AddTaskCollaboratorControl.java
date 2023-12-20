package treulo.src.Controler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import treulo.src.model.Model;
import treulo.src.model.Treulo;

import java.util.Objects;

public class AddTaskCollaboratorControl implements EventHandler<ActionEvent> {
    Treulo model;
    TextField collaborator;

    public AddTaskCollaboratorControl (Model m,TextField t){
        model=(Treulo) m;
        collaborator=t;
    }


    @Override
    public void handle(ActionEvent event) {
        if (!Objects.equals(collaborator.getText(), "")){
        model.addCollaboratorTempo(collaborator.getText());
        collaborator.setText("");
        }
    }
}
