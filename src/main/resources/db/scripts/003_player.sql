create table player
(
    id        int4         not null,
    elo       int4         default 400,
    name      varchar(255) not null,
    school_id int4,
    constraint plrpremkey primary key (id),
    constraint uniqname unique (name)
)
