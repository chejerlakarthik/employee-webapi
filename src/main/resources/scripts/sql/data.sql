delete from dbo.assets;
delete from dbo.employee;
delete from dbo.department;


insert into dbo.department values(1, 'HR',  'Human Resources');
insert into dbo.department values(2, 'TL',  'Transport and Logistics');
insert into dbo.department values(3, 'SEC',  'Security');
insert into dbo.department values(4, 'PRJ',  'Project');


insert into dbo.employee values (1, 1000 ,'Karthik', 'TCS', 5000.00, 1);
insert into dbo.employee values (2, 2000 ,'Aarthi', 'TCS', 5000.00, 2);
insert into dbo.employee values (3, 2001 ,'Daksha', 'TCS', 5000.00, 2);
insert into dbo.employee values (4, 3000 ,'Uday Shankar', 'TCS', 5000.00, 3);
insert into dbo.employee values (5, 4000 ,'Vihaan', 'TCS', 5000.00, 4);
insert into dbo.employee values (6, 4001 ,'Sanjeev', 'TCS', 5000.00, 4);