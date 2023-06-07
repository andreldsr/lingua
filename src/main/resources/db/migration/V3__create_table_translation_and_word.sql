CREATE TABLE translation
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    content     VARCHAR(255),
    language_id BIGINT,
    CONSTRAINT pk_translation PRIMARY KEY (id)
);

ALTER TABLE translation
    ADD CONSTRAINT FK_TRANSLATION_ON_LANGUAGE FOREIGN KEY (language_id) REFERENCES language (id);

CREATE TABLE word
(
    id      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    content VARCHAR(255),
    CONSTRAINT pk_word PRIMARY KEY (id)
);

CREATE TABLE word_translations
(
    word_id         BIGINT NOT NULL,
    translations_id BIGINT NOT NULL
);

ALTER TABLE word_translations
    ADD CONSTRAINT fk_wortra_on_translation FOREIGN KEY (translations_id) REFERENCES translation (id);

ALTER TABLE word_translations
    ADD CONSTRAINT fk_wortra_on_word FOREIGN KEY (word_id) REFERENCES word (id);