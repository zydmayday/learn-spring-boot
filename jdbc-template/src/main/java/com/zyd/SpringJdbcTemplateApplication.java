package com.zyd;

import com.zyd.models.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringJdbcTemplateApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(SpringJdbcTemplateApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcTemplateApplication.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) {
        jdbcTemplate.execute("drop table customers if exists");
        jdbcTemplate.execute("create table customers (id serial, first_name varchar(255), last_name varchar(255))");

        List<Object[]> spliteUpNames = Stream.of("Zhang Yidong", "Li Hui")
                                             .map(name -> name.split(" "))
                                             .collect(Collectors.toList());
        jdbcTemplate.batchUpdate("insert into customers(first_name, last_name) values (?, ?)", spliteUpNames);
        jdbcTemplate.query("select id, first_name, last_name from customers where first_name=?",
                           new Object[]{"Zhang"},
                           (rs, rowNum) -> new Customer(rs.getLong("id"),
                                                        rs.getString("first_name"),
                                                        rs.getString("last_name")))
                    .forEach(customer -> log.info(customer.toString()));
    }
}
