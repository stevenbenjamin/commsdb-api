
INSERT INTO content_type (name) VALUES ('pdf');
INSERT INTO content_type (name) VALUES ('text');
INSERT INTO content_type (name) VALUES ('video');
INSERT INTO priority (name) VALUES ('normal');
INSERT INTO priority (name) VALUES ('urgent');
INSERT INTO risk_level (name) VALUES ('low');
INSERT INTO risk_level (name) VALUES ('medium');
INSERT INTO risk_level (name) VALUES ('high');
INSERT INTO comms_status(name) values ('submitted');
INSERT INTO comms_status(name) values ('waiting');
INSERT INTO comms_status(name) values ('approved');
--INSERT INTO form(name, description) VALUES ('default', 'default schema');
--INSERT INTO form_field(name, required, field_type, form_id, description) VALUES
--('required_string', true, 'string', 1, 'a required string');
--INSERT INTO form_field(name, required, field_type, form_id, description, extra_data) VALUES
--('required_choice', true, 'choice', 1, 'a required choice', '["a","b","c" ]');
--INSERT INTO form_field(name, required, field_type, form_id, description) VALUES
--('number', true, 'number', 1, 'numeric');

INSERT INTO RULE(name, description, rule_type, field_name, expr) values ('eq', 'a == "X" example', 'eq', 'a','X');
INSERT INTO RULE(name, description, rule_type, expr) values ('contains X', 'contains rule example', 'contains', 'X');
