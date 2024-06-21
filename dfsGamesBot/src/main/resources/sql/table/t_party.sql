create table t_party(
	id			char(4) primary key,
	discrim		char(1),
	name		varchar(255),
	dis_id		varchar(255) /* 유저만 존재 */,
	reg_dt		timestamp default current_timestamp(),
	upt_dt		timestamp default current_timestamp() on update current_timestamp()
);
create index idx_by_discrim_identity on t_party(discrim, identity);
create unique index idx_by_discord_id on t_party(dis_id);

create table t_group(
	id			char(4) primary key,
	open		tinyint,
	intro		varchar(1000)
);