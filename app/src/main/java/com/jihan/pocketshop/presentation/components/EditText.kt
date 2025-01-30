package com.jihan.pocketshop.presentation.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.eyram.iconsax.IconSax

@Composable
fun EditText(
    value: String,
    label: String,
    leadingIcon: Int? = null,
    padding: Dp = 4.dp,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(10)),
    inputType: InputType = InputType.TEXT,
    imeAction: ImeAction = ImeAction.Next,
    onValueChange: (String) -> Unit,
) {

    val mValue by rememberUpdatedState(value)
    var mVisible by remember { mutableStateOf(false) }

    TextField(
        modifier = modifier.padding(padding),
        value = mValue,
        onValueChange = {
            onValueChange(it)
        },
        label = { Text(text = label, color = MaterialTheme.colorScheme.primary) },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        leadingIcon = {
            leadingIcon?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        trailingIcon = {

            if (inputType==InputType.PASSWORD) {

                val icon = if (mVisible) {
                    painterResource(IconSax.Broken.EyeSlash)
                } else {
                    painterResource(IconSax.Broken.Eye)
                }

                IconButton(onClick = {
                    mVisible = mVisible.not()
                }) {
                    Icon(painter = icon, contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary)
                }


            }
            else  if (value.isNotEmpty()) {
                IconButton(onClick = {
                    onValueChange("")
                }) {

                    Icon(painter = painterResource(IconSax.Broken.CloseCircle), contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary)
                }
            }


        },

        visualTransformation = if (inputType==InputType.PASSWORD && !mVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        keyboardOptions = KeyboardOptions(
            autoCorrectEnabled = inputType==InputType.TEXT,
            keyboardType = when(inputType){
                InputType.TEXT -> KeyboardType.Text
                InputType.PASSWORD -> KeyboardType.Password
                InputType.EMAIL -> KeyboardType.Email
                InputType.NUMBER -> KeyboardType.Number
                InputType.PHONE -> KeyboardType.Phone
                InputType.DECIMAL -> KeyboardType.Decimal
            },
//            imeAction = imeAction,
            showKeyboardOnFocus = true
        ),

        )
}


enum class InputType {
    TEXT, PASSWORD, EMAIL, NUMBER, PHONE, DECIMAL
}