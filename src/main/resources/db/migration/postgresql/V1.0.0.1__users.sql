create table copsboot_user
(
    auth_server_id uuid,
    id             uuid not null,
    email          varchar(255),
    mobile_token   varchar(255),
    primary key (id)
);