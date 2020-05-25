package com.paolomanlunas.countriesapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.paolomanlunas.countriesapp.R;
import com.paolomanlunas.countriesapp.model.CountryModel;
import com.paolomanlunas.countriesapp.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
   @BindView(R.id.swipeRefreshLayout)  // @BindView (From: 'Butterknife' lib)
   SwipeRefreshLayout refreshLayout;

   @BindView(R.id.countriesList)
   RecyclerView countriesList;

   @BindView(R.id.list_error)
   TextView listError;

   @BindView(R.id.loading_view)
   ProgressBar loadingView;


   private ListViewModel viewModel;
   private CountryListAdapter adapter = new CountryListAdapter(new ArrayList<>());


   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      ButterKnife.bind(this);

      // set 'this' as Lifecycle-Owner
      viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
      viewModel.refresh();

      // RecyclerView - LayoutManager
      countriesList.setLayoutManager(new LinearLayoutManager(this));
      countriesList.setAdapter(adapter);


      // Swipe-to-REFRESH Layout
      refreshLayout.setOnRefreshListener(() -> {
         viewModel.refresh();                   // shows circular-refresh-animation
         refreshLayout.setRefreshing(false);    // hides/stops circular-refresh-animation
      });

      // Set Observers to ViewModel-LifeCycles
      observerViewModel();
   }

   private void observerViewModel() {
      // Observe: List of Countries:
      viewModel.countries.observe(this, countryModels -> {
         if (countryModels != null) {
            countriesList.setVisibility(View.VISIBLE);
            adapter.updateCountries(countryModels);
         }
      });

      // Observe: countryLoadError:
      viewModel.countryLoadError.observe(this, isError -> {
         if (isError != null) {
            listError.setVisibility(isError ? View.VISIBLE : View.GONE);
         }
      });

      // Observe: loading:
      viewModel.loading.observe(this, isLoading -> {
         if (isLoading != null) {
            loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);

            if (isLoading) {
               listError.setVisibility(View.GONE);
               countriesList.setVisibility(View.GONE);
            }
         }
      });
   }
}
