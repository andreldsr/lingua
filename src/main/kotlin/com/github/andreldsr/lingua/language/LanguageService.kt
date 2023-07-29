package com.github.andreldsr.lingua.language

import org.springframework.stereotype.Service

@Service
class LanguageService(private val languageRepository: LanguageRepository) {

    fun findAll(): List<Language> = languageRepository.findAll()

    fun findByCode(code: String): Language? =
        languageRepository.findByCode(code).orElseThrow { LanguageNotFoundException(code) }

    fun save(request: CreateLanguageRequest): Language {
        if (languageRepository.existsByCode(request.code)) {
            throw LanguageAlreadyExistsException(request.code)
        }

        val language = Language(
            code = request.code,
            flag = request.flag
        )

        return languageRepository.save(language)
    }

    fun deleteById(id: Long) = languageRepository.deleteById(id)
}
