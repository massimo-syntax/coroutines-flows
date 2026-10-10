package com.example.domain.model

/**
 * AndroidFeature
 * 
 * Minimal data model with only essential properties:
 * - id: Unique identifier
 * - tag: Feature classification on top (e.g. UI & Motion, Security)
 * - title: Feature name
 * - description: Body text explaining functionality and key APIs
 */
data class AndroidFeature(
    val id: String,
    val tag: String,
    val title: String,
    val description: String
)
