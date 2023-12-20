package treulo.src.Controler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import treulo.src.model.Model;
import treulo.src.model.Treulo;

public class BackControler implements EventHandler<ActionEvent> {

    Treulo model;

    public BackControler (Model m)
    {
        this.model=(Treulo) m;
    }

    @Override
    public void handle(ActionEvent event) {
        this.model.setDisplayMode(model.getDisplayModeOld());

        this.model.getDependencieTempo().clear();
        this.model.getCollaboratorTempo().clear();
    }
}
