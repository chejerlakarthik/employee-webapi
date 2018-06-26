alter table dbo.ASSETS drop constraint FKph0scyhd8gfqingv9jkgmf26k;
alter table dbo.EMPLOYEE drop constraint FKfvt83ye0flecxfewcniue0n9n;
drop table if exists dbo.ASSETS cascade;
drop table if exists dbo.DEPARTMENT cascade;
drop table if exists dbo.EMPLOYEE cascade;
create table dbo.ASSETS (ASSET_ID  bigserial not null, ASSET_TYPE varchar(255), EMP_ID int4, primary key (ASSET_ID));
create table dbo.DEPARTMENT (DEPARTMENT_ID  serial not null, DEPARTMENT_NAME varchar(255), DEPARTMENT_DESC varchar(255), primary key (DEPARTMENT_ID));
create table dbo.EMPLOYEE (EMP_ID  serial not null, CUBICLE_ID int8, EMP_NAME varchar(255), EMP_EMPLOYER varchar(255), LAST_MODIFIED timestamp, salary float8, DEPARTMENT_ID int4, primary key (EMP_ID));
alter table dbo.DEPARTMENT add constraint uk_department_name unique (DEPARTMENT_NAME);
alter table dbo.EMPLOYEE add constraint uk_cubicle unique (CUBICLE_ID);
alter table dbo.ASSETS add constraint FKph0scyhd8gfqingv9jkgmf26k foreign key (EMP_ID) references dbo.EMPLOYEE;
alter table dbo.EMPLOYEE add constraint FKfvt83ye0flecxfewcniue0n9n foreign key (DEPARTMENT_ID) references dbo.DEPARTMENT;



delete from dbo.assets;
delete from dbo.employee;
delete from dbo.department;


insert into dbo.department (DEPARTMENT_ID, DEPARTMENT_NAME, DEPARTMENT_DESC) values (1,'IT', 'Information Technology');
insert into dbo.department (DEPARTMENT_ID, DEPARTMENT_NAME, DEPARTMENT_DESC) values (2,'HR', 'Human Resources');
insert into dbo.department (DEPARTMENT_ID, DEPARTMENT_NAME, DEPARTMENT_DESC) values (3,'SC', 'Security');
insert into dbo.department (DEPARTMENT_ID, DEPARTMENT_NAME, DEPARTMENT_DESC) values (4,'TL', 'Transport & Logistics');
                                                                      

insert into dbo.employee (EMP_ID, CUBICLE_ID, DEPARTMENT_ID, EMP_NAME, EMP_EMPLOYER, LAST_MODIFIED, salary) values (1,1001, 1, 'Karthik', 'HONEYWELL', '2018-06-10 23:45:10.991',5000.00);
insert into dbo.employee (EMP_ID, CUBICLE_ID, DEPARTMENT_ID, EMP_NAME, EMP_EMPLOYER, LAST_MODIFIED, salary) values (2,1002, 2, 'Aarthi', 'TCS', '2018-06-10 23:45:10.991',5000.00);
insert into dbo.employee (EMP_ID, CUBICLE_ID, DEPARTMENT_ID, EMP_NAME, EMP_EMPLOYER, LAST_MODIFIED, salary) values (3,1003, 2, 'Daksha', 'DELOITTE', '2018-06-10 23:45:10.991',5000.00);
insert into dbo.employee (EMP_ID, CUBICLE_ID, DEPARTMENT_ID, EMP_NAME, EMP_EMPLOYER, LAST_MODIFIED, salary) values (4,1004, 3, 'Archana', 'ORACLE', '2018-06-10 23:45:10.991',5000.00);
insert into dbo.employee (EMP_ID, CUBICLE_ID, DEPARTMENT_ID, EMP_NAME, EMP_EMPLOYER, LAST_MODIFIED, salary) values (5,1005, 4, 'Sanjeev', 'DELOITTE', '2018-06-10 23:45:10.991',5000.00);
insert into dbo.employee (EMP_ID, CUBICLE_ID, DEPARTMENT_ID, EMP_NAME, EMP_EMPLOYER, LAST_MODIFIED, salary) values (6,1006, 4, 'Uday Shankar', 'DELOITTE', '2018-06-10 23:45:10.991',5000.00);


insert into dbo.assets (ASSET_ID, ASSET_TYPE, EMP_ID) values (1,'Laptop', 1);
insert into dbo.assets (ASSET_ID, ASSET_TYPE, EMP_ID) values (2,'Laptop', 2);
insert into dbo.assets (ASSET_ID, ASSET_TYPE, EMP_ID) values (3,'Laptop', 3);
insert into dbo.assets (ASSET_ID, ASSET_TYPE, EMP_ID) values (4,'Laptop', 4);
insert into dbo.assets (ASSET_ID, ASSET_TYPE, EMP_ID) values (5,'Laptop', 5);
insert into dbo.assets (ASSET_ID, ASSET_TYPE, EMP_ID) values (6,'Laptop', 6);

Select setval('employee_emp_id_seq', 6, true);
Select setval('assets_asset_id_seq', 6, true);
Select setval('department_department_id_seq', 4, true);