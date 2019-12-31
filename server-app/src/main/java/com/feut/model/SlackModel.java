package com.feut.model;

public class SlackModel {
	
	private Long id;
	private StudentModel student;
	private String channelName;
	private String channelId;
	private String url;
	private String botToken;
	private Long studentId;
	private boolean active;
	
	public SlackModel() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StudentModel getStudent() {
		return student;
	}

	public void setStudent(StudentModel student) {
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

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getBotToken() {
		return botToken;
	}

	public void setBotToken(String botToken) {
		this.botToken = botToken;
	}

	@Override
	public String toString() {
		return "SlackModel [id=" + id + ", student=" + student + ", channelName=" + channelName + ", channelId="
				+ channelId + ", url=" + url + ", botToken=" + botToken + ", studentId=" + studentId + ", active="
				+ active + "]";
	}

	
	
	
}
