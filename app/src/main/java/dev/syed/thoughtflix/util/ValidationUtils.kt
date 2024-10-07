package dev.syed.thoughtflix.util

object ValidationUtils {
    private const val EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    fun isValidEmail(email: String): Boolean {
        return email.matches(EMAIL_PATTERN.toRegex())
    }

    fun isValidPassword(password: String): Boolean {
        val hasSpecialChar = password.any { "!@#\$%^&*()-_=+[]{}|;:'\",.<>?/`~".contains(it) }
        return password.length >= 8 && password.any { it.isDigit() } && password.any { it.isUpperCase() } && password.any { it.isLowerCase() } && hasSpecialChar
    }
}