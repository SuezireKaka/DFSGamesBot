insert into t_party(id, discrim, name)
select NEXT_PK('s_party') /*'0000'*/ id, 'G' discrim, 'JMemoOper' name

insert into t_group(id, open, intro)
values ('0000', 1, '모두 즐겁게 메모합시다ㅡ');

insert into t_party(id, discrim, selector)
select NEXT_PK('s_party') /*'0001'*/ id, 'U' discrim, 'suezirekaka' name;