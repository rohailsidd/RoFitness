package com.rofitness.fitnessapp.ui.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.facebook.drawee.view.SimpleDraweeView;

import com.rofitness.fitnessapp.Utils.AppConstants;
import com.rofitness.fitnessapp.dto.TargetMuscle;

import com.rofitness.fitnessapp.ui.adapters.TargetMuscleAdapter;
import com.rofitness.fitnessapp.ui.listeners.ITragetMuscleActivity;
import com.rofitness.fitnessapp.ui.viewmodels.ViewModelFactory;
import com.rofitness.fitnessapp.ui.viewmodels.WorkoutsViewModel;
import com.rofitness.fitnessapp.R;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TargetMuscleActivity extends BaseActivity implements ITragetMuscleActivity {
    String categoryName;
    String imageLink;
    SimpleDraweeView imageViewHeader;
    ImageView imageViewResetProgress;
    RoundCornerProgressBar progressBar;
    RecyclerView recyclerViewTargetMuscles;
    TargetMuscleAdapter targetMuscleAdapter;
    List<TargetMuscle> targetMuscleList;
    TextView textViewCategoryName;
    TextView textViewProgressPercentage;
    Toolbar toolbar;
    View viewHeader;
    WorkoutsViewModel viewModel;
    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_target_muscle);
        if (bundle != null) {
            this.categoryName = bundle.getString(AppConstants.categoryNameKey);
            this.imageLink = bundle.getString(AppConstants.imageLinkKey);
        } else {
            this.categoryName = getIntent().getExtras().getString(AppConstants.categoryNameKey);
            this.imageLink = getIntent().getExtras().getString(AppConstants.imageLinkKey);
        }
        this.viewModel = (WorkoutsViewModel) ViewModelProviders.of(this, this.viewModelFactory).get(WorkoutsViewModel.class);
        bindViews();
        initViews();
        initListener();
    }

    private void initViews() {
        if (this.categoryName != null) {
            TextView textView = this.textViewCategoryName;
            textView.setText(this.categoryName + "\n" + getString(R.string.text_workouts));
            this.imageViewHeader.setImageURI(getHeaderImageUri(this.categoryName));
            setProgress();
            setupRecyclerView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setProgress();
        setupRecyclerView();
    }

    private void initListener() {
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                TargetMuscleActivity.this.finish();
            }
        });
        this.imageViewResetProgress.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                new FancyAlertDialog.Builder(TargetMuscleActivity.this).setTitle(TargetMuscleActivity.this.getString(R.string.reset_dialog_title)).setBackgroundColor(Color.parseColor("#303F9F")).setMessage(TargetMuscleActivity.this.getString(R.string.reset_dialog_message)).setNegativeBtnText(TargetMuscleActivity.this.getString(R.string.reset_progress_dialog_negative_btn_text)).setBackgroundColor(ContextCompat.getColor(TargetMuscleActivity.this, R.color.white)).setPositiveBtnText(TargetMuscleActivity.this.getString(R.string.reset_dialog_positive_btn_text)).setPositiveBtnBackground(ContextCompat.getColor(TargetMuscleActivity.this, R.color.colorPrimary)).setNegativeBtnBackground(Color.parseColor("#FFA9A7A8")).setAnimation(Animation.POP).isCancellable(true).setIcon(R.drawable.dialog_logo, com.shashank.sony.fancydialoglib.Icon.Visible).OnPositiveClicked(new FancyAlertDialogListener() {


                    @Override
                    public void OnClick() {
       ///                 Iterator<TargetMuscle> it = TargetMuscleActivity.this.viewModel.getSubCategories(TargetMuscleActivity.this.categoryName).iterator();
       ///                 while (it.hasNext()) {
       ///                 }
                        TargetMuscleActivity.this.viewModel.resetCategoryTypeProgress(TargetMuscleActivity.this.categoryName);
                        TargetMuscleActivity.this.setProgress();
                        TargetMuscleActivity.this.setupRecyclerView();
                    }
                }).OnNegativeClicked(new FancyAlertDialogListener() {


                    @Override
                    public void OnClick() {
                    }
                }).build();
            }
        });
    }

    private void bindViews() {
        this.recyclerViewTargetMuscles = (RecyclerView) findViewById(R.id.recyclerView_targetMuscle_musclesList);
        View findViewById = findViewById(R.id.layout_targetMuscle_header);
        this.viewHeader = findViewById;
        this.toolbar = (Toolbar) findViewById.findViewById(R.id.toolbar_workoutHeader);
        this.imageViewResetProgress = (ImageView) this.viewHeader.findViewById(R.id.imageView_workoutHeader_reset);
        this.progressBar = (RoundCornerProgressBar) this.viewHeader.findViewById(R.id.roundCornerProgressBar_workoutHeader_workoutOverAllProgress);
        this.textViewProgressPercentage = (TextView) this.viewHeader.findViewById(R.id.textView_workoutHeader_percentProgress);
        this.textViewCategoryName = (TextView) this.viewHeader.findViewById(R.id.textView_workoutHeader_title);
        this.imageViewHeader = (SimpleDraweeView) this.viewHeader.findViewById(R.id.imageView_workoutHeader_headerBackground);
    }

    public void setupRecyclerView() {
        this.recyclerViewTargetMuscles.removeAllViews();
        this.targetMuscleList = new ArrayList();
        List<TargetMuscle> subCategories2 = this.viewModel.getSubCategories(this.categoryName);
        this.targetMuscleList = subCategories2;
        TargetMuscleAdapter targetMuscleAdapter2 = new TargetMuscleAdapter(subCategories2, this);
        this.targetMuscleAdapter = targetMuscleAdapter2;
        this.recyclerViewTargetMuscles.setAdapter(targetMuscleAdapter2);
    }

    @Override
    public void onClick(int i) {
        List<TargetMuscle> list = this.targetMuscleList;
        if (list != null && list.size() != 0) {
            TargetMuscle targetMuscle = this.targetMuscleList.get(i);
            Intent intent = new Intent(this, SubWorkoutsActivity.class);
            if (i % 2 == 0) {
                intent.putExtra(AppConstants.imageLinkKey, targetMuscle.getBackgroundImage());
            } else {
                intent.putExtra(AppConstants.imageLinkKey, targetMuscle.getBackgroundMirroredImage());
            }
            intent.putExtra(AppConstants.categoryNameKey, this.categoryName);
            intent.putExtra(AppConstants.subCategoryNameKey, targetMuscle.getMuscleName());
            startActivity(intent);
        }
    }

    public void setProgress() {
        float mainBodyGroupProgress = this.viewModel.getMainBodyGroupProgress(this.categoryName);
        this.progressBar.setProgress(mainBodyGroupProgress);
        this.textViewProgressPercentage.setText(getString(R.string.text_progress_completed_target_muscle_header, new Object[]{String.valueOf(mainBodyGroupProgress)}));
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putString(AppConstants.categoryNameKey, this.categoryName);
        bundle.putString(AppConstants.imageLinkKey, this.imageLink);
        super.onSaveInstanceState(bundle);
    }

    private String getHeaderImageUri(String str) {
        String trim = str.replace(" ", "").toLowerCase().trim();
        return AppConstants.path + trim + "header.webp";
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
