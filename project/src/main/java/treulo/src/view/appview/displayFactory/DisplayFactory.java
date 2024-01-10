package treulo.src.view.appview.displayFactory;

import treulo.src.model.Treulo;
import treulo.src.view.appview.display.Display;

//Classe abstraire de fabrique d'afficheur
//Créée par : Adrien
public abstract class DisplayFactory {

    public static DisplayFactory getDisplayFactory(Treulo model, String displayMode) {

        //Création de l'afficheur en fonction du mode d'affichage
        switch (displayMode) {
            case "Nouvelle tache":
                return new CreateTaskDisplayFactory(model);
            case "Nouvelle Liste":
                return new CreateTaskListDisplayFactory(model);
            case "Tableau" :
                return new DeskDisplayFactory(model);
            case "Liste" :
                return  new ListDisplayFactory(model);
            case "GANTT" :
                return new GanttDisplayFactory(model);
            case "Detaille Tache" :
                return new DetailTaskDisplayFactory(model);
            default :
                return new TextDisplayFactory(model);
        }
    }
    public abstract Display createDisplay();

}
