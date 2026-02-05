package com.besha.egyptguide.appcore.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.besha.egyptguide.R

@Composable
fun AuthOutlinedTextField(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isError: Boolean = false,
    supportingText: String ="",
    trailingIcon: @Composable (() -> Unit)? = null,
    passwordVisible: Boolean=true
) {
    Column(modifier = modifier) {

        Text(
            color = colorResource(R.color.gray),
            text = title,
            fontSize = 12.sp
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp),
            placeholder = {
                Text(
                    placeholder,
                    color = colorResource(R.color.gray)
                )
            },
            singleLine = singleLine,
            keyboardOptions = keyboardOptions,
            trailingIcon = trailingIcon,
            isError = isError,
            supportingText = {
                if (isError) {
                    Text(
                        text = supportingText,
                        fontSize = 11.sp,
                        color = Color.Red
                    )
                }
            },
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colorResource(R.color.white),
                unfocusedContainerColor = colorResource(R.color.white),
                focusedBorderColor = colorResource(R.color.stroke_gray),
                unfocusedBorderColor = colorResource(R.color.stroke_gray),
                errorContainerColor = colorResource(R.color.white)
            ),
            visualTransformation =  if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )
    }
}

