package hr.ferit.bruno.exercise1;

import java.util.List;

import hr.ferit.bruno.exercise1.model.Task;
import hr.ferit.bruno.exercise1.persistance.FakeDatabase;

public class TasksRepository {

	private static TasksRepository tasksRepository = null;

	private FakeDatabase fakeDatabase;

	private TasksRepository(){
		fakeDatabase = new FakeDatabase();
	}

	public static synchronized TasksRepository getInstance(){
		if(tasksRepository == null){
			tasksRepository = new TasksRepository();
		}
		return tasksRepository;
	}

	public void save(Task task){
		fakeDatabase.save(task);
	}

	public List<Task> getTasks(){
		return fakeDatabase.getTasks();
	}
}
