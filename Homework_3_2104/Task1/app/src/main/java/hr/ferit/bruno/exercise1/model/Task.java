package hr.ferit.bruno.exercise1.model;

public class Task {
	private int importance;
	private String title;
	private String summary;

	public Task(int importance, String title, String summary) {
		this.importance = importance;
		this.title = title;
		this.summary = summary;
	}

	public int getImportance() {
		return importance;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public String toString() {
		return "Task{" +
				"importance=" + importance +
				", title='" + title + '\'' +
				", summary='" + summary + '\'' +
				'}';
	}
}
