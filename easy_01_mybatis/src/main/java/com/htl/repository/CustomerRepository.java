package com.htl.repository;

import com.htl.entity.Customer;

public interface CustomerRepository {
    public Customer findById(long id);
}
