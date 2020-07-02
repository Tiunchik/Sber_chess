drop table if exists metriks cascade;
create table metriks
(
    id              bigserial    not null,
    event_timestamp timestamp    not null,
    name            varchar(255) not null,
    parameters      varchar(255),
    value           int8         not null,
    primary key (id)
);


