package com.utn.infraestructura.hogares;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.utn.dominio.Hogares;
import com.utn.dominio.excepcion.HogaresNoObtenidosException;

import java.io.IOException;

public class ServicioHogares implements Hogares {

    private final Retrofit retrofit;
    private final String endpoint = "https://api.refugiosdds.com.ar/api/";
    private final String bearerToken = "Bearer DlEO1YkConEH8roim0rnoHOe7dSgRyFBr95t7h0j5mXcAW32gE8OcdNclill";
    private final int paginas = 1;
    private static ServicioHogares instance = null;

    public static ServicioHogares getInstance() {
        if (instance == null) {
            instance = new ServicioHogares();
        }
        return instance;
    }

    public ServicioHogares() {
        this.retrofit = new Retrofit.Builder()
            .baseUrl(endpoint)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    @Override
    public HogaresResponse obtener() {
        try {
            APIHogares hogares = this.retrofit.create(APIHogares.class);
            Call<HogaresResponse> solicitud = hogares.todos(paginas, bearerToken);
            Response<HogaresResponse> respuesta = solicitud.execute();
            return respuesta.body();
        } catch (IOException e) {
            throw new HogaresNoObtenidosException();
        }
    }

}