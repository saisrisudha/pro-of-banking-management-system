create database bank;
use bank;
create table customer (cusid int primary key,cusname varchar(20),cuspin int,cusamt int);
desc customer;
select *from customer;

select * from customer where cusname="teju";
update customer set cusamout=5000 where cusid=1011;