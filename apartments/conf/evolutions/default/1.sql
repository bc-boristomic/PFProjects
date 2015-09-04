# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table apartment (
  id                        bigint auto_increment not null,
  name                      varchar(100),
  rent                      decimal(10,2),
  location                  varchar(500),
  square                    double,
  rooms                     integer(1),
  constraint pk_apartment primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table apartment;

SET FOREIGN_KEY_CHECKS=1;

