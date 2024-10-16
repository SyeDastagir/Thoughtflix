package dev.syed.thoughtflix.feature.auth.signin

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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import dev.syed.thoughtflix.components.TButton
import dev.syed.thoughtflix.components.TTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthenticationScreen(
    innerPadding: PaddingValues,
    viewModel: AuthenticationViewModel = koinViewModel(),
    onSignIn: () -> Unit = {},
    onSignUp: () -> Unit = {}
) {
    val emailAddress by viewModel.emailAddress.observeAsState("")
    val password by viewModel.password.observeAsState("")
    val emailError by viewModel.emailError.observeAsState(null)
    val passwordError by viewModel.passwordError.observeAsState(null)

    Column(
        modifier = Modifier
            .background(Color.Black)
            .padding(innerPadding)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .imePadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Sign In",
                color = Color.White,
                style = MaterialTheme.typography.displaySmall
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
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
        Spacer(modifier = Modifier.height(8.dp))
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
        Spacer(modifier = Modifier.height(16.dp))

        TButton(
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color(0xFFE50914),
            contentColor = Color.White,
            borderColor = Color.Transparent,
            label = "Sign In",
            onClick = {
                if (viewModel.validateCredentials()) onSignIn()
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = onSignUp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    style = MaterialTheme.typography.titleSmall,
                    text =
                    buildAnnotatedString {
                        append("New to Thoughtflix? ")
                        withStyle(
                            style = SpanStyle(
                                textDecoration = TextDecoration.Underline,
                                fontStyle = MaterialTheme.typography.labelLarge.fontStyle
                            )
                        ) {
                            append("Sign up now")
                        }
                    },
                    color = Color.White
                )
            }
        }
    }
}
