package ada.osc.taskie.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public class Task implements Serializable{


	@DatabaseField(generatedId = true)
	int mId;
	@DatabaseField
	private String mTitle;
	@DatabaseField
	private String mDescription;
	@DatabaseField
	private boolean mCompleted;
	@DatabaseField
	private TaskPriority mPriority;

	public Task(){}

	public Task(String title, String description, TaskPriority priority) {
		mTitle = title;
		mDescription = description;
		mCompleted = false;
		mPriority = priority;
	}

	public int getId() {
		return mId;
	}

	public void setId(int mId) {
		this.mId = mId;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String mDescription) {
		this.mDescription = mDescription;
	}

	public boolean isCompleted() {
		return mCompleted;
	}

	public void setCompleted(boolean mCompleted) {
		this.mCompleted = mCompleted;
	}

	public TaskPriority getPriority() {
		return mPriority;
	}

	public void setPriority(TaskPriority mPriority) {
		this.mPriority = mPriority;
	}
}
