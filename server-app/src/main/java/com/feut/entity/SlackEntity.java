package com.feut.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="slack", schema="feut")
public class SlackEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="student_id")
	private StudentEntity student;
	
	@Column(name="channel_name")
	private String channelName;
	
	@Column(name="channel_id")
	private String channelId;
	
	@Column(name="bot_token")
	private String botToken;
	
	@Column(name="url")
	private String url;
	
	@Column(name="active")
	private boolean active;
	
	public SlackEntity() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getBotToken() {
		return botToken;
	}

	public void setBotToken(String botToken) {
		this.botToken = botToken;
	}

	@Override
	public String toString() {
		return "SlackEntity [id=" + id + ", student=" + student + ", channelName=" + channelName + ", channelId="
				+ channelId + ", botToken=" + botToken + ", url=" + url + ", active=" + active + "]";
	}

	
	
}
