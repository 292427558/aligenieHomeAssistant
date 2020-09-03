drop table if exists oauth_client_token;
create table oauth_client_token (
    token_id VARCHAR(255),
    token LONG VARBINARY,
    authentication_id VARCHAR(255) PRIMARY KEY,
    user_name VARCHAR(255),
    client_id VARCHAR(255)
);

drop table if exists oauth_access_token;
create table oauth_access_token (
    token_id VARCHAR(255),
    token LONG VARBINARY,
    authentication_id VARCHAR(255) PRIMARY KEY,
    user_name VARCHAR(255),
    client_id VARCHAR(255),
    authentication LONG VARBINARY,
    refresh_token VARCHAR(255)
);

drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
    token_id VARCHAR(255),
    token LONG VARBINARY,
    authentication LONG VARBINARY
);

drop table if exists oauth_code;
create table oauth_code (
    code VARCHAR(255), authentication LONG VARBINARY
);

drop table if exists oauth_approvals;
create table oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt TIMESTAMP,
    lastModifiedAt datetime
);
