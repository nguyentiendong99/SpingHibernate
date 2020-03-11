package edu.fa.model;
	import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name; 
	@Temporal(TemporalType.DATE)
	private Date createddate;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<syllabus> syllabusList = new ArrayList<syllabus>();


	public List<syllabus> getSyllabusList() {
		return syllabusList;
	}

	public void setSyllabusList(List<syllabus> syllabusList) {
		this.syllabusList = syllabusList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	 public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
	
	public Course(String name, Date createddate) {
		super();
		this.name = name;
		this.createddate = createddate;
	}

	public Course(String name, Date createddate, List<syllabus> syllabusList) {
		super();
		this.name = name;
		this.createddate = createddate;
		this.syllabusList = syllabusList;
	}

	public Course() {
		super();
	}

	public Course(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
