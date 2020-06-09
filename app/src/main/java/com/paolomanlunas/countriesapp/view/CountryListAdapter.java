package com.paolomanlunas.countriesapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paolomanlunas.countriesapp.R;
import com.paolomanlunas.countriesapp.databinding.ItemCountryBinding;
import com.paolomanlunas.countriesapp.model.CountryModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {

   private List<CountryModel> countries;

   //--- Class Constructor:
   public CountryListAdapter(List<CountryModel> countries) {
      this.countries = countries;
   }

   //--- Class Methods:
   public void updateCountries(List<CountryModel> newCountries) {
      countries.clear();
      countries.addAll(newCountries);

      notifyDataSetChanged();
   }

   //--- Override Methods:
   @NonNull
   @Override
   public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      /*View view = LayoutInflater
              .from(parent.getContext())
              .inflate(R.layout.item_country, parent, false);*/

      // Use ViewBinding
      ItemCountryBinding viewBinding = ItemCountryBinding.inflate(
              LayoutInflater.from(parent.getContext()), parent, false);

      return new CountryViewHolder(viewBinding);
   }

   @Override
   public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
      holder.bind(countries.get(position));
   }

   @Override
   public int getItemCount() {
      return countries.size();
   }


   //--- ViewHolder Class:
   static class CountryViewHolder extends RecyclerView.ViewHolder {
      /*@BindView(R.id.imageView)  // @BindView (From: 'Butterknife' lib)
      ImageView countryImage;*/

      // Use ViewBinding
      ItemCountryBinding viewBinding;

      //-- ViewHolder Constructor
      public CountryViewHolder(@NonNull ItemCountryBinding binding) {
         super(binding.getRoot());     // Get the Root Layout(view)
         this.viewBinding = binding;   // Assign the same binding
      }

      void bind(CountryModel country) {
         viewBinding.name.setText(country.getCountryName());
         viewBinding.capital.setText(country.getCapital());

         // Use Glide & CircularProgressDrawable from Util.java:
         Util.loadImage(
                 viewBinding.imageView,
                 country.getFlag(),
                 Util.getProgressDrawable(viewBinding.imageView.getContext())
         );
      }
   }

}
