package course.demoappfirebase.feature.auth.presentation.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.VisualTransformation
import course.demoappfirebase.R

@Composable
fun MyTextFieldComponent(
    labelValue: String, painterResource: Painter?,
    onTextChanged: (String) -> Unit,
    errorStatus: Boolean = false
) {

    var textValue by remember { mutableStateOf("") }

    TextField(
        value = textValue,
        onValueChange = {
            textValue = it
            onTextChanged(it)
        },
        label = {
            Text(text = labelValue)
        },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = false,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        leadingIcon = {
            if (painterResource != null) {
                Icon(painter = painterResource, contentDescription = "")
            }
        },
        isError = !errorStatus
    )
}

@Composable
fun PasswordTextFieldComponent(
    labelValue: String, painterResource: Painter?,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean = false
) {

    var password by remember { mutableStateOf("") }

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    TextField(
        value = password,
        onValueChange = {
            password = it
            onTextSelected(it)
        },
        label = {
            Text(text = labelValue)
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = false,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        leadingIcon = {
            if (painterResource != null) {
                Icon(painter = painterResource, contentDescription = "")
            }
        },
        trailingIcon = {

            val iconImage = if (passwordVisible.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            val description = if (passwordVisible.value) {
                stringResource(id = R.string.hide_password)
            } else {
                stringResource(id = R.string.show_password)
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }

        },
        isError = !errorStatus
    )
}
