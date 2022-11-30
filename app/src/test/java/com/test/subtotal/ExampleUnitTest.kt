package com.test.subtotal

import com.test.subtotal.api.GoogleApiInstance
import kotlinx.coroutines.*
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val repository = GoogleApiInstance.create()
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }




    @Test
    fun testApi() = runTest {
        val searchingText = "Разработка"
        val response = repository.search(searchingText)
        val data = response.body()


        val resultList = data?.items?:ArrayList()

        assert(resultList.isNotEmpty()){ println("Error isNotEmpty")}


        assert(response.code() == 200){ println("Error code")}

        assert(response.errorBody() == null){ println("Error errorBody")}

        assert(resultList.all{it.volumeInfo.authors!=null}){ println("Error authors")}

        assert(resultList.all{items -> items.volumeInfo.imageLinks == null}){ println("Error imageLinks")}


    }



}