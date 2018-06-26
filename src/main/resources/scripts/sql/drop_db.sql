alter table dbo.ASSETS drop constraint FKsrya1i796si1f2mdwm3jc775k;
alter table dbo.EMPLOYEE drop constraint FKfvt83ye0flecxfewcniue0n9n;
alter table dbo.EMPLOYEE_ASSETS drop constraint FKoxn8s9xnw9khcic3tr4hkcu7t;
alter table dbo.EMPLOYEE_ASSETS drop constraint FKnn2tnkxdqg0r87i5qul8uli8c;
drop table if exists dbo.ASSETS cascade;
drop table if exists dbo.DEPARTMENT cascade;
drop table if exists dbo.EMPLOYEE cascade;
drop table if exists dbo.EMPLOYEE_ASSETS cascade;