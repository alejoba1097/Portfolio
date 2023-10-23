CREATE EXTERNAL SCHEMA kds
FROM KINESIS
IAM_ROLE 'arn:aws:iam::490388389030:role/RedshiftReadFromKinesis';

DROP MATERIALIZED VIEW IF EXISTS kinesis_data_view;

/*Creates a materialized view with the approximate_arrival_timestamp and parsed json data
The JSON_PARSE function returns a SUPER data type that can be then accessed with PartiQL*/
CREATE MATERIALIZED VIEW kinesis_data_view AUTO REFRESH YES AS
SELECT
    approximate_arrival_timestamp,
    JSON_PARSE(kinesis_data) as Data
FROM kds.test_kds
WHERE CAN_JSON_PARSE(kinesis_data);

REFRESH MATERIALIZED VIEW kinesis_data_view;

SELECT data FROM kinesis_data_view;

/*Returns the attribute list of the object[0] in the data column, by using the extension UNPIVOT of the FROM clause*/
SELECT attr AS attribute_name, JSON_TYPEOF(attribute) as value_type
FROM kinesis_data_view kds_view, UNPIVOT kds_view.data[0] attribute AT attr;

/*Returns the attribute_name and vale for each one of the objects in a raw_data (unpivot data) form*/
SELECT attr AS attribute_name, val AS object_value
FROM kinesis_data_view kds_view, kds_view.data objects, UNPIVOT objects AS val AT attr;

/*Enable case sensitive for "userId"*/
SET enable_case_sensitive_identifier TO true;

/*Select all the columns for all the objects*/
SELECT posts.id, posts.title, posts.body, posts."userId", posts.tags, posts.reactions
FROM kinesis_data_view kds_view, kds_view.data posts
ORDER BY "userId";
