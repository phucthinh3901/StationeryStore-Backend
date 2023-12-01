package com.project.stationeryStore.domain.inventory;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "feedbacks")
@Getter @Setter
public class Feedbacks {

	@Column(name = "comments")
	private String Comments;
	
	@Column(name = "admin_reply")
	private String AdminReply;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "reply_date")
	private Date ReplyDate;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	@EmbeddedId
	private FeedbackId feedbackId;
}
