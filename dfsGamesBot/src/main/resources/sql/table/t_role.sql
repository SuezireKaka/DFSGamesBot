create table t_role(
	id			char(4) primary key,
	provider	char(4),
	name		varchar(16),
	info		varchar(200),
	dflt		tinyint
);
create index idx_by_provider_name on t_role(provider, name);