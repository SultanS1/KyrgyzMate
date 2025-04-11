package alatoo.edu.kg.kyrgyzmate.data.dto.status

enum class FireBasePostResponse {
    SUCCESS,
    FAILED,
    NETWORK_ERROR,
    UNKNOWN_ERROR,
    INVALID_EMAIL,
    EMAIL_ALREADY_IN_USE,
    USER_EXISTS,
    USER_NOT_FOUND,
    EMAIL_NOT_VERIFIED,
    WRONG_CREDENTIALS
}