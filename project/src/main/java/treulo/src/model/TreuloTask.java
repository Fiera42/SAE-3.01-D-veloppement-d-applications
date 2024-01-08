package treulo.src.model;

import treulo.src.view.Observator;

import java.text.Collator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreuloTask implements Model, Observator {
    private String name , description;
    private boolean isArchive;

    private float duration ;
    private ArrayList<String> collaborators ;
    private ArrayList<TreuloTask> dependencies ;
    private LinkedList<TreuloTask> subtasks ;

    private ArrayList<Observator> observators ;

    private TreuloTask parentTask;
    private TaskList parentList;
    private static List<TreuloTask> alltasks = new LinkedList<TreuloTask>();
    private int id;
    private static int maxId;

    private boolean deploy=false;

    public TreuloTask (){
        this.name = "" ;
        this.description = "" ;
        this.duration = 0 ;
        this.collaborators = new ArrayList<String>();
        this.dependencies = new ArrayList<TreuloTask>() ;
        this.subtasks = new LinkedList<>() ;
        this.observators = new ArrayList<Observator>();
        alltasks.add(this) ;
        this.id = ++maxId;
    }

    public TreuloTask (String n){
        this.name = n ;
        this.description = "" ;
        this.duration = 0 ;
        this.collaborators = new ArrayList<String>();
        this.dependencies = new ArrayList<TreuloTask>() ;
        this.subtasks = new LinkedList<>() ;
        this.observators = new ArrayList<Observator>();
        alltasks.add(this) ;
        this.id = ++maxId;
    }

    public TreuloTask (String n , String d){
        this.name = n ;
        this.description = d ;
        this.duration = 0 ;
        this.collaborators = new ArrayList<String>();
        this.dependencies = new ArrayList<TreuloTask>() ;
        this.subtasks = new LinkedList<>() ;
        this.observators = new ArrayList<Observator>();
        alltasks.add(this) ;
        this.id = ++maxId;
    }

    public TreuloTask (String n , String d , float f){
        this.name = n ;
        this.description = d ;
        this.duration = f ;
        this.collaborators = new ArrayList<String>();
        this.dependencies = new ArrayList<TreuloTask>() ;
        this.subtasks = new LinkedList<>() ;
        this.observators = new ArrayList<Observator>();
        alltasks.add(this) ;
        this.id = ++maxId;
    }


    public void addDependencie(TreuloTask task){
        this.dependencies.add(task);
        this.updateObservator();
    }

    public void deleteDependencie(TreuloTask task){
        this.dependencies.remove(task);
        this.updateObservator();
    }

    public ArrayList<TreuloTask> getDependencies(){
        return this.dependencies ;
    }

    public void addSubTask(TreuloTask task){
        this.subtasks.add(task);
        task.setParentTask(this);
        task.addObservator(this);
        task.setParentList(parentList);
        this.updateObservator();
    }

    public void deleteSubTask(TreuloTask task){
        this.subtasks.remove(task);
        task.setParentTask(null);
        task.deleteObservator(this);
        task.setParentList(null);
        this.updateObservator();
    }

    public LinkedList<TreuloTask> getSubtasks(){
        return this.subtasks ;
    }

    public void destroy() {
        if (parentList != null) {
            parentList.deleteTask(this);
        }
        if (parentTask != null) {
            parentTask.deleteSubTask(this);
        }
        alltasks.remove(this);
    }

    @Override
    public void addObservator(Observator o) {
        this.observators.add(o);
    }

    @Override
    public void deleteObservator(Observator o) {
        this.observators.remove(o);
    }

    @Override
    public void updateObservator() {
        for (Observator o: this.observators) {
            o.update(this);
        }
    }

    public void addCollaborator(String name)
    {
        collaborators.add(name);
    }

    public ArrayList<String> getCollaborators() {
        return collaborators;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isArchived() {
        return this.isArchive;
    }

    public static List<TreuloTask> getAlltasks(){
        return alltasks;
    }

    public void setName(String name) {
        this.name = name;
        this.updateObservator();
    }

    public void setDescription(String description) {
        this.description = description;
        this.updateObservator();
    }

    public void setArchive(boolean archive) {
        isArchive = archive;
        this.updateObservator();
    }

    public void setDuration(float duration) {
        this.duration = duration;
        this.updateObservator();
    }

    public TreuloTask getParentTask() {
        return parentTask;
    }

    public void setParentTask(TreuloTask parentTask) {
        this.parentTask = parentTask;
    }

    public TaskList getParentList() {
        return parentList;
    }

    public void setParentList(TaskList parentList) {
        this.parentList = parentList;
        for(TreuloTask subTask : subtasks) {
            subTask.setParentList(parentList);
        }
    }

    public void setDeploy(boolean deploy) {
        this.deploy = deploy;
        this.updateObservator();
    }

    public boolean getDeploy()
    {
        return this.deploy;
    }

    @Override
    public String toString() {
        return
                "'" + name + '\'';
    }

    /*

    public boolean equals(Object obj) {
        if(!(obj instanceof TreuloTask)) return false;
        TreuloTask task = (TreuloTask) obj;

        if(!task.name.equals(name)) return false;
        if(!task.description.equals(description)) return false;
        if(!task.subtasks.containsAll(subtasks)) return false;
        if(task.isArchive != isArchive) return false;
        if(task.parentTask != parentTask) return false;
        if(task.parentList != parentList) return false;

        return true;
    }
     */

    @Override
    public void update(Model model) {
        updateObservator();
    }

    public float getDuration() {
        return duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static TreuloTask getTaskById(int id) {
        for(TreuloTask task : alltasks) {
            if(task.getId() == id) return task;
        }

        return null;
    }

    public ArrayList<Observator> getObservators() {
        return observators;
    }

    public void setObservators(ArrayList<Observator> observators) {
        this.observators = observators;
    }
}
