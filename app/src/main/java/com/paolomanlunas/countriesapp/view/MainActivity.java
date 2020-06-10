package com.paolomanlunas.countriesapp.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.paolomanlunas.countriesapp.databinding.ActivityMainBinding;
import com.paolomanlunas.countriesapp.viewmodel.ListViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   // Use ViewBinding
   private ActivityMainBinding viewBinding;

   private ListViewModel viewModel;
   private CountryListAdapter adapter = new CountryListAdapter(new ArrayList<>());


   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      viewBinding = ActivityMainBinding.inflate(getLayoutInflater());
      View view = viewBinding.getRoot();
      setContentView(view);

      // set 'this' as Lifecycle-Owner
      viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
      viewModel.refresh();

      // RecyclerView - LayoutManager
      viewBinding.countriesList.setLayoutManager(new LinearLayoutManager(this));
      viewBinding.countriesList.setAdapter(adapter);


      // Swipe-to-REFRESH Layout
      viewBinding.swipeRefreshLayout.setOnRefreshListener(() -> {
         viewModel.refresh();                   // shows circular-refresh-animation
         viewBinding.swipeRefreshLayout.setRefreshing(false);    // hides/stops circular-refresh-animation
      });

      // Set Observers to ViewModel-LifeCycles
      observerViewModel();
   }

   private void observerViewModel() {
      // OBSERVE: List of Countries:
      viewModel.countries.observe(this, countryModels -> {
         if (countryModels != null) {
            viewBinding.countriesList.setVisibility(View.VISIBLE);
            adapter.updateCountries(countryModels);
         }
      });

      // OBSERVE: countryLoadError:
      viewModel.countryLoadError.observe(this, isError -> {
         if (isError != null) {
            viewBinding.listError.setVisibility(isError ? View.VISIBLE : View.GONE);
         }
      });

      // OBSERVE: loading:
      viewModel.loading.observe(this, isLoading -> {
         if (isLoading != null) {
            viewBinding.loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);

            if (isLoading) {
               viewBinding.listError.setVisibility(View.GONE);
               viewBinding.countriesList.setVisibility(View.GONE);
            }
         }
      });
   }
}
