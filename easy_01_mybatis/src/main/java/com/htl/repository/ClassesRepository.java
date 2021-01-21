package com.htl.repository;

import com.htl.entity.Classes;

public interface ClassesRepository {
    public Classes findById(long id);
    public Classes findByIdLazy(long id);

}
