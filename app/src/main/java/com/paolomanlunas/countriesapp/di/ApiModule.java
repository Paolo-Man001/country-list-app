package com.paolomanlunas.countriesapp.di;

import com.paolomanlunas.countriesapp.model.CountriesApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class ApiModule {
   // BASE URL
   private static final String BASE_URL = "https://raw.githubusercontent.com";

   @Provides
   public CountriesApi provideCountriesApi() {
      return new Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())          // Converts JSON-res into Obj. of Country-MODEL we defined, as a LIST-of-CountryModel.
              .addCallAdapterFactory(RxJava2CallAdapterFactory.create())   // Converts the List<CountryModel> into an RxJava Component(Single) Observable
              .build()
              .create(CountriesApi.class);
   }
}
