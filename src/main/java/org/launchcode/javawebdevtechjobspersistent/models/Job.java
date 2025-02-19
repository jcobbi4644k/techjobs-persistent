package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.*;
import java.util.List;

@Entity
public class Job extends AbstractEntity{


//    @Id
//    @GeneratedValue
//    private int id;
//
//    private String name;
    @ManyToOne
    private Employer employer;

    @ManyToMany
    private List<Skill> skills;

//    private String employer;
//    private String skills;

    public Job() {
    }

//    public Job(String anEmployer, String someSkills) {
    public Job(Employer anEmployer, List<Skill> someSkills) {
        super();
        this.employer = anEmployer;
        this.skills = someSkills;
    }

    // Getters and setters.
/*
    public String getName() {
        return name;*/
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

//    public String getEmployer() {
    public Employer getEmployer() {
        return employer;
    }

//    public void setEmployer(String employer) {
    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

//    public String getSkills() {
    public List<Skill> getSkills() {
        return skills;
    }

//    public void setSkills(String skills) {
    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
