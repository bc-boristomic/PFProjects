# --- !Ups

create table article (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  sku                       varchar(255) not null,
  description               TEXT,
  constraint uq_article_sku unique (sku),
  constraint pk_article primary key (id))
;

create table price (
  id                        bigint auto_increment not null,
  cost                      FLOAT,
  price_date                datetime,
  article_id                bigint,
  constraint pk_price primary key (id))
;

alter table price add constraint fk_price_article_1 foreign key (article_id) references article (id) on delete restrict on update restrict;
create index ix_price_article_1 on price (article_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table article;

drop table category;

drop table price;

SET FOREIGN_KEY_CHECKS=1;

