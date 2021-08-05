package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.dataRepos.JobRepository;
import org.launchcode.javawebdevtechjobspersistent.dataRepos.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.dataRepos.SkillRepository;
import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");

        model.addAttribute("jobs", jobRepository.findAll());
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills",skillRepository.findAll());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
//                                    Errors errors, Model model, @RequestParam int employerId) {
                                    Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {


        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
//            model.addAttribute(new Job());
            return "redirect:add";
        }

//        return "redirect:";
        Optional<Employer> employerResult = employerRepository.findById((employerId));
        if (employerResult.isPresent()) {
            Employer employer = employerResult.get();
            newJob.setEmployer(employer);
            List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
            newJob.setSkills(skillObjs);
            jobRepository.save(newJob);
            return "redirect:";
        }
        return "add";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

//            return "view";
            Optional<Job> jobResult = jobRepository.findById(jobId);
            if (jobResult.isEmpty()) {
                model.addAttribute("title", "Invalid Job ID of" + jobId);
                return "redirect:";
            } else {
                Job job = (Job) jobResult.get();
                model.addAttribute("job", job);
                return "view";
            }
        }


}