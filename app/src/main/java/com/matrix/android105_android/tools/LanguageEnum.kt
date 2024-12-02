package com.matrix.android105_android.tools

enum class LanguageEnum(val fullName: String) {
    AZ("Azərbaycan"),
    EN("English");

    companion object {
        fun find(name: String?) = entries.find { it.name.lowercase() == name?.lowercase() } ?: AZ

        fun LanguageEnum.lowercase() = name.lowercase()
    }
}