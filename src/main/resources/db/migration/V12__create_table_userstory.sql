CREATE TABLE user_story
(
    id       BIGINT NOT NULL,
    user_id  UUID NOT NULL,
    story_id BIGINT NOT NULL,
    CONSTRAINT pk_userstory PRIMARY KEY (id)
);
CREATE INDEX idx_userstory_user ON user_story (user_id);
ALTER TABLE user_story
    ADD CONSTRAINT fk_userstory_user FOREIGN KEY (user_id) REFERENCES "user" (id);
ALTER TABLE user_story
    ADD CONSTRAINT fk_userstory_story FOREIGN KEY (story_id) REFERENCES story (id);
