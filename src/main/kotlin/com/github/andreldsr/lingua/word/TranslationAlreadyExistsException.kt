package com.github.andreldsr.lingua.word

class TranslationAlreadyExistsException(translation: String) : RuntimeException("Translation $translation already exists")
