# Add Category

# --- !Ups

create table category (
  id              bigint auto_increment not null,
  name            varchar(255),
  availability    tinyint(1) default 0,
  primary key (id)
);

# --- !Downs

drop table category;