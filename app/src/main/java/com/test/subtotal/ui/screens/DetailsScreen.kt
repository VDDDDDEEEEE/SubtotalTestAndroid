package com.test.subtotal.ui.screens

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.test.subtotal.utils.Const
import com.test.subtotal.viewmodels.MainViewModel
import com.test.subtotal.R

@Composable
fun DetailsScreen(
    mainViewModel: MainViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    id: String = "0"
) {
    val book = mainViewModel.getBookFromId(id)
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = book?.volumeInfo?.imageLinks?.thumbnail ?: Const.DEFAULT_BOOK_IMAGE,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp))
                .aspectRatio(1f / 1.5f),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = book?.getTitle() ?: "",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Medium)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = book?.getAuthors() ?: "",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = book?.getDesc() ?: "",
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "${stringResource(id = R.string.date)} ${book?.getDate()}",
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal),

        )
    }

}