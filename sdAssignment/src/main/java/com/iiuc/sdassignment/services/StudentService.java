package com.iiuc.sdassignment.services;

import com.iiuc.sdassignment.entities.Course;
import com.iiuc.sdassignment.entities.Student;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();
    private Long nextId = 100L;

    public List<Student> getAll() {
        return new ArrayList<>(students);
    }
    public Optional<Student> getById(Long id) {
        return students.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public Student createStudent( Student student) {
        student.setId(nextId++);
        student.setCourseIds(new ArrayList<>());
        students.add(student);
        return student;
    }
    public boolean add(Long studentId,Long cid) {
        Student student = students.stream().filter(c -> c.getId().equals(studentId)).findFirst().orElse(null);
        List<Long> arr = student.getCourseIds();
        if(student.getCourseIds()!=null ) {
            if(!arr.contains(cid)) {
                arr.add(cid);
            }

        }else{
            arr.add(cid);
        }
        student.setCourseIds(arr);

        return true;
    }
    public String deleteCourse(Long studentId, Long courseId) {
        if (courseId == null) {
            return "courseId is required";
        }

        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                List<Long> courseIds = student.getCourseIds();
                if (courseIds == null || courseIds.isEmpty()) {
                    return "No courses to delete for student " + studentId;
                }

                boolean removed = courseIds.removeIf(id -> courseId.equals(id));
                return removed
                        ? "Deleted courseId " + courseId + " for student " + studentId
                        : "CourseId " + courseId + " not found for student " + studentId;
            }
        }
        return "Student " + studentId + " not found";
    }

}
