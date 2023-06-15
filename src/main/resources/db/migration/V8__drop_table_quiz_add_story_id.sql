DROP TABLE story_quiz;
ALTER TABLE question ADD COLUMN story_id INTEGER REFERENCES story(id);
CREATE INDEX question_story_id_idx ON question(story_id);
