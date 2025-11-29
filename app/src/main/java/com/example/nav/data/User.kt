package com.example.nav.data

data class User(
    val id: String?,
    val name: String,
    val email: String
)

// Mock user data - in a real app, this would come from a database or API
object UserRepository {
    private val users = mutableMapOf(
        "user_123" to User("user_123", "John Doe", "john.doe@email.com"),
        "user_456" to User("user_456", "Jane Smith", "jane.smith@email.com")
    )

    fun getUserById(userId: String): User? {
        return users[userId]
    }

    fun updateUser(userId: String, name: String, email: String) {
        users[userId] = User(userId, name, email)
    }
}

