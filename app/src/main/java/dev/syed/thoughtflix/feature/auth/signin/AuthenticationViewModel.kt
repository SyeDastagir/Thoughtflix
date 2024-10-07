package dev.syed.thoughtflix.feature.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import dev.syed.thoughtflix.util.ValidationUtils

class AuthenticationViewModel : ViewModel() {
    private val _emailAddress = MutableLiveData("")
    val emailAddress: LiveData<String> = _emailAddress

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _emailError = MutableLiveData<String?>(null)
    val emailError: LiveData<String?> = _emailError

    private val _passwordError = MutableLiveData<String?>(null)
    val passwordError: LiveData<String?> = _passwordError

    fun onEmailChange(email: String) {
        _emailAddress.value = email
        _emailError.value = null
    }

    fun onPasswordChange(password: String) {
        _password.value = password
        _passwordError.value = null
    }

    fun validateCredentials(): Boolean {
        var isValid = true
        if (!ValidationUtils.isValidEmail(_emailAddress.value ?: "")) {
            _emailError.value = "Invalid email address"
            isValid = false
        } else {
            _emailError.value = null
        }
        if (!ValidationUtils.isValidPassword(_password.value ?: "")) {
            _passwordError.value = "Invalid password"
            isValid = false
        } else {
            _passwordError.value = null
        }
        return isValid
    }
}