CREATE TABLE WORK_SITE(
                        ID                   UUID PRIMARY KEY,
                        COMMENT               VARCHAR(200),
                        CREATED_AT            timestamp,
                        UPDATED_AT           timestamp,
                        CREATED_BY           VARCHAR(200),
                        UPDATED_BY           VARCHAR(200)

);

CREATE TABLE TIMELINE
(
  ID             UUID PRIMARY KEY,
  FK_WORK_SITE   UUID,
  DATE           TIMESTAMP,
  TITLE          varchar(250),
  COMMENT        varchar(600),
  STATE          varchar(250),
  TYPE           varchar(250),
  CORRELATION_ID UUID,
  CREATED_BY     VARCHAR(200)
);

CREATE TABLE TIMELINE_EVENT_METADATA
(
  ID          UUID PRIMARY KEY,
  FK_TIMELINE UUID NOT NULL,
  KEY         varchar(100),
  VALUE       varchar(2000)
);
