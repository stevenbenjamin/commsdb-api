

CREATE TABLE IF NOT EXISTS  content_type (
    id bigserial PRIMARY KEY,
    name character varying(100) NOT NULL
);
CREATE TABLE IF NOT EXISTS  denial_reason (
    id bigserial PRIMARY KEY,
    name character varying(100) NOT NULL
);
CREATE TABLE IF NOT EXISTS  distribution_channel (
    id bigserial PRIMARY KEY,
    name character varying(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS  industry (
    id bigserial PRIMARY KEY,
    name character varying(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS  priority (
    id bigserial PRIMARY KEY,
    name character varying(100) NOT NULL
);
CREATE TABLE IF NOT EXISTS  risk_level (
    id bigserial PRIMARY KEY,
    name character varying(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS  comms_status (
    id bigserial PRIMARY KEY,
    name character varying(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS  comms_user (
    id bigserial PRIMARY KEY,
    password character varying(128) NOT NULL,
    last_login timestamp with time zone DEFAULT NOW(),
    user_name character varying(150) NOT NULL UNIQUE,
    first_name character varying(150) NOT NULL,
    last_name character varying(150) NOT NULL,
    roles character varying NOT NULL,
    email character varying NOT NULL
);

CREATE TABLE IF NOT EXISTS  form (
    id bigserial PRIMARY KEY,
    name character varying NOT NULL,
    description text
);

CREATE TABLE IF NOT EXISTS  form_field (
    id bigserial PRIMARY KEY,
    name character varying NOT NULL,
    required boolean NOT NULL,
    field_type character varying NOT NULL,
    form_id bigint REFERENCES form(id) ON DELETE CASCADE,
    description character varying,
    extra_data jsonb
);

CREATE TABLE IF NOT EXISTS  partner (
    id bigserial PRIMARY KEY,
    name character varying NOT NULL,
    contact_email character varying(254) NOT NULL,
    industry_id bigint REFERENCES industry(id)
);

CREATE TABLE IF NOT EXISTS  rule (
    id bigserial PRIMARY KEY,
    name character varying NOT NULL,
    description text NOT NULL,
    rule_type character varying NOT NULL,
    field_name character varying,
    expr character varying NOT NULL
);

CREATE TABLE IF NOT EXISTS form_field_rule_m2m (
    id bigserial PRIMARY KEY,
    rule_id bigserial REFERENCES rule(id),
    form_id bigserial REFERENCES form(id)
);

CREATE TABLE IF NOT EXISTS  submission (
    id bigserial PRIMARY KEY,
    title character varying,
    flagged boolean NOT NULL,
    flag_reason text,
    data text,
    created_time timestamp with time zone NOT NULL DEFAULT NOW(),
    last_modified_time timestamp with time zone NOT NULL DEFAULT NOW(),
    content_type_id bigint REFERENCES content_type(id),
    creator_id bigint REFERENCES comms_user(id),
    industry_id bigint REFERENCES industry(id),
    partner_id bigint REFERENCES partner(id),
    priority_id bigint REFERENCES priority(id),
    schema_id bigint REFERENCES form(id),
    status_id bigint REFERENCES comms_status(id)
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
    name character varying,
    description text,
    data text,
    url character varying(200),
    created_time timestamp with time zone NOT NULL DEFAULT NOW(),
    last_modified_time timestamp with time zone NOT NULL DEFAULT NOW(),
    content_type_id bigint REFERENCES content_type(id),
    creator_id bigint REFERENCES comms_user(id),
    submission_id bigint REFERENCES submission(id)
);

CREATE TABLE IF NOT EXISTS  compliance_risk (
    id bigserial PRIMARY KEY,
    name varchar NOT NULL,
    suggested_edits text NOT NULL,
    risk_level_id bigint REFERENCES risk_level(id),
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

