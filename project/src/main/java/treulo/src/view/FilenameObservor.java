package treulo.src.view;

import javafx.scene.control.Label;
import treulo.src.model.Model;
import treulo.src.model.Treulo;

public class FilenameObservor extends Label implements Observator {

    public FilenameObservor() {
    }

    public FilenameObservor(String text) {
        super(text);
    }

    @Override
    public void update(Model model) {
        if(!(model instanceof Treulo)) return;

        Treulo treulo = (Treulo) model;
        if(!treulo.getFilename().equals("")) setText(treulo.getFilename());
        else setText("unnamed");
    }
}
