# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table role (
  id                        bigserial not null,
  name                      varchar(10),
  constraint uq_role_name unique (name),
  constraint pk_role primary key (id))
;

create table app_user (
  id                        bigserial not null,
  email                     varchar(50) not null,
  password                  varchar(255) not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  access_level              integer,
  create_date               timestamp,
  created_by                varchar(100),
  update_date               timestamp,
  updated_by                varchar(100),
  constraint uq_app_user_email unique (email),
  constraint pk_app_user primary key (id))
;


create table app_user_role (
  app_user_id                    bigint not null,
  role_id                        bigint not null,
  constraint pk_app_user_role primary key (app_user_id, role_id))
;



alter table app_user_role add constraint fk_app_user_role_app_user_01 foreign key (app_user_id) references app_user (id);

alter table app_user_role add constraint fk_app_user_role_role_02 foreign key (role_id) references role (id);

# --- !Downs

drop table if exists role cascade;

drop table if exists app_user cascade;

drop table if exists app_user_role cascade;

