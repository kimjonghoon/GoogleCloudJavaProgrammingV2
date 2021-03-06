SpringBbs
============

## A Bulletin Board Program with 
* Spring MVC
* MyBatis-Spring
* Spring-Security
* Bean Validation
* i18n
* Apache Tiles

## Database Design (The member and authorities tables are not used, but previous versions used these tables, so I did not remove them.)

	mysql --user=root --password mysql
	
	create user 'java'@'%' identified by 'school';
	grant all privileges on *.* to 'java'@'%';
	
	create database javaskool;
	exit;
	
	mysql --user=java --password javaskool
	
	create table member (
	    email varchar(60) PRIMARY KEY,
	    passwd varchar(200) NOT NULL,
	    name varchar(20) NOT NULL,
	    mobile varchar(20)
	);
	
	create table authorities (
	    email VARCHAR(60) NOT NULL,
	    authority VARCHAR(20) NOT NULL,
	    CONSTRAINT fk_authorities FOREIGN KEY(email) REFERENCES member(email)
	);
	
	CREATE UNIQUE INDEX ix_authorities ON authorities(email,authority); 
	
	create table board (
	    boardcd varchar(20),
	    boardnm varchar(40) NOT NULL,
	    boardnm_ko varchar(40) NOT NULL,
	    constraint PK_BOARD PRIMARY KEY(boardcd)
	);
	
	create table article (
	    articleno int NOT NULL AUTO_INCREMENT,
	    boardcd varchar(20),
	    title varchar(200) NOT NULL,
	    content text NOT NULL,
	    email varchar(60),
	    hit bigint,
	    regdate datetime,
	    constraint PK_ARTICLE PRIMARY KEY(articleno),
	    constraint FK_ARTICLE FOREIGN KEY(boardcd) REFERENCES board(boardcd)
	);
	
	create table comments (
	    commentno int NOT NULL AUTO_INCREMENT,
	    articleno int,
	    email varchar(60),
	    memo varchar(4000) NOT NULL,
	    regdate datetime,
	    constraint PK_COMMENTS PRIMARY KEY(commentno)
	);
	
	create table attachfile (
	    attachfileno int NOT NULL AUTO_INCREMENT,
	    filename varchar(255) NOT NULL,
	    filetype varchar(255),
	    filesize bigint,
	    articleno int,
	    email varchar(60),
	    filekey varchar(255),
	    creation datetime,
	    constraint PK_ATTACHFILE PRIMARY KEY(attachfileno)
	);
	
	create table views (
	  no int primary key AUTO_INCREMENT,
	  articleNo int,
	  ip varchar(60),
	  yearMonthDayHour char(10),
	  unique key (articleNo, ip, yearMonthDayHour)
	);

## How to run (The file upload test is only available in the Java 8 environment.)
**mvn appengine:run**

## Note
Even if you are logged in as an administrator, the **Admin** may not be displayed in the main menu.
This is due to the unique characteristics of the Datastore.
The **Admin** menu will appear shortly.
You can use this menu to create a new bulletin board.