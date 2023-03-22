insert into ORGANIZATION  (org_code, org_name) values ('orgCode1', 'orgName1'),('orgCode2', 'orgName2'),('orgCode3', 'orgName3');

insert into EMPLOYEE (emp_no, ymd, level5_org_code, level6_org_code, level7_org_code) values
('emp1', '20220101', 'orgCode1', 'orgCode2', 'orgCode3'),
('emp1', '20220201', 'orgCode1', 'orgCode2', 'orgCode3'),
('emp1', '20220301', 'orgCode1', 'orgCode2', 'orgCode3');

insert into SALARY (emp_no, ymd, salary) values
('emp1', '20220101', 1000),
('emp1', '20220201', 2000),
('emp1', '20220301', 3000);
