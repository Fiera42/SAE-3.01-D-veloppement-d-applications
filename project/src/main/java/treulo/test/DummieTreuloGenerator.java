package treulo.test;

import treulo.src.model.TaskList;
import treulo.src.model.Treulo;
import treulo.src.model.TreuloTask;

public class DummieTreuloGenerator {
    public static Treulo generateDummie() {
        Treulo treulo = new Treulo();
        TreuloTask task1 = new TreuloTask("tache 1", "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum");
        TreuloTask task2 = new TreuloTask("tache 2", "Lorem Ipsum Lorem Ipsum Lpsum Lorem Ipsum ");
        TreuloTask task3 = new TreuloTask("tache 3", "");
        TreuloTask task4 = new TreuloTask("tache 4", "Lorem Ipsum Lorem Ipsum Lorsum Lorem Ipsum Lorem Ipsum ");
        TreuloTask task5 = new TreuloTask("tache 5", "Lorem Ipsorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum ");
        TreuloTask task6 = new TreuloTask("tache 6", "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum");
        TreuloTask task7 = new TreuloTask("tache 7", "ye");

        task2.addSubTask(task5);
        task3.addSubTask(task6);
        task5.addSubTask(task7);

        TaskList list1 = new TaskList("liste 1" , treulo);
        TaskList list2 = new TaskList("liste 2" , treulo);

        list1.addTask(task1);
        list1.addTask(task2);
        list2.addTask(task3);
        list2.addTask(task4);

        treulo.addTaskList(list1);
        treulo.addTaskList(list2);

        return treulo;
    }
}
