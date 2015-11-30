# Update Article

# --- !Ups

ALTER TABLE article ADD availability tinyint(1) default 0;


# --- !Downs

ALTER TABLE article DROP availability;
