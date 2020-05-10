package com.paolomanlunas.countriesapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.paolomanlunas.countriesapp.model.CountryModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends ViewModel {

   /**
    * LiveData is an Object that generates Values.
    * These values are generated asynchoronously.
    * They are OBSERVABLES which values can be observed.
    */
   public MutableLiveData<List<CountryModel>> countries = new MutableLiveData<>();
   public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<>();
   public MutableLiveData<Boolean> loading = new MutableLiveData<>();


   public void refresh() {
      fetchCountries();
   }

   private void fetchCountries() {
      /*
       * Temporary Data(hard-coded) List of Counties
       * */

      List<CountryModel> list = new ArrayList<>();
      list.add(new CountryModel("Albania", "Tirana", ""));
      list.add(new CountryModel("Brazil", "Brasilia", ""));
      list.add(new CountryModel("Czech Republic", "Prague", ""));
      list.add(new CountryModel("Denmark", "Copenhagen", ""));

      countries.setValue(list);
      countryLoadError.setValue(false);
      loading.setValue(false);            // progress-bar-Spinner
   }
}
