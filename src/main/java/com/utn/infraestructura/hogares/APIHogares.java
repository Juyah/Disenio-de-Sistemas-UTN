package com.utn.infraestructura.hogares;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Header;

public interface APIHogares {

    @GET("hogares")
    Call<HogaresResponse> todos(@Query("offset") int offset, @Header("Authorization") String bearerToken);

}