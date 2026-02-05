package com.besha.egyptguide.appcore.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.besha.egyptguide.R

@Composable
fun AuthButton(
    text: String,
    modifier: Modifier = Modifier,
    hasError: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.blue)
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = if (!hasError) 4.dp else 0.dp,
            pressedElevation = if (!hasError) 8.dp else 0.dp
        ),
        enabled = !hasError,
        onClick = {
            if (!hasError) {
                onClick()
            }
        }
    ) {
        Text(
            text = text,
            fontSize = 14.sp
        )
    }
}