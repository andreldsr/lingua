package com.github.andreldsr.lingua.word

import com.github.andreldsr.lingua.language.Language
import com.github.andreldsr.lingua.translation.Translation
import com.github.andreldsr.lingua.translation.TranslationRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WordService(
    private val wordRepository: WordRepository,
    private val translationRepository: TranslationRepository
) {
    fun save(request: CreateWordRequest): Word {
        if (wordRepository.existsByContentAndLanguageId(request.word, request.languageId)) {
            throw WordAlreadyExistsException(request.word)
        }
        val word = Word(content = request.word, languageId = request.languageId)
        return wordRepository.save(word)
    }

    @Transactional
    fun addTranslation(wordId: Long, request: TranslationRequest) {
        if (translationRepository.existsByContentAndLanguageId(request.translation, request.languageId)) {
            throw TranslationAlreadyExistsException(request.translation)
        }
        val word = wordRepository.findById(wordId).orElseThrow { RuntimeException(wordId.toString()) }
        word.translations.add(createTranslation(request))
        wordRepository.save(word)
    }

    private fun createTranslation(request: TranslationRequest): Translation {
        val translation = Translation(content = request.translation, language = Language(id = request.languageId))
        return translationRepository.save(translation)
    }

    fun findByLanguage(languageId: Long, content: String?, pageable: Pageable): Page<Word> {
        if (content != null) {
            return wordRepository.findByLanguageIdAndContentContainingIgnoreCase(languageId, content, pageable)
        }
        return wordRepository.findByLanguageId(languageId, pageable)
    }
}
