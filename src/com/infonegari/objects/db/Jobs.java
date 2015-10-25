package com.infonegari.objects.db;

import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

public class Jobs extends SugarRecord<Jobs>{
	private long jobId;
	private String Job_Title;
	private String Category;
	private long LocationId;
	private String Education_Level;
	private String Qualification;
	private String Responsibility;
	private String Experiance;
	private String Salary;
	private String Address;
	private String Work_Place;
	private String Dead_Line;
	private String Job_Duration;
	private String User_Name;
	
    public boolean isNew() {
        long count = Select.from(Jobs.class).where(Condition.prop("id").eq(id)).count();
        return count == 0;
    }

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public String getJob_Title() {
		return Job_Title;
	}

	public void setJob_Title(String job_Title) {
		Job_Title = job_Title;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public long getLocationId() {
		return LocationId;
	}

	public void setLocationId(long locationId) {
		LocationId = locationId;
	}

	public String getEducation_Level() {
		return Education_Level;
	}

	public void setEducation_Level(String education_Level) {
		Education_Level = education_Level;
	}

	public String getQualification() {
		return Qualification;
	}

	public void setQualification(String qualification) {
		Qualification = qualification;
	}

	public String getResponsibility() {
		return Responsibility;
	}

	public void setResponsibility(String responsibility) {
		Responsibility = responsibility;
	}

	public String getExperiance() {
		return Experiance;
	}

	public void setExperiance(String experiance) {
		Experiance = experiance;
	}

	public String getSalary() {
		return Salary;
	}

	public void setSalary(String salary) {
		Salary = salary;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getWork_Place() {
		return Work_Place;
	}

	public void setWork_Place(String work_Place) {
		Work_Place = work_Place;
	}

	public String getDead_Line() {
		return Dead_Line;
	}

	public void setDead_Line(String dead_Line) {
		Dead_Line = dead_Line;
	}

	public String getJob_Duration() {
		return Job_Duration;
	}

	public void setJob_Duration(String job_Duration) {
		Job_Duration = job_Duration;
	}
    
	public String getUser_Name() {
		return User_Name;
	}

	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}

	@Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
