package com.github.andreldsr.lingua.story

import com.github.andreldsr.lingua.language.LanguageRepository
import com.github.andreldsr.lingua.question.Question
import com.github.andreldsr.lingua.question.QuestionService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class StoryService(
    private val storyRepository: StoryRepository,
    private val languageRepository: LanguageRepository,
    private val questionService: QuestionService,
    @Value("\${story.default-cover}") private val defaultCover: String
) {

    fun save(request: CreateStoryRequest): Story {
        val language = languageRepository.findById(request.languageId)
            .orElseThrow { RuntimeException("Language ${request.languageId} not found") }
        val story = Story(
            title = request.title,
            content = request.content,
            level = request.level,
            language = language,
            cover = request.cover ?: defaultCover
        )
        storyRepository.save(story)
        val questions = mutableListOf<Question>()
        if (request.quiz.isNotEmpty()) {
            request.quiz.forEach { question ->
                questionService.create(story, question)
            }
        }
        return story.copy(quiz = questions)
    }

    fun updateCover(id: Long, request: UpdateCoverRequest): Story {
        val story = storyRepository.findById(id)
            .orElseThrow { RuntimeException("Story $id not found") }
        return storyRepository.save(story.copy(cover = request.cover))
    }

    fun find(id: Long) = storyRepository.findById(id)

    fun delete(id: Long) = storyRepository.deleteById(id)

    fun deleteAll() = storyRepository.deleteAll()


    fun findAll(): List<StoryListDto> = storyRepository.findBy()
    fun findByLanguageIdAndLevel(languageId: Long, level: String): Story? {
//        val principal = SecurityContextHolder.getContext().authentication.principal as UserDetailImpl
        return storyRepository.findTop1ByLanguageIdAndLevel(languageId, level)
    }
}
