package treulo.src.model;

import treulo.src.view.Observator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

//Classe représentant une liste de tâche (colonne dans la vue bureau par exemple)
//Classe originale : Doryann
//Modification pour adaptation : Tout le monde (pour la plupart des feature)
//Implémente modèle et observateur pour transféré les mises à jour au modèle
//Implémente itérable
public class TaskList implements Model, Observator, Iterable<TreuloTask>, Serializable {

    //Attributs
    private String name ;
    private boolean isArchived ;
    //tâches contenues par la liste
    private LinkedList<TreuloTask> tasks ;
    private ArrayList<Observator> observators;
    //application, notamment pour suppression de la liste
    private Treulo parentApp;

    //toutes les listes de tâche existantes
    private static List<TaskList> allLists = new LinkedList<TaskList>();

    //ID pour drag and drop
    private int id;
    private static int maxId;
    //ID pour drag and drop

    //Déploiement pour vue liste
    private boolean deploy=false;


    public TaskList(String name) {
        this.name = name;
        this.isArchived = false;
        this.tasks = new LinkedList();
        this.observators = new ArrayList<>();
        allLists.add(this);
        this.id = ++maxId;
        deploy = true;
    }

    public void addTask (TreuloTask task){
        addTask(task, tasks.size());
    }

    public void addTask (TreuloTask task, int index){
        this.tasks.add(index, task);
        //observe nos tâches pour faire remonté les update au modèle
        task.addObservator(this);
        task.setParentList(this);
        this.updateObservator();
    }

    public void deleteTask (TreuloTask task){
        this.tasks.remove(task);
        //observe nos tâches pour faire remonté les update au modèle
        task.deleteObservator(this);
        task.setParentList(null);
        this.updateObservator();
    }

    //retire la tâche de toute existance
    public void destroy() {
        LinkedList<TreuloTask> temp = new LinkedList<>(tasks);
        for(TreuloTask task : temp) {
            task.destroy();
        }
        parentApp.removeTaskList(this);
        allLists.remove(this);
    }

    public boolean isEmpty(){
        return tasks.isEmpty();
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

    @Override
    public void update(Model model) {
        updateObservator();
    }

    //patron itérable
    @Override
    public Iterator<TreuloTask> iterator() {
        return this.tasks.iterator();
    }

    public String getName() {
        return name;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public LinkedList<TreuloTask> getTasks() {
        return tasks;
    }

    public ArrayList<Observator> getObservators() {
        return observators;
    }

    public void setName(String name) {
        this.name = name;
        this.updateObservator();
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
        this.updateObservator();
    }

    public Treulo getParentApp() {
        return parentApp;
    }

    public void setParentApp(Treulo parentApp) {
        this.parentApp = parentApp;
    }

    //drag and drop
    public static TaskList getListById(int id) {
        for(TaskList list : allLists) {
            if(list.getId() == id) return list;
        }

        return null;
    }

    //serialisation des champs static
    public static List<TaskList> getAllLists() {
        return allLists;
    }

    //serialisation des champs static
    public static void setAllLists(List<TaskList> allLists) {
        TaskList.allLists = allLists;
    }

    public int getId() {
        return id;
    }

    public static int getMaxId() {
        return maxId;
    }

    public void setDeploy(boolean deploy) {
        this.deploy = deploy;
        this.updateObservator();
    }

    public boolean getdeploy()
    {
        return this.deploy;
    }

    //serialization
    public static void setMaxId(int maxId) {
        TaskList.maxId = maxId;
    }
}
