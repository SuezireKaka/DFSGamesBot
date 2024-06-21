create table sys_act(
	act		char(2) primary key,
	area	varchar(8),
	type	varchar(8),
	info	varchar(255)
);
create index idx_area on sys_act(area);