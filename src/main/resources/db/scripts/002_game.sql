create table game
(
    id                        int8 not null,
    first_player_elo_changed  int4 not null,
    game_finished             timestamp,
    second_player_elo_changed int4 not null,
    firstplayer_id            int4,
    secondplayer_id           int4,
    constraint schprimkey primary key (id)
)
