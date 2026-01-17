package com.iiuc.sdassignment.entities;

import java.util.List;

public class Student {
    private Long id;
    private List<Long> courseIds;

    public Student() {}

    public Student(List<Long> courseIds, Long id) {
        this.courseIds = courseIds;
        this.id = id;
    }

    public List<Long> getCourseIds() {
        return courseIds;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setCourseIds(List<Long> courseIds) {
        this.courseIds = courseIds;
    }
}
