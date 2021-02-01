package com.et.testimageskotlin.api

import com.et.testimageskotlin.models.ImgModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {

    @GET("/v2/list")
    fun getImages(@Query("page") page: Int,
                  @Query("limit") limit: Int) : Single<ArrayList<ImgModel>>
}