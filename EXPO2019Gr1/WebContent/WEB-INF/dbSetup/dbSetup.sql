--drop schema if exists expo cascade;

create schema expo;
set SEARCH_PATH to expo;

create table stand (
standId serial,
name varchar(50) unique,
imageurl varchar(255),
epostadmin varchar(50) unique not null,
pin char(4) not null,
constraint standId primary key (standId)
);

create table expouser (
epost varchar(100) primary key
);

create table vote (
voteId serial,
userName varchar(100),
stand int,
score int check (score>=0 and score<=5),
constraint voteId primary key (voteId),
constraint userName foreign key (userName) references expouser(epost),
constraint stand foreign key (stand) references stand(standId)
);

create view standoverview as 
select name,avg(score),count(userName) 
from expo.stand,expo.vote 
where stand.standId = vote.stand 
group by stand.name;