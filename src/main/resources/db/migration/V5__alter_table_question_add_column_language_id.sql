ALTER TABLE question
    ADD Column language_id INT;

ALTER TABLE question
    ADD CONSTRAINT FK_QUESTION_ON_LANGUAGE FOREIGN KEY (language_id) REFERENCES language (id);