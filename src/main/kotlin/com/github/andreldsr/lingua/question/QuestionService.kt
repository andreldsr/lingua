package com.github.andreldsr.lingua.question

import com.github.andreldsr.lingua.answer.Answer
import com.github.andreldsr.lingua.answer.AnswerRepository
import com.github.andreldsr.lingua.story.Story
import com.github.andreldsr.lingua.story.StoryRepository
import org.springframework.stereotype.Service

@Service
class QuestionService(
    private val questionRepository: QuestionRepository,
    private val answerRepository: AnswerRepository,
    private val storyRepository: StoryRepository
) {
    fun create(storyId: Long, request: CreateQuestionRequest): Question {
        val story = storyRepository.findById(storyId).orElseThrow { throw Exception("Story not found") }
        return create(story, request)
    }

    fun create(story: Story, request: CreateQuestionRequest): Question {
        val question = Question(content = request.content, storyId = story.id)
        val answers = mutableListOf<Answer>()
        questionRepository.save(question)
        request.answers.forEach {
            val answer = Answer(content = it.content, isCorrect = it.isCorrect, questionId = question.id!!)
            answers.add(answerRepository.save(answer))
        }
        return question.copy(answers = answers)
    }

    fun findAll(): List<Question> {
        return questionRepository.findAll()
    }

    fun delete(id: Long) {
        return questionRepository.deleteById(id)
    }
}
