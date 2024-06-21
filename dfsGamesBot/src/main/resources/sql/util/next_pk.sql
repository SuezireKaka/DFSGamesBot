DELIMITER $$
CREATE OR REPLACE FUNCTION NEXT_PK(t_NAME VARCHAR(255)) RETURNS char(4)
BEGIN
	DECLARE unrecorded boolean;	
	DECLARE r_sequence char(4);

	select not exists(select num from t_sequence where NAME = t_NAME) into unrecorded;

	if (unrecorded) then

		INSERT INTO t_sequence(NAME) VALUES (t_NAME);
 	end if;

	UPDATE t_sequence SET NUM = NUM + 1  WHERE NAME = t_NAME;


	SELECT c.SEED INTO r_sequence
	  FROM t_sequence s, T_IDSEED c
	 WHERE s.NAME = t_NAME
	   and s.NUM = c.SEQ;
	
	RETURN r_sequence;
END;
$$
DELIMITER ;