package treulo.src.view;

import javafx.scene.control.Label;
import treulo.src.model.Model;
import treulo.src.model.Treulo;

import java.io.Serializable;

public class FilenameObservor extends Label implements Observator, Serializable {

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
