package service;

import dto.CourseDTO;
import entity.Course;
import entity.User;
import repository.CourseRepository;

import java.time.LocalTime;
import java.util.List;


public class CourseService {


    static CourseRepository courseRepository = CourseRepository.getInstance();

    private static CourseService courseService;

    private CourseService() {
    }

    public static CourseService getInstance() {
        if (courseService == null) {
            courseService = new CourseService();
        }
        return courseService;
    }


    public boolean saveCourse(CourseDTO courseDTO) {
        return courseRepository.saveCourse(courseDTO);
    }

    public boolean saveUser(CourseDTO courseDTO) {
        return courseRepository.saveCourse(courseDTO);
    }

    public List<Course> getCourseByPartOrFull(String name) {
        return  courseRepository.searchUsersByName(name);
    }

    public boolean updateCourseMentor(Course course) {
           return courseRepository.updateCourse(course);
    }

    public Course getCourseByName(String name) {
        return courseRepository.getUsersByName(name);
    }

    public boolean updateCoursePrice(Course course) {
        return courseRepository.updateCourse(course);
    }

    public boolean updateCourseName(Course course) {
        return courseRepository.updateCourse(course);
    }

    public boolean updateCourseDuration(Course course) {
        return courseRepository.updateCourse(course);
    }

    public boolean updateCourStartTime(Course course) {return courseRepository.updateCourse(course);}


    public boolean deleteCourseByEmail(String nameCourse) {
        return courseRepository.deleteCourse(nameCourse);
    }
}
