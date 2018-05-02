package ada.osc.taskie.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ada.osc.taskie.model.Task;
import ada.osc.taskie.model.TaskPriority;

public class TaskGenerator {

	private static final Random generator = new Random();

	private static String[] titles = {
			"Osc", "Potato", "Homework", "Shopping", "Gaming", "Reading",
			"Android", "Studying", "Question", "Ideas", "Party", "Nothing",
	};

	private static String[] descriptions = {
			"Do it.", "Play it", "Drink it", "Answer it", "Shut it down",
			"Try it.", "Don't do it.", "Ignore it.",
	};

	private static String[] dates = {
			"2, lipanj 2018", "5, srpanj 2018", "8, svibanj 2018", "24, lipanj 2018", "20, kolovoz, 2018",
			"16, lipanj 2018", "2, prosinac 2018", "11, studeni 2018",
	};

	public static List<Task> generate(int taskCount) {
		List<Task> tasks = new ArrayList<Task>();
		for (int i=0; i<taskCount; i++){
			String title = titles[generator.nextInt(titles.length)];
			String description = descriptions[generator.nextInt(descriptions.length)];
			String date = dates[generator.nextInt(dates.length)];

			int prioritySelector = generator.nextInt(TaskPriority.values().length);
			TaskPriority priority = TaskPriority.values()[prioritySelector];

			tasks.add(new Task(title, description, priority, date));
		}
		return tasks;
	}
}

