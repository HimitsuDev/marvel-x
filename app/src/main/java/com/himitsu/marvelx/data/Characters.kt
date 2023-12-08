package com.himitsu.marvelx.data

data class Characters(
    val attributionHTML: String?,
    val attributionText: String?,
    val code: Int?,
    val copyright: String?,
    val `data`: Data? = null,
    val etag: String?,
    val status: String?
)