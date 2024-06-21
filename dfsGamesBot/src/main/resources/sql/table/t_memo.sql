create table t_memo(
	id			char(4) primary key,
	writer		char(4),
	title		varchar(255),
	public		tinyint,
	memo		text,
	reg_dt		timestamp default current_timestamp(),
	upt_dt		timestamp default current_timestamp() on update current_timestamp()
);

create index idx_writer on t_memo(writer);