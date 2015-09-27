create user 'teammanager'@'localhost' identified by 'teammanager';
create database teammanager;
grant all on teammanager.* to 'teammanager'@'localhost';
use teammanager;
show tables;