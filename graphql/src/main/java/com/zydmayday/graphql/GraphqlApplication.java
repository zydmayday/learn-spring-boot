package com.zydmayday.graphql;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.List;

@SpringBootApplication
public class GraphqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlApplication.class, args);
    }

}

@ToString
@Entity
@Table(name = "organization")
class Organization {
    @Id
    String orgCode;

    String orgName;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class SalaryId {

    String empNo;

    String ymd;
}

@IdClass(SalaryId.class)
@ToString
@Entity
@Table(name = "salary")
class Salary {
    @Id
    String empNo;

    @Id
    String ymd;

    int salary;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "empNo", insertable = false, updatable = false),
            @JoinColumn(name = "ymd", insertable = false, updatable = false)
    })
    Employee employee;
}

class EmployeeId {
    String empNo;
    String ymd;
}

@IdClass(EmployeeId.class)
@ToString
@Entity
@Table(name = "employee")
class Employee {
    @Id
    String empNo;

    @Id
    String ymd;

    @Fetch(FetchMode.JOIN)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "level5_org_code")
    Organization level5Org;

    @Fetch(FetchMode.JOIN)
    @OneToOne
    @JoinColumn(name = "level6_org_code")
    Organization level6Org;

    @Fetch(FetchMode.JOIN)
    @OneToOne
    @JoinColumn(name = "level7_org_code")
    Organization level7Org;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.LAZY)
    List<Salary> salaries;
}

@Repository
interface OrganizationRepository extends CrudRepository<Organization, String> {
    Organization findByOrgCode(String orgCode);
}

@Repository
interface EmployeeRepository extends CrudRepository<Employee, String> {
    List<Employee> findByEmpNo(String empNo);
}

@Controller
class OrganizationGraphqlController {

    public OrganizationGraphqlController(OrganizationRepository organizationRepository,
                                         EmployeeRepository employeeRepository) {
        this.organizationRepository = organizationRepository;
        this.employeeRepository = employeeRepository;
    }

    private final OrganizationRepository organizationRepository;
    private final EmployeeRepository employeeRepository;

    @QueryMapping
    public Organization organizationByOrgCode(@Argument String orgCode) {
        System.out.println(organizationRepository.findAll());
        Organization org = organizationRepository.findByOrgCode(orgCode);
        System.out.println(org);
        return org;
    }

    @QueryMapping
    public List<Employee> employeeByEmpNo(@Argument String empNo) {
        return employeeRepository.findByEmpNo(empNo);
    }
}
