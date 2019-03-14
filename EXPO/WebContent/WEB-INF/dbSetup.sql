--drop schema if exists expo cascade;

create schema expo;
set SEARCH_PATH to expo;

create table stand (
standId serial,
name varchar(50) unique not null,
imageurl varchar(255),
constraint standId primary key (standId)
);

create table vote (
voteId serial,
userName varchar(60),
stand int,
score int check (score>=0 and score<=5),
constraint voteId primary key (voteId),
constraint stand foreign key (stand) references stand(standId)
);

