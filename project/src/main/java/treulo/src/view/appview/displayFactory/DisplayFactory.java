package treulo.src.view.appview.displayFactory;

import treulo.src.model.Treulo;
import treulo.src.view.appview.display.Display;

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
            default :
                return new TextDisplayFactory(model);
        }
    }
    public abstract Display createDisplay();

}
