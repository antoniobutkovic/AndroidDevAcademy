package ada.osc.taskie.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

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
	private boolean mCompleted; //used this variable to set flag if task is uploaded to server or not.
	@Expose
	@SerializedName("favorite")
	private boolean mFavorite;

	@Expose
	@SerializedName("taskPriority")
	private int mPriority;

	public Task(){

	}

	public Task(String title, String description, TaskPriority priority) {
		mId = UUID.randomUUID().toString();
		mTitle = title;
		mDescription = description;
		mCompleted = false;
		mFavorite = false;

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


	public String getId() {
		return mId;
	}

	public void setId(String mId) {
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

	public int getmPriority(){
		return mPriority;
	}

	public TaskPriority getPriority() {
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

	public void setPriority(int mPriority) {
		this.mPriority = mPriority;
	}

	public boolean isFavorite() {
		return mFavorite;
	}

	public void setFavorite(boolean mFavorite) {
		this.mFavorite = mFavorite;
	}
}
