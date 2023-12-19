package treulo.src.Controler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import treulo.src.model.Model;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

public class AddTaskControl implements EventHandler <ActionEvent> {
        Treulo model;
        TaskList tL;
    TextField nom ;
    TextField description;

        public AddTaskControl(Model m, TaskList tL , TextField nom , TextField description)
        {
            model= (Treulo)m;

            this.tL= tL;
            this.description=description;
            this.nom=nom;
        }

    @Override
    public void handle(ActionEvent event) {
            TreuloTask treutask = new TreuloTask(this.nom.getText(),this.description.getText());

            this.tL.addTask(treutask);
        this.model.setDisplayMode("Tableau");
    }
}
