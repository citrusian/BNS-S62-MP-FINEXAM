package com.example.bns_s62_mp_finexam.Utility

import android.content.Context
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

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

data class DataWilayahItem(
    val WILAYAH: String,
    val DATA: List<SubDataWilayahItem>
)

data class SubDataWilayahItem(
    val PROVINSI: String,
    val LOGO_URL: String,
)

class JSONalamatProcessor(
    private val context: Context,
    daerah: String,
) {

    private val dataItems: List<DataItem>
    private val selectedDaerah: String

    init {
        val jsonString = context.assets.open("provinsiData.json").bufferedReader().use { it.readText() }
        val gson = Gson()
        dataItems = gson.fromJson(jsonString, object : TypeToken<List<DataItem>>() {}.type)
        selectedDaerah = daerah
    }

    // PROVINSI
    fun getDetailsProvince(): List<String> {
        val filteredItems = dataItems.filter { it.DAERAH == selectedDaerah }
        return filteredItems.flatMap { it.DATA.map { subDataItem -> subDataItem.PROVINSI } }
    }

    // ALAMAT
    fun getDetailsAddress(): List<String> {
        val filteredItems = dataItems.filter { it.DAERAH == selectedDaerah }
        return filteredItems.flatMap { it.DATA.map { subDataItem -> subDataItem.ALAMAT } }
    }

    // WEBSITE
    fun getDetailsWebsite(): List<String> {
        val filteredItems = dataItems.filter { it.DAERAH == selectedDaerah }
        return filteredItems.flatMap { it.DATA.map { subDataItem -> subDataItem.WEBSITE } }
    }

    // TEL/FAX
    fun getDetailsPhone(): List<String> {
        val filteredItems = dataItems.filter { it.DAERAH == selectedDaerah }
        return filteredItems.flatMap { it.DATA.map { subDataItem -> subDataItem.TEL_FAX } }
    }

    // E-MAIL
    fun getDetailsMail(): List<String> {
        val filteredItems = dataItems.filter { it.DAERAH == selectedDaerah }
        return filteredItems.flatMap { it.DATA.map { subDataItem -> subDataItem.E_MAIL } }
    }
}
class JSONwilayahProcessor(
    private val context: Context,
    wilayah: String,
) {

    private val dataWilayahItem: List<DataWilayahItem>
    private val selectedWilayah: String

    init {
        val jsonString = context.assets.open("wilayahData.json").bufferedReader().use { it.readText() }
        val gson = Gson()
        dataWilayahItem = gson.fromJson(jsonString, object : TypeToken<List<DataWilayahItem>>() {}.type)
        selectedWilayah = wilayah
    }

    // PROVINSI
    fun getWilayahProvince(): List<String> {
        val filteredItems = dataWilayahItem.filter { it.WILAYAH == selectedWilayah }
        return filteredItems.flatMap { it.DATA.map { SubDataWilayahItem -> SubDataWilayahItem.PROVINSI } }
    }

    // LOGO
    fun getWilayahLogo(): List<String> {
        val filteredItems = dataWilayahItem.filter { it.WILAYAH == selectedWilayah }
        return filteredItems.flatMap { it.DATA.map { SubDataWilayahItem -> SubDataWilayahItem.LOGO_URL } }
    }
}