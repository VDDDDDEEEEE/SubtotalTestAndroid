package com.test.subtotal.model

import android.content.res.Resources
import com.test.subtotal.utils.L

data class ItemsItem(
    val saleInfo: SaleInfo,
    val searchInfo: SearchInfo,
    val kind: String = "",
    val volumeInfo: VolumeInfo,
    val etag: String = "",
    val id: String = "",
    val accessInfo: AccessInfo,
    val selfLink: String = ""
) {

    private val unknown = Resources.getSystem().getString(android.R.string.unknownName)

    fun getTitle(): String {
        return volumeInfo.title ?: unknown
    }

    fun getAuthors(): String {
        L.d("getAuthors volumeInfo = ${volumeInfo.authors}")
        if (volumeInfo.authors == null) {
            return unknown
        } else {
            var str = ""
            for (author in volumeInfo.authors) {
                str += "$author, "
            }
            if(str.isNotEmpty()){
                str = str.dropLast(2)
            }
            return str
        }
    }

    fun getDesc():String{
        return volumeInfo.description?:""
    }

    fun getDate():String{
        return volumeInfo.publishedDate ?: unknown
    }
}