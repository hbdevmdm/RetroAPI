package com.dc.mvvmskeleton.data.remote

import com.google.gson.JsonElement
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PlainApiService {

    @FormUrlEncoded
    @POST("api/users")
    fun createUser(@Field("name") name: String, @Field("job") job: String): retrofit2.Call<JsonElement>
}
