* MARKDOWN

**작동영상
URL link


**1. 테이블 

create table user(
id int auto_increment primary key,
username varchar(100) not null unique, -- 제약조건
password varchar(100) not null,
email varchar(100) not null,
address varchar(100) not null,
createDate timestamp
) engine = InnoDB default charset =utf8;

create table board(
id int auto_increment primary key,
userId int not null,
title varchar(100) not null,
content longtext,
readCount int default 0,
createDate timestamp,
foreign key (UserId) references user (id)
) engine = InnoDB default charset =utf8;

create table reply(
id int auto_increment primary key,
userID int,
BoardID int,
content varchar(300) not null,
createDate timestamp,
foreign key (UserId) references user (id),
foreign key (boardId) references board (id)
) engine = InnoDB default charset =utf8;

