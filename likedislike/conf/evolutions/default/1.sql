# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table like_model (
  id                        integer auto_increment not null,
  status                    tinyint(1) default 0,
  ip_address                varchar(255),
  constraint pk_like_model primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table like_model;

SET FOREIGN_KEY_CHECKS=1;

