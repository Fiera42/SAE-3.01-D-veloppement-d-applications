package treulo.src.Controler.task;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import treulo.src.model.Model;
import treulo.src.model.Treulo;

public class AddTaskMenuControl implements EventHandler<ActionEvent> {
        Treulo model;

        public AddTaskMenuControl(Model m)
        {
            model= (Treulo) m;
        }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.model.setDisplayMode("Nouvelle tache");
    }
}