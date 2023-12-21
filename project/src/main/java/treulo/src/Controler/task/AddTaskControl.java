package treulo.src.Controler.task;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import treulo.src.model.Model;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

import java.util.Objects;

public class AddTaskControl implements EventHandler <ActionEvent> {
        Treulo model;
        TaskList tL;
    TextField nom ;
    TextField description;

    ComboBox<TreuloTask> combo;

        public AddTaskControl(Model m, TaskList tL , TextField nom , TextField description , ComboBox<TreuloTask> combo)
        {
            model= (Treulo)m;

            this.tL= tL;
            this.description=description;
            this.nom=nom;
            this.combo=combo;
        }

    @Override
    public void handle(ActionEvent event) {
            if (Objects.equals(description.getText(), "") || Objects.equals(nom.getText(), "")){}
            else {
            TreuloTask treutask = new TreuloTask(this.nom.getText(),this.description.getText());


                for(int i=0;i<model.getCollaboratorTempo().size();i++)
                {
                    treutask.addCollaborator(model.getCollaboratorTempo().get(i));
                }
                for(int i=0;i<model.getDependencieTempo().size();i++)
                {
                    treutask.addDependencie(model.getDependencieTempo().get(i));
                }

                if (combo.getSelectionModel().getSelectedItem()!=null)
                {
                    combo.getSelectionModel().getSelectedItem().addSubTask(treutask);
                }
                else
                {
                    this.tL.addTask(treutask);
                }


            }
            this.model.getDependencieTempo().clear();
            this.model.getCollaboratorTempo().clear();
        this.model.setDisplayMode("Tableau");
    }
}
