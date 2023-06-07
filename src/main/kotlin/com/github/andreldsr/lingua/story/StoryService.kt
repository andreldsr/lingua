package com.github.andreldsr.lingua.story

import com.github.andreldsr.lingua.answer.AnswerRepository
import com.github.andreldsr.lingua.language.LanguageRepository
import com.github.andreldsr.lingua.question.QuestionRepository
import com.github.andreldsr.lingua.word.WordRepository
import org.springframework.stereotype.Service

@Service
class StoryService(
    private val storyRepository: StoryRepository,
    private val questionRepository: QuestionRepository,
    private val answerRepository: AnswerRepository,
    private val languageRepository: LanguageRepository,
    private val wordRepository: WordRepository
) {

    fun save(story: Story): Story {
        story.language?.let {
            languageRepository.findById(it.id).orElseGet {
                languageRepository.save(it)
            }
        }
        story.quiz.forEach { question ->
            val savedQuestion = questionRepository.save(question)
            question.answers.forEach { answer ->
                answerRepository.save(answer.copy(question = savedQuestion))
            }
        }
        story.vocabulary.forEach { word ->
            wordRepository.save(word)
        }
        return storyRepository.save(story)
    }

    fun find(id: Long) = storyRepository.findById(id)

    fun delete(id: Long) = storyRepository.deleteById(id)

    fun findAll(): List<Story> = storyRepository.findAll()
}
