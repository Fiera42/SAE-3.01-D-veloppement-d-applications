package treulo.src.Controler.task;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import treulo.src.model.Model;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

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
