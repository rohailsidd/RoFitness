package com.rofitness.fitnessapp.imagehelper;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.google.firebase.analytics.FirebaseAnalytics;

public class ImageLoadingAdapter {
    private static String TAG = ImageLoadingAdapter.class.getName();

    public static void loadImage(SimpleDraweeView simpleDraweeView, String str) {
        if (simpleDraweeView != null) {
            BaseControllerListener r0 = new BaseControllerListener<ImageInfo>() {

                public void onFinalImageSet(String str, ImageInfo imageInfo, Animatable animatable) {
                    Log.d(ImageLoadingAdapter.TAG, FirebaseAnalytics.Param.SUCCESS);
                }

                @Override
                public void onFailure(String str, Throwable th) {
                    String str2 = ImageLoadingAdapter.TAG;
                    Log.d(str2, "failue" + th.getMessage());
                }
            };
            Log.d(TAG, str);
            simpleDraweeView.setController(((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setUri(Uri.parse(str)).setControllerListener(r0)).build());
        }
    }


    public static void setVisibilityExerciseCompleted(ImageView imageView, boolean z, boolean z2) {
        if (!z) {
            if (z2) {
                imageView.setVisibility(View.VISIBLE);
            } else {
                imageView.setVisibility(View.GONE);
            }
        } else if (z2) {
            imageView.setVisibility(android.view.View.VISIBLE);
        } else {
            imageView.setVisibility(android.view.View.GONE);
        }
    }


}
