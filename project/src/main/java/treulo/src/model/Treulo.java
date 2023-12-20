package treulo.src.model;

import treulo.src.view.Observator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static treulo.src.model.TreuloTask.getAlltasks;

public class Treulo implements Model, Observator {
    protected ArrayList<Observator> observators;

    protected boolean displayArchive;
    protected String displayMode;

    protected String displayModeOld;

    protected LinkedList<TaskList> tasks;
    protected TaskList editedTaskList;

    protected List<String> collaboratorTempo = new ArrayList<String>();

    protected List<TreuloTask> dependencieTempo = new ArrayList<TreuloTask>();

    public Treulo() {
        this.observators = new ArrayList<>();
        this.displayArchive = false;
        this.displayMode = "default";
        this.displayModeOld=this.displayMode;
        this.tasks = new LinkedList<>();
    }

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

    public void addCollaboratorTempo(String nom)
    {
        this.collaboratorTempo.add(nom);
        this.updateObservator();
    }
    public List<String> getCollaboratorTempo() {
        return collaboratorTempo;
    }

    public void addDependencyTempo(TreuloTask name)
    {


               this.dependencieTempo.add(name);
       this.updateObservator();
    }

    public List<TreuloTask> getDependencieTempo() {
        return dependencieTempo;
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

    public LinkedList<TaskList> getTasks() {
        return tasks;
    }

    public void addTaskList(TaskList taskList) {
        tasks.add(taskList);
        taskList.addObservator(this);
        this.updateObservator();
    }

    public void removeTaskList(TaskList taskList) {
        tasks.remove(taskList);
        taskList.deleteObservator(this);
        this.updateObservator();
    }



    @Override
    public void update(Model model) {
        this.updateObservator();
    }

    public void setDisplayMode(String displayMode) {
        this.displayModeOld = this.displayMode;
        this.displayMode = displayMode;
        this.updateObservator();
    }

    public String getDisplayModeOld() {
        return displayModeOld;
    }

    public void setEditedTaskList(TaskList editedTaskList) {
        this.editedTaskList = editedTaskList;
    }

    public void addCollaborator(String s){
        this.collaboratorTempo.add(s);
    }
}
