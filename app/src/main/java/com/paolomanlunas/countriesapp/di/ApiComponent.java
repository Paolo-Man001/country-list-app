package com.paolomanlunas.countriesapp.di;


import com.paolomanlunas.countriesapp.model.CountriesService;

import dagger.Component;
import dagger.Provides;

/* Dagger's @Component will look into 'ApiModule.java' to find @Provides.
*  @Provides will call the function it's attached to, which is 'provideCountriesApi()'.
*  it will return 'CountriesApi built by Retrofit' ....
* */
@Component(modules = {ApiModule.class})
public interface ApiComponent {

   /* object returned from @Provides is injected into 'CountriesService service' - provided below -
   *  where @Inject will call this 'inject()'.
   *     This component knows where to inject because we passed the instance of the class
   *     where we want to inject into (CountriesService)
   * */
   void inject(CountriesService service);
}
