package com.github.andreldsr.lingua.translation

import org.springframework.data.jpa.repository.JpaRepository

interface TranslationRepository : JpaRepository<Translation, Long>
