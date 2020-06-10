package com.paolomanlunas.countriesapp.model;

import com.paolomanlunas.countriesapp.di.DaggerApiComponent;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountriesService {

   private static CountriesService instance;

   /* the Api Creation is done in ApiModule.
   *  @Inject calls'inject()' from 'ApiComponent-Class'
   * */
   @Inject
   public CountriesApi api;

   /* SINGLETON Creation:
    *  A PRIVATE Constructor must be used to make this class
    *  a SINGLETON - because we DO NOT want 'this' to be instantiated
    *  by any other classes or codes - ONE(Single) Instance only!
    * */
   private CountriesService() {

      // Perform and Complete the Actual Injection of the Generated-Api
      DaggerApiComponent.create().inject(this);
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
