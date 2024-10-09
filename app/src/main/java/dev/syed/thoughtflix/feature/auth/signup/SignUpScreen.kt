package dev.syed.thoughtflix.feature.auth.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import dev.syed.thoughtflix.R
import dev.syed.thoughtflix.components.TButton
import dev.syed.thoughtflix.components.TTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpScreen(
    innerPadding: PaddingValues,
    viewModel: SignUpViewModel = koinViewModel(),
    onSignUp: () -> Unit = {}
) {
    val emailAddress by viewModel.emailAddress.observeAsState("")
    val password by viewModel.password.observeAsState("")
    val confirmPassword by viewModel.confirmPassword.observeAsState("")
    val emailError by viewModel.emailError.observeAsState(null)
    val passwordError by viewModel.passwordError.observeAsState(null)
    val confirmPasswordError by viewModel.confirmPasswordError.observeAsState(null)

    Column(
        modifier = Modifier
            .background(Color.Black)
            .padding(innerPadding)
            .padding(horizontal = 16.dp, vertical = 32.dp)
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.n_logo),
            contentDescription = "ThoughtFlix Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
        Spacer(modifier = Modifier.height(64.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Sign Up",
                color = Color.White,
                style = MaterialTheme.typography.displaySmall
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        TTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailAddress,
            label = "Email address",
            onValueChange = {
                viewModel.onEmailChange(it)
            },
            isError = emailError != null,
            supportingText = emailError ?: ""
        )
        TTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            label = "Password",
            onValueChange = {
                viewModel.onPasswordChange(it)
            },
            visualTransformation = PasswordVisualTransformation(),
            isError = passwordError != null,
            supportingText = passwordError ?: ""
        )
        TTextField(
            value = confirmPassword,
            onValueChange = { viewModel.onConfirmPasswordChange(it) },
            label = "Confirm password",
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            isError = confirmPasswordError != null,
            supportingText = confirmPasswordError ?: ""
        )
        Spacer(modifier = Modifier.height(16.dp))
        TButton(
            modifier = Modifier
                .fillMaxWidth(),
            label = "Sign Up",
            containerColor = Color(0xFFE50914),
            contentColor = Color.White,
            borderColor = Color.Transparent,
            onClick = { if (viewModel.validateCredentials()) onSignUp() }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Already have an account?",
                color = Color.White,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.width(4.dp))
            TextButton(
                onClick = { onSignUp() }
            ) {
                Text(
                    text = "Sign In",
                    color = Color.White,
                    textDecoration = TextDecoration.Underline,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}