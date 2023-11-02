package com.example.bns_s62_mp_finexam.Utility

import android.content.Context
import com.google.gson.annotations.SerializedName

//data class dataHelper(
//    val DAERAH: String,
//    val NO: String,
//    val PROVINSI: String,
//    val ALAMAT: String,
//    val WEBSITE: String?,
//    val TEL_FAX: String?,
//    val E_MAIL: String?,
//)

data class DataItem(
    val NOMOR: Int,
    val DAERAH: String,
    val DATA: List<SubDataItem>
)

data class SubDataItem(
    val NO: String,
    val PROVINSI: String,
    val ALAMAT: String,
    val WEBSITE: String,

    // cant use slash, use gson alternate name
    // https://www.javadoc.io/doc/com.google.code.gson/gson/2.6.2/com/google/gson/annotations/SerializedName.html
    @SerializedName("TEL/FAX")
    val TEL_FAX: String,

    @SerializedName("E-MAIL")
    val E_MAIL: String
)

