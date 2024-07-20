package com.sample.jetpackcomposesample.data.local.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sample.jetpackcomposesample.data.model.RepositoriesResponse
import com.sample.jetpackcomposesample.util.toJson
import com.sample.jetpackcomposesample.util.toObject

class RepositoryTypeConverter {

    val gson = Gson()

    @TypeConverter
    fun repositoriesToString(repositoriesResponse: RepositoriesResponse): String =
        repositoriesResponse.toJson()

    @TypeConverter
    fun stringToRepositories(data: String): RepositoriesResponse = data.toObject()


    @TypeConverter
    fun itemToString(item: List<RepositoriesResponse.Item>): String = item.toJson()

    @TypeConverter
    fun stringToItem(data: String): List<RepositoriesResponse.Item> {
        val myType = object : TypeToken<List<RepositoriesResponse.Item>>() {}.type
        return gson.fromJson(data, myType)
    }


    @TypeConverter
    fun licenseToString(license: RepositoriesResponse.Item.License): String = license.toJson()

    @TypeConverter
    fun stringToLicense(data: String): RepositoriesResponse.Item.License = data.toObject()

    @TypeConverter
    fun ownerToString(owner: RepositoriesResponse.Item.Owner): String = owner.toJson()

    @TypeConverter
    fun stringToOwner(data: String): RepositoriesResponse.Item.Owner = data.toObject()


}