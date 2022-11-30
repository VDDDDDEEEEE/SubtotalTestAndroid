package com.test.subtotal.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.test.subtotal.api.GoogleApiInstance
import com.test.subtotal.model.ItemsItem
import com.test.subtotal.utils.L
import kotlinx.coroutines.*

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val foundBooks = MutableLiveData<List<ItemsItem>>()

    private val api = GoogleApiInstance.create()
    private var searchJob : Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun searchBooks(searchQuery: String){
        searchJob?.cancel()
        foundBooks.value = ArrayList()
        errorMessage.postValue("")
        loading.postValue (true)
        searchJob = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = api.search(searchQuery)
            L.d("registrationUser response = $response")
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    L.d("regUser success result = ${response.body()}")
                    loading.postValue (false)
                    if(response.body()?.items?.isNotEmpty() == true){
                        foundBooks.postValue(response.body()?.items?:ArrayList())
                    }
                } else {
                    onError("regUser message : ${response.message()} ")
                }
            }
        }
    }


    fun getBookFromId(id: String): ItemsItem?{
        return  foundBooks.value?.find{it.id.contains(id)}
    }

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }
    private fun onError(message: String) {
        L.d("onError message = $message")
        errorMessage.postValue(message)
        loading.postValue(false)
    }
}