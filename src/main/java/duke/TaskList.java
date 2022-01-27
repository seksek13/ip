package duke;
import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>(100);
    }

    public int size() {
        return tasks.size();
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public Task deleteTask(int index) {
        Task t = tasks.get(index);
        tasks.remove(index);
        return t;
    }

    public Task markTask(int index) {
        Task t = tasks.get(index);
        t.markAsDone();
        return t;
    }

    public Task UnmarkTask(int index) {
        Task t = tasks.get(index);
        t.markAsNotDone();
        return t;
    }

    @Override
    public String toString() {

        String taskList = "";
        for(int i=0;i< tasks.size();i++){
            int count = i+1;
            taskList += count + "." + tasks.get(i) + "\n";
        }
        return taskList;
    }
}