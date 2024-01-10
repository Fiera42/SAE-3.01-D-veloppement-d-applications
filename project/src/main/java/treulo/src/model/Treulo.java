package treulo.src.model;

import treulo.src.view.Observator;

import java.io.*;
import java.util.*;

import static treulo.src.model.TreuloTask.getAlltasks;

public class Treulo implements Model, Observator, Serializable, Iterable<TaskList> {
    protected ArrayList<Observator> observators;

    protected boolean displayArchive;
    protected String displayMode;

    protected String displayModeOld;

    protected LinkedList<TaskList> tasks;
    protected TaskList editedTaskList;

    protected TreuloTask tache;

    //GESTION DE FICHIER
    private String filename;
    private String path;
    private List<TreuloTask> serialAllTask;
    private List<TaskList> serialAllTaskList;
    private int serialMaxTaskId;
    private int serialMaxListId;
    //GESTION DE FICHIER

    protected List<String> collaboratorTempo = new ArrayList<String>();

    protected List<TreuloTask> dependencieTempo = new ArrayList<TreuloTask>();

    public Treulo() {
        this.observators = new ArrayList<>();
        this.displayArchive = false;
        this.displayMode = "default";
        this.displayModeOld = this.displayMode;
        this.tasks = new LinkedList<>();
        serialAllTask = TreuloTask.getAlltasks();
        serialAllTaskList = TaskList.getAllLists();
    }

    public void openFile(String path) {
        Treulo loadedModel = null;

        try {
            if(path == null || path.isEmpty()) throw new IllegalArgumentException("Path is empty");
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            loadedModel = (Treulo) objectInputStream.readObject();
            objectInputStream.close();
            if(loadedModel == null) throw new FileNotFoundException("no model loaded");
        }
        catch (Exception e) {e.printStackTrace();}

        if(loadedModel == null) return;

        newFile();

        TaskList.setAllLists(loadedModel.serialAllTaskList);
        TaskList.setMaxId(loadedModel.serialMaxListId);
        TreuloTask.setAlltasks(loadedModel.serialAllTask);
        TreuloTask.setMaxId(loadedModel.serialMaxTaskId);

        for(TaskList taskList : loadedModel) {
            addTaskList(taskList);
        }
    }

    public void saveAsFile () {
        serialMaxTaskId = TreuloTask.getMaxId();
        serialMaxListId = TreuloTask.getMaxId();
        try {
            if(path == null || path.isEmpty()) throw new IOException("File path is empty");
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(this);
            objectOutputStream.flush();
            objectOutputStream.close();
        }
        catch (IOException ioException) {ioException.printStackTrace();}
    }

    public void exportAsImage (String filename) {
        setFilename(filename);
    }

    public void newFile() {
        LinkedList<TaskList> listes = new LinkedList<>(tasks);

        for(TaskList list : listes) {
            list.destroy();
        }

        TreuloTask.setAlltasks(new LinkedList<TreuloTask>());

        setFilename("");
        setPath("");
        setDisplayArchive(false);
        setDisplayMode("Tableau");
        collaboratorTempo = new ArrayList<>();
        tache = null;
        editedTaskList = null;
        displayModeOld = "";
        TaskList.setMaxId(0);
        TreuloTask.setMaxId(0);
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
        taskList.setParentApp(this);
        this.updateObservator();
    }

    public void removeTaskList(TaskList taskList) {
        tasks.remove(taskList);
        taskList.deleteObservator(this);
        taskList.setParentApp(null);
        this.updateObservator();
    }

    public void setTasks(LinkedList<TaskList> tasks) {
        this.tasks = tasks;
        updateObservator();
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

    public void setTache(TreuloTask task){
        this.tache = task ;
    }

    public  TreuloTask getTache(){
        return this.tache;
    }

    public boolean getDisplayArchive() {
        return displayArchive;
    }

    public void setDisplayArchive(boolean displayArchive) {
        this.displayArchive = displayArchive;
        updateObservator();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
        updateObservator();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public Iterator<TaskList> iterator() {
        return tasks.iterator();
    }
}
