create table T_I1(id varchar(4));


insert into T_I1(id) values ('a');
insert into T_I1(id) values ('b');
insert into T_I1(id) values ('c');
insert into T_I1(id) values ('d');
insert into T_I1(id) values ('e');
insert into T_I1(id) values ('f');
insert into T_I1(id) values ('g');
insert into T_I1(id) values ('h');
insert into T_I1(id) values ('i');
insert into T_I1(id) values ('j');
insert into T_I1(id) values ('k');
insert into T_I1(id) values ('l');
insert into T_I1(id) values ('m');
insert into T_I1(id) values ('n');
insert into T_I1(id) values ('o');
insert into T_I1(id) values ('p');
insert into T_I1(id) values ('q');
insert into T_I1(id) values ('r');
insert into T_I1(id) values ('s');
insert into T_I1(id) values ('t');
insert into T_I1(id) values ('u');
insert into T_I1(id) values ('v');
insert into T_I1(id) values ('w');
insert into T_I1(id) values ('x');
insert into T_I1(id) values ('y');
insert into T_I1(id) values ('z');
insert into T_I1(id) values ('0');
insert into T_I1(id) values ('1');
insert into T_I1(id) values ('2');
insert into T_I1(id) values ('3');
insert into T_I1(id) values ('4');
insert into T_I1(id) values ('5');
insert into T_I1(id) values ('6');
insert into T_I1(id) values ('7');
insert into T_I1(id) values ('8');
insert into T_I1(id) values ('9');
 
create table T_I2(id varchar(4));
create table T_I4(id varchar(4));

--2글자짜리 만들기
INSERT INTO T_I2(id)
SELECT CONCAT(a.id, b.id)
FROM T_I1 a, T_I1 b
;
--4글자짜리 만들기
INSERT INTO T_I4(id)
SELECT CONCAT(a.id, b.id)
FROM T_I2 a, T_I2 b
;


create table T_IDSEED(
   SEQ		integer primary key,
   SEED		char(4)
);
	
-- 문자랑 순번을  T_ID_SEED테이블에 집어넣기	
insert into T_IDSEED(SEED, SEQ)
	SELECT tid.id iid, @ROWNUM:=@ROWNUM+1 AS rowNum
	FROM T_I4 as tid, (SELECT @ROWNUM:=0) AS R
	order by iid asc;

