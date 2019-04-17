create database sample_board default character set utf8;

CREATE USER 'userBoard'@'localhost' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON sample_board.* TO 'userBoard'@'localhost';

CREATE USER 'userBoard'@'%' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON sample_board.* TO 'userBoard'@'%';

CREATE TABLE BOARD(
	SEQ			int AUTO_INCREMENT NOT NULL,
    WRITER		VARCHAR(50),
	TITLE		VARcHAR(20) NOT NULL,
	CONTENT		VARCHAR(1000),
	REGDATE		TIMESTAMP DEFAULT NOW(),
	CNT			int DEFAULT 0,
	PRIMARY KEY (SEQ)
) engine=InnoDB default character set = utf8;

select * from board;

describe board;

insert into board (
    WRITER,
    TITLE,
    CONTENT
)
values(
    'TEST',
    'TITLE22',
    'CONTENT22'
);

drop table board;

select version();

select now() as now;


CREATE TABLE USERES(
	USERID		VARCHAR(20) NOT NULL,
    PASSWORD	VARCHAR(50) NOT NULL,
    NAME		VARCHAR(50) NOT NULL,
    ROLE		VARCHAR(20),
    PRIMARY KEY	(USERID)
) engine=InnoDB default character set = utf8;

select * from useres;

insert into 
	useres
values(
	"test",
    "1234",
    "관리자",
    "root"
);

insert into 
	useres
values(
	"user",
    "2345",
    "일반인",
    "user"
);