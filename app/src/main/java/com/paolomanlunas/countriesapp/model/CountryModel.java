package com.paolomanlunas.countriesapp.model;

public class CountryModel {
   String countryName;
   String capital;
   String flag;

   public CountryModel(String countryName, String capital, String flag) {
      this.countryName = countryName;
      this.capital = capital;
      this.flag = flag;
   }

   //---- Getters:
   public String getCountryName() {
      return countryName;
   }

   public String getCapital() {
      return capital;
   }

   public String getFlag() {
      return flag;
   }
}
