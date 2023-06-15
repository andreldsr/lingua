package com.github.andreldsr.lingua.word

class WordAlreadyExistsException(word: String) : RuntimeException("Word $word already exists")
