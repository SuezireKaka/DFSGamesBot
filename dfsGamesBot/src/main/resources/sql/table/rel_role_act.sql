create table rel_role_act (
	role		char(4),
	act			char(2)
);
create index idx_role on rel_role_act(role);