package com.br.ff.model;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.persistence.Id;

@Document(collection = "user_progress")
public class UserProgress {
	 @Id
	 private String id;

	@DBRef
    private Users users;
    
	@Field("notes")
    private String notes;

    @Field("progress_date")
    private LocalDateTime progressDate;

    @Field("completed_milestone")
    private boolean completedMilestone;

    public UserProgress() {
        this.progressDate = LocalDateTime.now();
    }

    public UserProgress(String id, Users userId, String notes, LocalDateTime progressDate, boolean completedMilestone) {
        this.id = id;
        this.users = userId;
        this.notes = notes;
        this.progressDate = progressDate;
        this.completedMilestone = completedMilestone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }    

    public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getProgressDate() {
        return progressDate;
    }

    public void setProgressDate(LocalDateTime progressDate) {
        this.progressDate = progressDate;
    }

    public boolean isCompletedMilestone() {
        return completedMilestone;
    }

    public void setCompletedMilestone(boolean completedMilestone) {
        this.completedMilestone = completedMilestone;
    }
}
