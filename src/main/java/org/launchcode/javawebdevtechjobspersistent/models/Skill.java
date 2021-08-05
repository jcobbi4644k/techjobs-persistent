package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
//import javax.validation.constaints.NotNull;


@Entity
public class Skill extends AbstractEntity {

    @NotBlank(message = "Description cannot be empty")
    @Size(min = 1, max = 100, message = "Must be from 1-100 characters")
    private String description;

    @ManyToMany(mappedBy = "skill")
    private List<Job> jobs = new ArrayList<>();

    public Skill()  {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }
}
