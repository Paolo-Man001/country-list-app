package com.paolomanlunas.countriesapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.paolomanlunas.countriesapp.di.DaggerApiComponent;
import com.paolomanlunas.countriesapp.model.CountriesService;
import com.paolomanlunas.countriesapp.model.CountryModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends ViewModel {

   /**
    * LiveData is an Object that generates Values.
    * These values are generated asynchoronously.
    * They are OBSERVABLES which values can be observed.
    */
   public MutableLiveData<List<CountryModel>> countries = new MutableLiveData<>();
   public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<>();
   public MutableLiveData<Boolean> loading = new MutableLiveData<>();

   /* 'CompositeDisposable' allows us to intercept the countriesService to dispose its background-process(fetch JSON).
    *  Dispose process when finished fetching back-end data prevents memory-loss.
    */
   private CompositeDisposable disposable = new CompositeDisposable();

   @Inject
   public CountriesService countriesService;

   //-- CONSTRUCTOR: for Dagger Injecting instance of 'CountriesService'
   public ListViewModel() {
      super();
      // Inject 'CountriesService'
      DaggerApiComponent.create().inject(this);
   }


   //-- METHODS:
   public void refresh() {
      fetchCountries();
   }

   // GET Data from backend
   private void fetchCountries() {
      loading.setValue(true);       // ProgressBar START
      disposable.add(
              countriesService.getCountries()
                      .subscribeOn(Schedulers.newThread())  // puts this process on a New Thread(background-process while waiting for API response)
                      .observeOn(AndroidSchedulers.mainThread())  // When the RESPONSE is available, put it on the Main-Thread-process, to UPDATE our view.
                      .subscribeWith(new DisposableSingleObserver<List<CountryModel>>() { // Subscribe to RxJava Observable-Component 'Single'

                         @Override
                         public void onSuccess(List<CountryModel> countryModels) {
                            countries.setValue(countryModels); // GET List-of-Countries
                            countryLoadError.setValue(false);  // NoError
                            loading.setValue(false);           // ProgressBar STOP
                         }

                         @Override
                         public void onError(Throwable e) {
                            countryLoadError.setValue(true);   // NoError
                            loading.setValue(false);           // ProgressBar STOP
                            e.printStackTrace();
                         }
                      })
      ); // END: disposable.add()
   }

   @Override
   protected void onCleared() {
      super.onCleared();
      disposable.clear();
   }

   /* ****
    * Temporary Local-Data(hard-coded) List of Counties
    * *** */
//   private void fetchCountries() {
//      List<CountryModel> list = new ArrayList<>();
//      list.add(new CountryModel("Albania", "Tirana", ""));
//      list.add(new CountryModel("Brazil", "Brasilia", ""));
//      list.add(new CountryModel("Czech Republic", "Prague", ""));
//      list.add(new CountryModel("Denmark", "Copenhagen", ""));
//      list.add(new CountryModel("Albania", "Tirana", ""));
//      list.add(new CountryModel("Brazil", "Brasilia", ""));
//      list.add(new CountryModel("Czech Republic", "Prague", ""));
//      list.add(new CountryModel("Denmark", "Copenhagen", ""));
//      list.add(new CountryModel("Albania", "Tirana", ""));
//      list.add(new CountryModel("Brazil", "Brasilia", ""));
//      list.add(new CountryModel("Czech Republic", "Prague", ""));
//      list.add(new CountryModel("Denmark", "Copenhagen", ""));
//
//      countries.setValue(list);
//      countryLoadError.setValue(false);
//      loading.setValue(false);            // progress-bar-Spinner
//   }

}
