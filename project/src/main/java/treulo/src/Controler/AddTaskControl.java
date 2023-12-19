package treulo.src.Controler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import treulo.src.model.Model;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

public class AddTaskControl implements EventHandler <ActionEvent> {
        Treulo model;
        TaskList tL;
        public AddTaskControl(Model m,TaskList tL)
        {
            model= (Treulo)m;

            this.tL= tL;
        }

    @Override
    public void handle(ActionEvent event) {
            TreuloTask treutask = new TreuloTask("bonjour","bonsoir");

            this.tL.addTask(treutask);
        this.model.setDisplayMode("Tableau");
    }
}
