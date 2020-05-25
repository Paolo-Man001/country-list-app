package com.paolomanlunas.countriesapp.view;

import android.content.Context;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.paolomanlunas.countriesapp.R;

public class Util {

   //--- Method enables 'Glide' to be available from anywhere:
   public static void loadImage(ImageView imageView, String url, CircularProgressDrawable progressDrawable) {
      RequestOptions options = new RequestOptions()
              .placeholder(progressDrawable)
              .error(R.mipmap.ic_launcher_round);
      Glide.with(imageView.getContext())
              .setDefaultRequestOptions(options)
              .load(url)
              .into(imageView);
   }

   //--- ProgressBar shows as Image-is-loading. Args == context where this progressBar should show.
   public static CircularProgressDrawable getProgressDrawable(Context context) {
      CircularProgressDrawable progressDrawable = new CircularProgressDrawable(context);
      progressDrawable.setStrokeWidth(10f);
      progressDrawable.setCenterRadius(50f);
      progressDrawable.start();
      return progressDrawable;
   }
}
