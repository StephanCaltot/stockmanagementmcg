CREATE SCHEMA stock;
SET CURRENT SCHEMA stock;
--
DELETE FROM ROOM;
DELETE FROM MATERIAL;

-- INSERT INTO stock.croom VALUES (1,'U026');
-- INSERT INTO stock.croom VALUES (2,'W207');
-- INSERT INTO stock.croom VALUES (3,'T201');
-- INSERT INTO stock.croom VALUES (4,'Y105');
-- INSERT INTO stock.croom VALUES (5,'T003');
--
-- INSERT INTO stock.cmaterial VALUES (1,'tableau',5,'ASUS',TRUE);
-- INSERT INTO stock.cmaterial VALUES (2,'projecteur',5);
--
--
-- UPDATE ID_GEN_ROOM SET GEN_VALUE = (SELECT count(ID) FROM croom) WHERE GEN_KEY='ROOM_ID';
-- UPDATE ID_GEN_MATERIAL SET GEN_VALUE = (SELECT count(ID) FROM cmaterial) WHERE GEN_KEY='MATERIAL_ID';

