package ada.osc.taskie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.UUID;

public class Task implements Serializable{

	@Expose
	@SerializedName("id")
	private String mId;
	@Expose
	@SerializedName("title")
	private String mTitle;
	@Expose
	@SerializedName("content")
	private String mDescription;
	@Expose
	@SerializedName("complete")
	private boolean mCompleted;
	@Expose
	@SerializedName("taskPriority")
	private int mPriority;

	public Task(String title, String description, TaskPriority priority) {
		mId = UUID.randomUUID().toString();
		mTitle = title;
		mDescription = description;
		mCompleted = false;

		switch (priority){
			case LOW:
				mPriority = 1;
				break;
			case MEDIUM:
				mPriority = 2;
				break;
			case HIGH:
				mPriority = 3;
				break;
		}
	}


	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
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

	public void setDescription(String description) {
		mDescription = description;
	}

	public boolean isCompleted() {
		return mCompleted;
	}

	public void setCompleted(boolean completed) {
		mCompleted = completed;
	}

	public TaskPriority getmPriority() {
		switch (mPriority){
			case 1:
				return TaskPriority.LOW;
			case 2:
				return TaskPriority.MEDIUM;
			case 3:
				return TaskPriority.HIGH;
		}

		return TaskPriority.LOW;
	}

	public void setmPriority(int mPriority) {
		this.mPriority = mPriority;
	}
}
