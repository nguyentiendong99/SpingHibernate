package edu.fa.model;

import javax.persistence.Embeddable;

@Embeddable
public class syllabus {
	private String content;
	private int duration;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public syllabus(String content, int duration) {
		super();
		this.content = content;
		this.duration = duration;
	}
	public syllabus() {
		super();
	}
	

}
