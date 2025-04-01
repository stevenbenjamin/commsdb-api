

INSERT INTO action (name) VALUES ('forward');
INSERT INTO action (name) VALUES ('deny');
INSERT INTO action (name) VALUES ('approve');
INSERT INTO content_type (name) VALUES ('pdf');
INSERT INTO content_type (name) VALUES ('text');
INSERT INTO content_type (name) VALUES ('video');
INSERT INTO priority (name) VALUES ('normal');
INSERT INTO priority (name) VALUES ('urgent');
INSERT INTO risk_level (name) VALUES ('low');
INSERT INTO risk_level (name) VALUES ('medium');
INSERT INTO risk_level (name) VALUES ('high');
INSERT INTO comms_role (name) VALUES ('submitter');
INSERT INTO comms_role (name) VALUES ('traffic');
INSERT INTO comms_role (name) VALUES ('approver');
INSERT INTO comms_role (name) VALUES ('admin');
INSERT INTO comms_status(name) values ('submitted');
INSERT INTO comms_status(name) values ('waiting');
INSERT INTO comms_status(name) values ('approved');
INSERT INTO FLEX_SCHEMA(name, description) VALUES ('default', 'default schema');
INSERT INTO FLEX_SCHEMA_FIELD(name, required, field_type, flex_schema_id, description) VALUES
('required_string', true, 'string', 1, 'a required string');
INSERT INTO FLEX_SCHEMA_FIELD(name, required, field_type, flex_schema_id, description, extra_data) VALUES
('required_choice', true, 'choice', 1, 'a required choice', '["a","b","c" ]');
INSERT INTO FLEX_SCHEMA_FIELD(name, required, field_type, flex_schema_id, description) VALUES
('number', true, 'number', 1, 'numeric');

INSERT INTO RULE(name, description, rule_type, field_name, rule) values ('eq', 'a == "X" example', 'eq', 'a','X');
INSERT INTO RULE(name, description, rule_type, rule) values ('contains X', 'contains rule example', 'contains', 'X');
