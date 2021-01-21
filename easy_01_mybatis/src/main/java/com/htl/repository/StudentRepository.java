package com.htl.repository;

import com.htl.entity.Student;

public interface StudentRepository {
    public Student findById(long id);
    public Student findByIdLazy(long id);

}
