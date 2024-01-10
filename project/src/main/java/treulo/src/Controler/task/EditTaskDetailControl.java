package treulo.src.Controler.task;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.FloatStringConverter;
import treulo.src.model.TaskList;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;


//Controlleur chargé de mettre à jour les tâches lors d'un affichage en détail
//Handler d'événement (bouton de retour, onAction)
//Créée par : Doryann
public class EditTaskDetailControl implements EventHandler<ActionEvent> {
    Treulo model ;
    //La tâche à éditer
    TreuloTask treutask ;
    //La liste parente
    TaskList tL;

    //On observe les textfield pour sauvegardé les données
    TextField nom ;
    TextField description;
    TextField Duree;
    ComboBox<TreuloTask> combo;

    public EditTaskDetailControl(Treulo model) {
        this.model = model ;
    }

    public EditTaskDetailControl(Treulo model, TaskList editedTaskList, TextField name, TextField des, ComboBox<TreuloTask> combo , TreuloTask tt , TextField duree) {
        this.model = model ;
        this.tL = editedTaskList;
        this.nom = name ;
        this.description = des;
        this.combo = combo ;
        this.treutask = tt ;
        this.Duree = duree ;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        //Modification des données par textfield
        this.treutask.setName(this.nom.getText());
        this.treutask.setDescription(this.description.getText());
        if(!this.Duree.getText().isEmpty()) {
            this.treutask.setDuration(Math.abs(Float.parseFloat(this.Duree.getText())));
        }

        //Ajout des collaborateurs
        for(int i=0;i<model.getCollaboratorTempo().size();i++)
        {
            treutask.addCollaborator(model.getCollaboratorTempo().get(i));
        }

        //Ajout des dépendances
        for(int i=0;i<model.getDependencieTempo().size();i++)
        {
            treutask.addDependencie(model.getDependencieTempo().get(i));
        }

        //On change la sous-tâche
        if (combo.getSelectionModel().getSelectedItem()!=null)
        {
            treutask.getParentTask().deleteSubTask(treutask);
            combo.getSelectionModel().getSelectedItem().addSubTask(treutask);
        }

        //on libère l'affichage et vide les variables temporaires
        DetailTaskControl.setBoolean(true);
        this.model.getDependencieTempo().clear();
        this.model.getCollaboratorTempo().clear();
        this.model.setDisplayMode(model.getDisplayModeOld());
    }
}
