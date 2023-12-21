package treulo.src.Controler.task;

import treulo.src.model.Model;
import treulo.src.model.TreuloTask;

import java.util.ArrayList;
import java.util.List;

public class EditDependencieControl {
    Model model;
    ArrayList <TreuloTask> dependencies;

    public EditDependencieControl(Model m, ArrayList <TreuloTask> dependencieList)
    {
        model=m;
        dependencies=dependencieList;
    }
}
