package com.rofitness.fitnessapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.rofitness.fitnessapp.FitnessApp;
import com.rofitness.fitnessapp.database.entities.Exercise;
import com.rofitness.fitnessapp.imagehelper.ImageLoadingAdapter;
import com.rofitness.fitnessapp.ui.activities.SubWorkoutsActivity;
import com.rofitness.fitnessapp.ui.listeners.ISubWorkoutActivityListener;
import com.rofitness.fitnessapp.R;

import java.util.ArrayList;
import java.util.List;

public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListAdapter.ExerciseViewHolder> {
    public SimpleDraweeView imageButtonSubWorkoutItemIcon;
    public ImageView imageViewSubWorkoutDone;
    public TextView textViewFavouritesItemRepetitions;
    public TextView textViewSubWorkoutItemExerciseName;
    protected Exercise mExercise;
    protected ISubWorkoutActivityListener mISubWorkoutActivityListener;
    protected Boolean mIsExerciseCompleted;
    protected Boolean mIsFromCategory;
    private int dayId = 0;
    private List<Exercise> exerciseList = new ArrayList();
    private ISubWorkoutActivityListener iSubWorkoutActivityListener;
    private boolean isFromCategory;
    private int planId = 0;
    private String tag;
    private int weekId = 0;
    private SubWorkoutsActivity mSubWorkoutsActivity;

    public ExerciseListAdapter(SubWorkoutsActivity activity, List<Exercise> list, boolean z, String str, ISubWorkoutActivityListener iSubWorkoutActivityListener2) {
        this.exerciseList = list;
        this.isFromCategory = z;
        this.tag = str;
        this.iSubWorkoutActivityListener = iSubWorkoutActivityListener2;
        mSubWorkoutsActivity = activity;
    }

    public ExerciseListAdapter(List<Exercise> list, boolean z, ISubWorkoutActivityListener iSubWorkoutActivityListener2, int i, int i2, int i3) {
        this.exerciseList = list;
        this.isFromCategory = z;
        this.iSubWorkoutActivityListener = iSubWorkoutActivityListener2;
        this.planId = i;
        this.dayId = i3;
        this.weekId = i2;
    }

    //  @BindingAdapter({"bind:exerciselist"})
    public void setExerciseList(List<Exercise> list) {
        this.exerciseList = list;
    }

    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ExerciseViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sub_workout, viewGroup, false));
    }

    public void onBindViewHolder(ExerciseViewHolder exerciseViewHolder, int i) {
        Exercise exercise = this.exerciseList.get(i);
        if (!this.isFromCategory) {
            setIsExerciseCompleted(FitnessApp.getInstance().fitnessRepository.isExerciseCompleted(this.planId, this.weekId, this.dayId, exercise.getId().longValue()));
        } else {
            setIsExerciseCompleted(FitnessApp.getInstance().fitnessRepository.isExerciseCompletedFromCategory(exercise.getId(), this.tag));
        }
        setExercise(exercise);
        setIsFromCategory(this.isFromCategory);
        setISubWorkoutActivityListener(this.iSubWorkoutActivityListener);
        //ISubWorkoutActivityListener iSubWorkoutActivityListener = mISubWorkoutActivityListener;
       /* if (iSubWorkoutActivityListener != null) {
            iSubWorkoutActivityListener.itemClickListener(mExercise);
        }*/
        textViewSubWorkoutItemExerciseName.setText(exercise.getExerciseTitle());
        if (exercise.getRepetitions().contains("sec")) {
            textViewFavouritesItemRepetitions.setText(exercise.getRepetitions());
        } else {
            textViewFavouritesItemRepetitions.setText(exercise.getRepetitions() + " Reps");
        }
        ImageLoadingAdapter.loadImage(imageButtonSubWorkoutItemIcon, exercise.getExerciseThumbnail());
        //ImageLoadingAdapter.setVisibilityExerciseCompleted(imageViewSubWorkoutDone, )
        imageViewSubWorkoutDone.setVisibility(mIsExerciseCompleted ? 0 : 8);
        exerciseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSubWorkoutsActivity.itemClick(exercise);
            }
        });
    }

    public void setIsExerciseCompleted(Boolean bool) {
        this.mIsExerciseCompleted = bool;
    }

    public void setExercise(Exercise exercise) {
        this.mExercise = exercise;
    }

    public void setIsFromCategory(Boolean bool) {
        this.mIsFromCategory = bool;
    }

    public void setISubWorkoutActivityListener(ISubWorkoutActivityListener iSubWorkoutActivityListener) {
        this.mISubWorkoutActivityListener = iSubWorkoutActivityListener;
    }

    @Override
    public int getItemCount() {
        return this.exerciseList.size();
    }

    class ExerciseViewHolder extends RecyclerView.ViewHolder {

        public ExerciseViewHolder(View view) {
            super(view);
            imageViewSubWorkoutDone = view.findViewById(R.id.imageView_subWorkout_done);
            imageButtonSubWorkoutItemIcon = view.findViewById(R.id.imageButton_subWorkoutItem_icon);
            textViewFavouritesItemRepetitions = view.findViewById(R.id.textView_favouritesItem_repetitions);
            textViewSubWorkoutItemExerciseName = view.findViewById(R.id.textView_subWorkoutItem_exerciseName);
        }
    }
}
