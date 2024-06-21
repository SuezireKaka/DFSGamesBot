create table rel_party_role(
	party		char(4),
	role		char(4)
);

create index idx_party on rel_party_role(party);