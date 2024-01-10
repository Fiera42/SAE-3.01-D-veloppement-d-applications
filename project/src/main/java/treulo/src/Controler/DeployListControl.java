package treulo.src.Controler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import treulo.src.model.Model;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

import java.util.Objects;

//Controlleur chargé de mettre à jour le déploiement des liste dans la vue liste
//Handler d'événement (bouton de pliage/dépliage des liste, onAction)
//Créée par : Tom
public class DeployListControl implements EventHandler <ActionEvent> {
    Treulo model;
    TaskList treuloList;

    Button state;

    public DeployListControl (Model m,TaskList tt)
    {
        model=(Treulo) m;
        treuloList =tt;
    }

    @Override
    public void handle(ActionEvent event) {
        if (!treuloList.getdeploy())
        {

            treuloList.setDeploy(true);
        }
        else
        {

            treuloList.setDeploy(false);
        }
    }
}
