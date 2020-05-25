package com.paolomanlunas.countriesapp.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountriesService {
   private static final String BASE_URL = "https://raw.githubusercontent.com";

   private static CountriesService instance;

   private CountriesApi api = new Retrofit.Builder()
           .baseUrl(BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())          // Converts JSON-res into Obj. of Country-MODEL we defined, as a LIST-of-CountryModel.
           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())   // Converts the List<CountryModel> into an RxJava Component(Single) Observable
           .build()
           .create(CountriesApi.class);

   /* SINGLETON Creation:
    *  A PRIVATE Constructor must be used to make this class
    *  a SINGLETON - because we DO NOT want 'this' to be instantiated
    *  by any other classes or codes - ONE(Single) Instance only!
    * */
   private CountriesService() {
   }
   // Make this class as a SINGLETON:
   public static CountriesService getInstance() {
      if (instance == null) {
         instance = new CountriesService();
      }
      return instance;
   }


   //-- getCountries LIST created by Builder()
   public Single<List<CountryModel>> getCountries() {
      return api.getCountries();
   }
}
