package io.agileintelligence.io.agileintelligence.web;

import io.agileintelligence.io.agileintelligence.domain.Course;
import io.agileintelligence.io.agileintelligence.domain.Lesson;
import io.agileintelligence.io.agileintelligence.domain.Section;
import io.agileintelligence.io.agileintelligence.domain.User;
import io.agileintelligence.io.agileintelligence.repository.CourseRepository;
import io.agileintelligence.io.agileintelligence.repository.SectionRepository;
import io.agileintelligence.io.agileintelligence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by carlosarosemena on 2017-03-11.
 */

@Controller
public class CourseController
{

    private CourseRepository courseRepo;
    private SectionRepository sectionRepo;
    private UserRepository userRepo;



    @RequestMapping("/")
    public String rootPath()
    {

        return "redirect:/courses";
    }

    @RequestMapping(value ="courses", method = RequestMethod.GET)
    public String courses (ModelMap model)
    {
        Course course = new Course();
       List<Course> courses = courseRepo.findAll();
       model.put("courses", courses);
        model.put("course", course);
        return "courses";
    }

    @RequestMapping(value ="courses", method = RequestMethod.POST)
    public String coursesPost (@ModelAttribute Course course, ModelMap model, @AuthenticationPrincipal User user)
    {
        course.setUser(user);
        user.getCourses().add(course);

        Course savedCourse = courseRepo.save(course);

        return "redirect:/editCourse/" + savedCourse.getId();
    }


    @RequestMapping(value="editCourse/{courseId}", method= RequestMethod.GET)
    public String editCourseGet (@PathVariable Long courseId, ModelMap model)
    {
        Course course = courseRepo.findOne(courseId);
        if(course == null){
            return "redirect:/";
        }
        model.put("course", course);

        model.put("sections", course.getSections());
        return "editCourse";
    }


    @RequestMapping(value="editCourse/{courseId}/deleteCourse", method= RequestMethod.POST)
    public String deleteCourse (@PathVariable Long courseId, ModelMap model, @AuthenticationPrincipal User user)
    {

        Course course = courseRepo.findOne(courseId);
        User savedUser = userRepo.findUserByEmail(user.getEmail());

        savedUser.getCourses().remove(course);
        courseRepo.delete(course);

        return "redirect:/";

    }

    @RequestMapping(value="editCourse/createSection", method= RequestMethod.POST)
    public @ResponseBody Course createSection (@RequestParam Long courseId, @RequestParam String sectionName, ModelMap model)
    {
        Course course = courseRepo.findOne(courseId);
        Section section = new Section();
        section.setName(sectionName);
        section.setCourse(course);
        course.getSections().add(section);
        Course savedCourse = courseRepo.save(course);

        return savedCourse;
    }

    @RequestMapping(value="editCourse/createLesson", method= RequestMethod.POST)
    public @ResponseBody Course createLesson (@RequestParam Long courseId, @RequestParam Long sectionId,
                                              String lessonTitle, Integer lessonNumber, ModelMap model)
    {
        Course course = courseRepo.findOne(courseId);

        for(Section section : course.getSections()){

            if(section.getId().equals(sectionId)){

            Lesson lesson = new Lesson();
            lesson.setTitle(lessonTitle);
            lesson.setNumber(lessonNumber);
            lesson.setSection(section);
            section.getLessons().add(lesson);
            sectionRepo.save(section);
            break;
            }

        }


        return course;

    }


//    @RequestMapping(value = "createCourse", method = RequestMethod.GET)
//    public String editCourseGet(ModelMap model)
//    {
//        Course course = new Course();
//        model.put("course", course);
//        return "createCourse";
//    }
//
//    @RequestMapping(value = "createCourse", method = RequestMethod.POST)
//    public String editCoursePost(@ModelAttribute Course course, ModelMap model)
//    {
//        courseRepo.save(course);
//        return "redirect:/";
//    }
//
//    @RequestMapping(value = "editCourse/addLesson/{courseid}", method = RequestMethod.GET)
//    public String addLessonGet(@PathVariable Long courseid, ModelMap model)
//    {
//        Course course = courseRepo.findOne(courseid);
//        model.put("course", course);
//        Lesson lesson = new Lesson();
//        model.put("lesson", lesson);
//
//
//        return "addLesson";
//    }
//
//    @RequestMapping(value = "editCourse/addLesson/{courseid}", method = RequestMethod.POST)
//    public String addLessonPost (@ModelAttribute Lesson lesson, @PathVariable Long courseid, ModelMap model)
//    {
//        Course course = courseRepo.findOne(courseid);
//        lesson.setCourse(course);
//        course.getLessons().add(lesson);
//        courseRepo.save(course);
//
//        return "redirect:/";
//
//    }


    @Autowired
    public void setCourseRepo(CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }

    @Autowired
    public void setSectionRepo(SectionRepository sectionRepo) {
        this.sectionRepo = sectionRepo;
    }

    @Autowired
    public void setUserRepo(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
}
