# Add article_category

# --- !Ups

create table article_category (
  id                        bigint auto_increment not null,
  create_date               datetime,
  article_id                bigint,
  category_id               bigint,
  constraint pk_article_category primary key (id))
;

alter table article_category add constraint fk_article_category_article_1 foreign key (article_id) references article (id) on delete restrict on update restrict;
create index ix_article_category_article_1 on article_category (article_id);
alter table article_category add constraint fk_article_category_category_2 foreign key (category_id) references category (id) on delete restrict on update restrict;
create index ix_article_category_category_2 on article_category (category_id);

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table article_category;

SET FOREIGN_KEY_CHECKS=1;