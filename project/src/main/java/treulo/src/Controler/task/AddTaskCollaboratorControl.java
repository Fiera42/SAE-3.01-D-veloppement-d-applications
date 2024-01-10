package treulo.src.Controler.task;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import treulo.src.model.Model;
import treulo.src.model.Treulo;

import java.util.Objects;
/**
* Controleur permetant d'ajouter les collaborateur
* dans une liste temporaire qui sera utilisé par la suite pour crée une tache
 */
//Handler d'événement (bouton d'ajout de collaborateur, onAction)
//Créée par : Tom
public class AddTaskCollaboratorControl implements EventHandler<ActionEvent> {
    Treulo model;
    TextField collaborator;

    public AddTaskCollaboratorControl (Model m,TextField t){
        model=(Treulo) m;
        collaborator=t;
    }


    @Override
    public void handle(ActionEvent event) {
        //pas de nom de collaborateur vide ou qui existe déjà
        if (!Objects.equals(collaborator.getText(), "")&& !model.getCollaboratorTempo().contains(collaborator.getText())){
        model.addCollaboratorTempo(collaborator.getText());
        collaborator.setText("");
        }
    }
}
