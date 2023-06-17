## some queries

```graphql
query getOrg {
  organizationByOrgCode(orgCode: "orgCode2") {
    orgCode,
    orgName
  }
}

query getNonExistOrg {
  organizationByOrgCode(orgCode: "999") {
    orgCode,
    orgName
  }
}

query getOnlyEmp {
  employeeByEmpNo(empNo: "emp1") {
    empNo
  }
}

query getNonExistEmp {
  employeeByEmpNo(empNo: "999") {
    empNo
  }
}

query getEmp {
  employeeByEmpNo(empNo: "emp1") {
    empNo,
    level5Org {
      orgCode,
      orgName
    },
    salaries {
      salary
    }
  }
}

query getEmpWithoutSalary {
  employeeByEmpNo(empNo: "emp1") {
    empNo,
    level5Org {
      orgCode,
      orgName
    }
  }
}
```