package com.example.demo;

import java.util.List;

public interface CustomerRepository {
    List<Customer> findAll();
    String findByName(String name);
}
