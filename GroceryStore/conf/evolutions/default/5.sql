# Add image

# --- !Ups

create table image (
  id                        bigint auto_increment not null,
  public_id                 varchar(255),
  image_url                 varchar(255),
  secret_image_url          varchar(255),
  article_id                bigint,
  constraint pk_image primary key (id))
;

alter table image add constraint fk_image_article_1 foreign key (article_id) references article (id) on delete restrict on update restrict;
create index ix_image_article_1 on image (article_id);


# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table image;

SET FOREIGN_KEY_CHECKS=1;