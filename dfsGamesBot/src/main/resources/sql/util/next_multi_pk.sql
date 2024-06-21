DELIMITER $$
CREATE OR REPLACE FUNCTION NEXT_MULTI_PK(t_NAME VARCHAR(255), summon_cnt tinyint) RETURNS varchar(10000)
BEGIN
	DECLARE unrecorded boolean;
	DECLARE old_seed int;
	DECLARE r_sequence varchar(10000);

	select 0 into old_seed;

	select not exists(select num from t_sequence where NAME = t_NAME) into unrecorded;

	if (unrecorded) then
		INSERT INTO t_sequence(NAME) VALUES (t_NAME);
	else
		select num into old_seed from t_sequence where NAME = t_NAME;
 	end if;

	UPDATE t_sequence SET NUM = NUM + summon_cnt  WHERE NAME = t_NAME;

	SELECT group_concat(c.SEED SEPARATOR ', ') INTO r_sequence
	  FROM t_sequence s, T_IDSEED c
	 WHERE s.NAME = t_NAME
	   AND c.seq > old_seed AND c.seq <= old_seed + summon_cnt;
	
	RETURN r_sequence;
END;
$$
DELIMITER ;