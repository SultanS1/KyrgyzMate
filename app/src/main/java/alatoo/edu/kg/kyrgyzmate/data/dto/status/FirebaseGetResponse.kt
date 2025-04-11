package alatoo.edu.kg.kyrgyzmate.data.dto.status

sealed interface FirebaseGetResponse <out T> {
    data class Success<out T>(val data: T) : FirebaseGetResponse<T>
    data class Error(val errorMessage: String) : FirebaseGetResponse<Nothing>
}