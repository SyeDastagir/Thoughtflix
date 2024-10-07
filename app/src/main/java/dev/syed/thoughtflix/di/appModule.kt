package dev.syed.thoughtflix.di

import dev.syed.thoughtflix.feature.auth.signin.AuthenticationViewModel
import dev.syed.thoughtflix.feature.auth.signup.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { AuthenticationViewModel() }
    viewModel { SignUpViewModel() }
}