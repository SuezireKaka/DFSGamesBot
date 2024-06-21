insert into t_role(id, provider, name, info, dflt, allow)
select NEXT_PK('s_role') /*'0000'*/ id,
            '0000' provider,
            'member' name,
            'JMemo를 이용하기 위한 기본적인 역할' info,
            1 dflt,
            'PM' allow;

insert into t_role(id, provider, name, info, dflt, allow)
select NEXT_PK('s_role') /*'0001'*/ id,
            '0000' provider,
            'developer' name,
            'JMemo의 모든 것을 개발하는 역할' info,
            0 dflt,
            'SM' allow;

insert into t_role(id, provider, name, info, dflt, allow)
select NEXT_PK('s_role') /*'0002'*/ id,
            '0000' provider,
            'anonymous' name,
            '익명으로 활동하는 모두에게 열린 기본 역할' info,
            0 dflt,
            'AV' allow;
