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