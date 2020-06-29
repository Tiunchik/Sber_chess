create table school
(
    id     int4         not null,
    called varchar(255) not null,
    constraint schlpremkey primary key (id),
    constraint uniqcalled unique (called)
)

