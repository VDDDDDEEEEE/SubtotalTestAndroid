package com.test.subtotal.model

data class BooksResponse(val totalItems: Int = 0,
                         val kind: String = "",
                         val items: List<ItemsItem>?)