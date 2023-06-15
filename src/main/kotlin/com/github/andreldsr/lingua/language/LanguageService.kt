package com.github.andreldsr.lingua.language

import org.springframework.stereotype.Service

@Service
class LanguageService(private val languageRepository: LanguageRepository) {

    fun findAll(): List<Language> = languageRepository.findAll()

    fun findByTitle(title: String): Language? =
        languageRepository.findByTitle(title).orElseThrow { LanguageNotFoundException(title) }

    fun save(request: CreateLanguageRequest): Language {
        if (languageRepository.existsByTitle(request.title)) {
            throw LanguageAlreadyExistsException(request.title)
        }
        val language = Language(title = request.title)
        return languageRepository.save(language)
    }

    fun deleteById(id: Long) = languageRepository.deleteById(id)
}
