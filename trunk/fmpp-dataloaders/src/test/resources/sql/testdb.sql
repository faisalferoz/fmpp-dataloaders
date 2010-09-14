create table ALL_NUMERIC (a TINYINT, b SMALLINT, c INTEGER, d BIGINT, e NUMERIC);

create table ALL_CHAR (a CHAR(1), b VARCHAR(255), c CLOB(65535));

create table ALL_LOB (a BLOB(65535), b CLOB(65535));

create table ALL_DATE (a DATE, b TIME, c TIMESTAMP);

create table ALL_DATATYPE (a TINYINT, b SMALLINT, c INTEGER, d BIGINT, e NUMERIC,
						   f CHAR(1), g VARCHAR(255), h CLOB(65535),
						   i BLOB(65535), j CLOB(65535),
						   k DATE, l TIME, m TIMESTAMP
						   );
						   
create table ARCHIVE_DATA (num_col NUMERIC,
						   varchar_col VARCHAR(255),
						   blob_col BLOB(65535),
						   created_at DATE,
						   );