CREATE TABLE question
(
    id      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    content VARCHAR(255),
    CONSTRAINT pk_question PRIMARY KEY (id)
);
CREATE TABLE answer
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    content     VARCHAR(255),
    is_correct  BOOLEAN,
    question_id BIGINT,
    CONSTRAINT pk_answer PRIMARY KEY (id)
);

ALTER TABLE answer
    ADD CONSTRAINT FK_ANSWER_ON_QUESTION FOREIGN KEY (question_id) REFERENCES question (id);