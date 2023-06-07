package com.github.andreldsr.lingua.answer

import org.springframework.stereotype.Service

@Service
class AnswerService(private val answerRepository: AnswerRepository) {

    fun save(answer: Answer) = answerRepository.save(answer)

    fun findByQuestionId(questionId: Long) = answerRepository.findByQuestionId(questionId)

    fun delete(answer: Answer) = answerRepository.delete(answer)

    fun deleteAll() = answerRepository.deleteAll()
}
