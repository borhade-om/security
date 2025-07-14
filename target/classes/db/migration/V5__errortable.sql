CREATE TABLE error_table (
    error_id BIGINT GENERATED  BY DEFAULT AS IDENTITY PRIMARY KEY ,
    message VARCHAR(255),
    row_number BIGINT
);