package service;

import dto.CourseDTO;
import entity.Course;
import repository.CourseRepository;

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
}
