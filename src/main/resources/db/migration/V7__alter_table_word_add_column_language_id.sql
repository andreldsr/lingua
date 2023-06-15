ALTER TABLE word
    ADD Column language_id INT;

ALTER TABLE word
    ADD CONSTRAINT FK_QUESTION_ON_LANGUAGE FOREIGN KEY (language_id) REFERENCES language (id);