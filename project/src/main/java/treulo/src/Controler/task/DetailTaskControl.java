package treulo.src.Controler.task;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import treulo.src.model.Model;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

//Controlleur chargé de changé le mode d'affichage pour afficher les tâche en détail
//Handler d'événement (Vbox des tâches, onClick)
//Créée par : Doryann
public class DetailTaskControl implements EventHandler<MouseEvent> {
    private Treulo model;
    private TreuloTask treuloTask;
    private static boolean  b = true ;

    public DetailTaskControl(Model model, TreuloTask treuloTask) {
        this.model = (Treulo) model;
        this.treuloTask = treuloTask;
    }

    @Override
    public void handle(MouseEvent actionEvent) {
        //Pour éviter que l'event fasse quelque chose sur les sous-tâches
        if (b == true) {
            this.model.setTache(treuloTask);
            this.model.setDisplayMode("Detaille Tache");
            b = false ;
        }
    }

    public static void setBoolean(Boolean bb){
        b = bb ;
    }
}
