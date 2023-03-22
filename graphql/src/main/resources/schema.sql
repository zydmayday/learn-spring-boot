create table organization (
  org_code varchar(8),
  org_name varchar(8)
);

create table Employee (
  emp_no varchar(8),
  ymd varchar(8),
  level5_org_code varchar(8),
  level6_org_code varchar(8),
  level7_org_code varchar(8)
);

create table Salary (
  emp_no varchar(8),
  ymd varchar(8),
  salary int
);
