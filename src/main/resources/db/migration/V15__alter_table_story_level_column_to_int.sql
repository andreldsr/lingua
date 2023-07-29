-- Update column story_level to int
ALTER TABLE story ALTER COLUMN level TYPE int USING (level::int);