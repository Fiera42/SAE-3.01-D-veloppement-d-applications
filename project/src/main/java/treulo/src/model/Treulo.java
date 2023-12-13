package treulo.src.model;

import treulo.src.view.Observator;

import java.util.ArrayList;

public class Treulo implements  Model{
    protected ArrayList<Observator> observators;

    protected boolean displayArchive;
    protected String displayMode;

    protected ArrayList <TaskList> tasks;
    protected TaskList editedTaskList;

    public void loadFile(String fileName)
    {

    }

    public void SaveAsFile (String Filename)
    {

    }

    public void ExportAsImage (String Filename)
    {

    }

    public ArrayList<Observator> getObservators() {
        return observators;
    }

    public String getDisplayMode() {
        return displayMode;
    }

    public TaskList getEditedTaskList() {
        return editedTaskList;
    }

    @Override
    public void addObservator(Observator o) {
        observators.add(o);
    }

    @Override
    public void deleteObservator(Observator o) {
        observators.remove(o);
    }

    @Override
    public void updateObservator() {
        for (int i=0;i<observators.size();i++)
        {
            observators.get(i).update(this);
        }
    }
}
