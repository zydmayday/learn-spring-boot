package com.zydmayday.graphql;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

@SpringBootApplication
public class GraphqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlApplication.class, args);
    }

}

@Entity
@Table(name = "organization")
class Organization {
    @Id
    String orgCode;

    String orgName;
}

@Entity
@Table(name = "employee")
class Employee {
    @Id
    String empNo;

    @OneToOne
    @JoinColumn(name = "level5_org_code")
    Organization level5Org;

    @OneToOne
    @JoinColumn(name = "level6_org_code")
    Organization level6Org;

    @OneToOne
    @JoinColumn(name = "level7_org_code")
    Organization level7Org;
}

@Repository
interface OrganizationRepository extends CrudRepository<Organization, String> {
    Organization findByOrgCode(String orgCode);
}

@Repository
interface EmployeeRepository extends CrudRepository<Employee, String> {
    Employee findByEmpNo(String empNo);
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
    public Employee employeeByEmpNo(@Argument String empNo) {
        return employeeRepository.findByEmpNo(empNo);
    }
}
