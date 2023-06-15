package com.github.andreldsr.lingua.story

interface StoryListDto {
    fun getId(): Long
    fun getTitle(): String
    fun getLevel(): String
    fun getCover(): String
    fun getLanguageTitle(): String
}
