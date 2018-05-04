package ada.osc.taskie.model;

import android.util.Log;

import java.io.Serializable;
import java.util.Objects;

public class Task implements Serializable{

	private static int sID = 0;

	int mId;
	private String mTitle;
	private String mDescription;
	private boolean mCompleted;
	private TaskPriority mPriority;
	private String date;

	public Task(){}

	public Task(String title, String description, TaskPriority priority, String date) {
		mId = sID++;
		mTitle = title;
		mDescription = description;
		mCompleted = false;
		mPriority = priority;
		this.date = date;
	}

	public void setId(int mId) {
		this.mId = mId;
	}

	public int getId() {
		return mId;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public String getDescription() {
		return mDescription;
	}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

	public void setDescription(String description) {
		mDescription = description;
	}

	public boolean isCompleted() {
		return mCompleted;
	}

	public void setCompleted(boolean completed) {
		mCompleted = completed;
	}

	public TaskPriority getPriority() {
		return mPriority;
	}

	public void setPriority(TaskPriority priority) {
		mPriority = priority;
	}

	public void changePriority(){
		switch (getPriority()){
			case LOW:
				setPriority(TaskPriority.MEDIUM);
				break;
			case MEDIUM:
				setPriority(TaskPriority.HIGH);
				break;
			case HIGH:
				setPriority(TaskPriority.LOW);
				break;
		}
	}
}
