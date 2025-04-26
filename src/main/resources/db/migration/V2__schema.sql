
CREATE TABLE IF NOT EXISTS  industry (
    id bigserial PRIMARY KEY,
    name varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS  comms_user (
    id bigserial PRIMARY KEY,
    password varchar(128) NOT NULL,
    last_login timestamp with time zone DEFAULT NOW(),
    user_name varchar(150) NOT NULL UNIQUE,
    first_name varchar(150) NOT NULL,
    last_name varchar(150) NOT NULL,
    roles varchar NOT NULL,
    email varchar NOT NULL
);

CREATE SEQUENCE comms_user_seq OWNED BY comms_user.id;

CREATE TABLE IF NOT EXISTS  form (
    id bigserial PRIMARY KEY,
    name varchar NOT NULL,
    description text
);

CREATE SEQUENCE form_seq OWNED BY form.id;

CREATE TABLE IF NOT EXISTS  form_field (
    id bigserial PRIMARY KEY,
    name varchar NOT NULL,
    required boolean NOT NULL,
    field_type varchar NOT NULL,
    form_id bigint REFERENCES form(id) ON DELETE CASCADE,
    description varchar,
    extra_data jsonb
);

CREATE SEQUENCE form_field_seq OWNED BY form_field.id;

CREATE TABLE IF NOT EXISTS  partner (
    id bigserial PRIMARY KEY,
    name varchar NOT NULL,
    contact_email varchar(254) NOT NULL,
    industry_id bigint REFERENCES industry(id)
);

CREATE SEQUENCE partner_seq OWNED BY partner.id;

CREATE TABLE IF NOT EXISTS  rule (
    id bigserial PRIMARY KEY,
    name varchar NOT NULL,
    description text NOT NULL,
    rule_type varchar NOT NULL,
    form_id bigint REFERENCES form(id) ON DELETE CASCADE,
    field_name varchar,
    expr varchar NOT NULL
);

CREATE SEQUENCE rule_seq OWNED BY rule.id;

CREATE TABLE IF NOT EXISTS on_rule_action (
    id bigserial PRIMARY KEY,
    name varchar NOT NULL,
    description text,
    rule_id bigint REFERENCES rule(id) ON DELETE CASCADE,
    action_type varchar NOT NULL,
    extra_data jsonb
);

CREATE TABLE IF NOT EXISTS  submission (
    id bigserial PRIMARY KEY,
    title varchar,
    flagged boolean NOT NULL,
    flag_reason text,
    data text,
    created_time timestamp with time zone NOT NULL DEFAULT NOW(),
    last_modified_time timestamp with time zone NOT NULL DEFAULT NOW(),
    content_type varchar,
    creator_id bigint REFERENCES comms_user(id),
    industry_id bigint REFERENCES industry(id),
    partner_id bigint REFERENCES partner(id),
    priority varchar,
    schema_id bigint REFERENCES form(id),
    status varchar
);

CREATE OR REPLACE FUNCTION trigger_set_timestamp()
RETURNS TRIGGER AS $$
BEGIN
  NEW.last_modified_time = NOW();
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_submission_timestamp
BEFORE UPDATE ON submission
FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();


CREATE TABLE IF NOT EXISTS  attachement (
    id bigserial PRIMARY KEY,
    name varchar,
    description text,
    data text,
    url varchar(200),
    created_time timestamp with time zone NOT NULL DEFAULT NOW(),
    last_modified_time timestamp with time zone NOT NULL DEFAULT NOW(),
    content_type varchar,
    creator_id bigint REFERENCES comms_user(id),
    submission_id bigint REFERENCES submission(id)
);

CREATE TABLE IF NOT EXISTS  compliance_risk (
    id bigserial PRIMARY KEY,
    name varchar NOT NULL,
    suggested_edits text NOT NULL,
    risk_level varchar,
    submission_id bigserial REFERENCES submission(id)
);

CREATE TABLE IF NOT EXISTS  review (
    id bigserial PRIMARY KEY,
    sequence_num bigserial,
    reviewer_id bigint REFERENCES comms_user(id),
    submission_id bigint REFERENCES submission(id),
    content text NOT NULL
);

CREATE TABLE IF NOT EXISTS  thread (
    id bigserial PRIMARY KEY,
    submission_id bigserial
);

CREATE TABLE IF NOT EXISTS  thread_message (
    id bigserial PRIMARY KEY,
    parent_id bigint NOT NULL,
    content text NOT NULL,
    thread_id bigserial
);

CREATE SEQUENCE thread_message_seq OWNED BY thread_message.id;

CREATE TABLE IF NOT EXISTS event (
    id bigserial PRIMARY KEY,
    description varchar,
    submission_id bigserial REFERENCES submission(id),
    extra_data jsonb,
    created_time timestamp with time zone NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS recommendation (
    id bigserial PRIMARY KEY,
    description varchar,
    action_type varchar,
    submission_id bigserial REFERENCES submission(id),
    extra_data jsonb,
    created_time timestamp with time zone NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS action (
    id bigserial PRIMARY KEY,
    description varchar,
    action_type varchar,
    submission_id bigserial REFERENCES submission(id),
    extra_data jsonb,
    created_time timestamp with time zone NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS task_queue (
    id bigserial PRIMARY KEY,
    user_id bigserial REFERENCES comms_user(id),
    description varchar,
    reason varchar,
    submission_id bigserial REFERENCES submission(id),
    extra_data jsonb,
    created_time timestamp with time zone NOT NULL DEFAULT NOW()
);


