package com.hibernate.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="MEETING_TABLE")
public class Meeting {

	@Id
	@GeneratedValue
	@Column(name="MEETING_ID")
	private int meetingId;
	
	@Column(name="MEETING_SUB")
	private String subject;
	
	@Column(name="MEETING_DATE")
	private Date meetingDate;

	@ManyToMany(mappedBy="meetings")
	private Collection<UserDetails> userList=new ArrayList<>(0);
	
	public Collection<UserDetails> getUserList() {
		return userList;
	}
	public void setUserList(Collection<UserDetails> userList) {
		this.userList = userList;
	}
	public int getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(int meetingId) {
		this.meetingId = meetingId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}

	@Override
	public String toString() {
		return "Meeting [meetingId=" + meetingId + ", subject=" + subject + ", meetingDate=" + meetingDate + "]";
	}
	
}
