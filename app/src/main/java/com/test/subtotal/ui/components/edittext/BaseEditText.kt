package com.test.subtotal.ui.components.edittext

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.test.subtotal.ui.theme.Typography


@Composable
fun SearchEditText(
    state: MutableState<TextFieldValue>,
    errorState: MutableState<Boolean>,
) {

    Box(modifier = Modifier
        .height(45.dp)
        .background(color = White, shape = RoundedCornerShape(10.dp))
        .border(
            width = 1.dp,
            color = if (errorState.value) Color.Red else Color.Transparent,
            shape = RoundedCornerShape(10.dp)
        ),

    ) {
        BasicTextField(
            value = state.value,
            onValueChange = { state.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .align(Alignment.Center),
            textStyle = Typography.body1,
            singleLine = true,
        )
    }
}



