package com.zydmayday.graphql;

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

record Organization (String orgCode, String orgName) {}

@Repository
interface OrganizationRepository extends CrudRepository<Organization, String> {
	Organization findByOrgCode(String orgCode);
}

@Controller
class OrganizationGraphqlController {

	public OrganizationGraphqlController(OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}

	private OrganizationRepository organizationRepository;
	@QueryMapping
	public Organization organizationByOrgCode(@Argument String orgCode) {
		return organizationRepository.findByOrgCode(orgCode);
	}
}
