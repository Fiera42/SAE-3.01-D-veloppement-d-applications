package treulo.src.Controler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import treulo.src.model.Model;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

import java.util.Objects;

public class DeployListControl implements EventHandler <ActionEvent> {
    Treulo model;
    TaskList treuloList;

    Button state;

    public DeployListControl (Model m,TaskList tt,Button nameButton)
    {
        model=(Treulo) m;
        treuloList =tt;
        state=nameButton;
    }

    @Override
    public void handle(ActionEvent event) {
        if (!treuloList.getdeploy())
        {

            treuloList.setDeploy(true);
            state.setText("roulé");
        }
        else
        {

            treuloList.setDeploy(false);
            state.setText("déroulé");

        }
    }
}
