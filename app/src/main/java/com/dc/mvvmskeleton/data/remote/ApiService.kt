package com.dc.mvvmskeleton.data.remote


import com.dc.mvvmskeleton.data.model.hb.WSGenericResponse

import com.google.gson.JsonElement
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by HB on 21/6/19.
 */
public interface ApiService {

    @FormUrlEncoded
    @POST("login")
            /*@RequestExclusionStrategy(RequestExclusionStrategy.ENCRYPTION + "," + RequestExclusionStrategy.CHECKSUM)*/
            /*@DataAsObjectResponse*/
    fun login(@Field("email") email: String, @Field("password") password: String): Call<JsonElement>

    @GET("token_generate")
    fun tokenGenerate(): Call<JsonElement>


    @FormUrlEncoded
    @POST("login")
    fun loginJoinPods(@FieldMap map: HashMap<String, String>): Call<WSGenericResponse<JsonElement>>

    @Multipart
    @DELETE("edit_profile")
    fun editProfileJoinPods(@Part("users_id") userID: RequestBody,
                            @Part file: MultipartBody.Part? = null): Call<JsonElement>


}