alter table game
    add constraint FIRST_PLAYER_ID_FK foreign key (firstplayer_id) references player;
alter table game
    add constraint SECOND_PLAYER_ID_FK foreign key (secondplayer_id) references player;
alter table game
    add constraint WINNER_ID_FK foreign key (winner_id) references player;
