create database sample_board default character set utf8;

CREATE USER 'userBoard'@'localhost' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON sample_board.* TO 'userBoard'@'localhost';

CREATE USER 'userBoard'@'%' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON sample_board.* TO 'userBoard'@'%';

CREATE TABLE ATTACH(
	FULLNAME	VARCHAR(150) NOT NULL,
    BOARDSEQ	INT,
    REGDATE		TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY (FULLNAME)
) engine=InnoDB default character set = utf8;

select * from attach;
select count(fullname) from attach where boardseq=22;

SELECT LAST_INSERT_ID();

CREATE TABLE REPLY(
	SEQ			int auto_increment NOT NULL,
    BOARDSEQ	int,
    PARENTSEQ	VARCHAR(10),
    WRINTER		VARCHAR(50),
    CONTENT		VARCHAR(100),
    REGDATE		TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY (SEQ)
) engine=InnoDB default character set = utf8;

ALTER TABLE REPLY CHANGE PARENTSEQ PARENTSEQ VARCHAR(10);
DESC REPLY;

select * from reply;
select * from reply where seq=8 or parentseq=convert(8, char(10));
delete from reply where seq = 1;

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
select a.seq, a.writer, a.title, a.content, a.regdate, a.cnt, (select count(fullname) from attach where boardseq=a.seq) as fCnt from board as a where 1=1 and (title like '%수정%' or content like '%은둑%');
select * from board limit 2,2;

SELECT 
			* 
		FROM 
			BOARD 
		WHERE
			1=1
		 
		 
		 
			AND CONTENT LIKE '%수정%'
		 
		ORDER BY SEQ DESC;

SELECT 
			COUNT(*) 
		FROM 
			BOARD 
		WHERE
			1=1
		 
			AND (TITLE LIKE '%%' OR CONTENT LIKE '%%')
		;

update board set cnt=cnt+1 where seq=5;

describe board;

insert into board (
    WRITER,
    TITLE,
    CONTENT
)
values(
    'TEST',
    'TITLE1818',
    'CONTENT1818'
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