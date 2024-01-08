package treulo.src.view.appview.displayFactory;

import treulo.src.model.Treulo;
import treulo.src.view.appview.display.Display;
import treulo.src.view.appview.display.GanttDisplay;


public class GanttDisplayFactory extends DisplayFactory {

    private Treulo model;
    public GanttDisplayFactory(Treulo model) {
        this.model = model;
    }
    @Override
    public Display createDisplay() {
        return new GanttDisplay(model, model.getTasks());
    }}
