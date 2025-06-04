package com.example.samplezooapp.core_networking.utils

enum class ZooServiceError: Error {
    SERVER_ERROR,
    REQUEST_TIMEOUT,
    TOO_MANY_REQUEST,
    NO_INTERNET,
    MALFORMED_JSON_ERROR,
    UNKNOWN_ERROR
}