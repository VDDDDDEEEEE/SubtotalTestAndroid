package com.test.subtotal.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.test.subtotal.model.ItemsItem
import com.test.subtotal.utils.Const

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookView(
    modifier: Modifier = Modifier,
    book: ItemsItem?,
    onClick: (id: String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(color = Color.White, shape = RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        elevation = 2.dp,
        onClick = { onClick(book?.id?:"0")}
    ) {
        Row(
            modifier = modifier
                //.fillMaxWidth()
                .padding(10.dp)
            ,
            verticalAlignment = Alignment.Top
        ) {
            AsyncImage(
                model = book?.volumeInfo?.imageLinks?.thumbnail ?: Const.DEFAULT_BOOK_IMAGE,
                contentDescription = null,
                modifier = Modifier
                    .width(80.dp)
                    //.clip(shape = RoundedCornerShape(4.dp))
                    .aspectRatio(1f / 1.5f),

                contentScale = ContentScale.Crop,

                )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier) {
                Text(text = book?.getTitle()?:"", style = TextStyle(fontWeight = FontWeight.Medium))
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = book?.getAuthors()?:"")
            }
        }
    }
}
