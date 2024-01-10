package treulo.src.model;

import treulo.src.view.Observator;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Classe représentant une tâche
//Classe originale : Doryann
//Modification pour adaptation : Tout le monde (pour la plupart des feature)
//Implémente modèle et observateur pour transféré les mises à jour au modèle
public class TreuloTask implements Model, Observator, Serializable {

    //attributs
    private String name , description;
    private boolean isArchive;
    private float duration ;
    //collaborateurs
    private ArrayList<String> collaborators ;
    //dépendances
    private ArrayList<TreuloTask> dependencies ;
    //sous-tâches
    private LinkedList<TreuloTask> subtasks ;

    private ArrayList<Observator> observators ;

    //tâche parente si sous-tâche
    private TreuloTask parentTask;
    //Liste dans laquel se trouve la tâche
    private TaskList parentList;
    //toutes les tâches
    private static List<TreuloTask> alltasks = new LinkedList<TreuloTask>();

    //ID pour drag and drop
    private int id;
    private static int maxId;
    //ID pour drag and drop

    //déploiement des sous-tâche pour display en liste
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
        deploy = true;
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
        deploy = true;
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
        deploy = true;
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
        deploy = true;
    }


    public void addDependencie(TreuloTask task){
        if (!this.getDependencies().contains(task))
        {
        this.dependencies.add(task);
        this.updateObservator();
        }
    }

    public void deleteDependencie(TreuloTask task){
        this.dependencies.remove(task);
        this.updateObservator();
    }

    public ArrayList<TreuloTask> getDependencies(){
        return this.dependencies ;
    }

    public void addSubTask(TreuloTask task){
        addSubTask(task, subtasks.size());
    }

    public void addSubTask(TreuloTask task, int index){
        if (task.getDuration()>this.duration){
            this.duration = task.getDuration();
        }
        this.subtasks.add(index, task);
        task.setParentTask(this);
        //on observe nos sous-tâches
        task.addObservator(this);
        task.setParentList(parentList);
        this.updateObservator();
    }

    public void deleteSubTask(TreuloTask task){
        this.subtasks.remove(task);
        task.setParentTask(null);
        //on observe nos sous-tâches
        task.deleteObservator(this);
        task.setParentList(null);
        this.updateObservator();
    }

    public LinkedList<TreuloTask> getSubtasks(){
        return this.subtasks ;
    }

    //réduit la tâche à néant
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

    //serialization & autre
    public static List<TreuloTask> getAlltasks(){
        return alltasks;
    }
    //serialization
    public static void setAlltasks(List<TreuloTask> alltasks) {
        TreuloTask.alltasks = alltasks;
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

    //est-ce que cette tâche possède des tâches dépendante d'elle ?
    public boolean isIndependent()
    {
        boolean res=true;
        int i=0;
        while (i<TreuloTask.getAlltasks().size() && res)
        {
            if (TreuloTask.getAlltasks().get(i).getDependencies().contains(this))
            {
                res=false;
            }
            i++;
        }
        return res;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //click & drag
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

    public static int getMaxId() {
        return maxId;
    }

    //serialization
    public static void setMaxId(int maxId) {
        TreuloTask.maxId = maxId;
    }
}
