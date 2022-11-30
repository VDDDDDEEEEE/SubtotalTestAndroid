package com.test.subtotal.ui.screens

import android.app.LocaleConfig
import android.widget.Toast
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.test.subtotal.ui.components.BookView
import com.test.subtotal.ui.components.edittext.SearchBookView
import com.test.subtotal.ui.components.loader.FullScreenLoaderComponent
import com.test.subtotal.ui.navigation.Graph
import com.test.subtotal.viewmodels.MainViewModel
import com.test.subtotal.R

@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val searchState =
        rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
    val errorMessage = mainViewModel.errorMessage.observeAsState().value
    val list = mainViewModel.foundBooks.observeAsState().value
    val isLoading = mainViewModel.loading.observeAsState().value



    Column(modifier = modifier.fillMaxSize()) {

        LazyColumn(
            state = rememberLazyListState(),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 4.dp)
        ) {
            item {
                SearchBookView(
                    state = searchState,
                    onSearchClick = { searchedText ->
                        mainViewModel.searchBooks(searchedText)
                    }
                )
                if ((errorMessage ?: "").isNotEmpty()) {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = errorMessage ?: "",
                        style = TextStyle(color = Color.Red)
                    )
                }
            }
            if (list != null) {
                if(isLoading == true){
                    item {
                        Box(modifier = Modifier.fillMaxSize()){

                            FullScreenLoaderComponent()
                        }
                    }
                }
                if(list.isEmpty() && isLoading == false){
                    item { Text(modifier = Modifier.fillMaxSize(),text = stringResource(id = R.string.nothing_found), textAlign = TextAlign.Center) }
                } else {
                    itemsIndexed(list ) { index, book ->
                        BookView(
                            book = book,
                            onClick = { id ->
                                navController.navigate("${Graph.DETAILS}/$id")
                            }
                        )
                    }
                }
            }
        }
    }
}