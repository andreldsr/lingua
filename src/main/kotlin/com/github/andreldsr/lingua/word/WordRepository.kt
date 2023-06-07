package com.github.andreldsr.lingua.word

import org.springframework.data.jpa.repository.JpaRepository

interface WordRepository : JpaRepository<Word, Long>
