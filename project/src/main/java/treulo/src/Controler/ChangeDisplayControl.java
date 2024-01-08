package treulo.src.Controler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import treulo.src.model.Model;
import treulo.src.model.Treulo;

public class ChangeDisplayControl implements EventHandler<ActionEvent> {
    Treulo model;
    String nameButton ;

    public ChangeDisplayControl (Model m , String nb)
    {
        model= (Treulo)m;
        this.nameButton = nb ;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.model.setDisplayMode(nameButton);
        this.model.getCollaboratorTempo().clear();
        this.model.getDependencieTempo().clear();
    }
}
