package com.rofitness.fitnessapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.rofitness.fitnessapp.FitnessApp;
import com.rofitness.fitnessapp.database.dataobjects.Category;
import com.rofitness.fitnessapp.imagehelper.ImageLoadingAdapter;
import com.rofitness.fitnessapp.ui.listeners.IHomePageListener;
import com.rofitness.fitnessapp.R;

import java.util.ArrayList;
import java.util.List;

public class WorkoutsPageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int TYPE_AD_SECOND_LAST_POSITION = -1;
    private int TYPE_AD_THIRD_POSITION = 2;
    private List<Category> categories = new ArrayList();
    private IHomePageListener mIHomePageListener;

    public WorkoutsPageAdapter(IHomePageListener iHomePageListener) {
        this.mIHomePageListener = iHomePageListener;
    }

    public void setCategories(List<Category> list) {
        this.categories = list;
        this.TYPE_AD_THIRD_POSITION = 2;
        this.TYPE_AD_SECOND_LAST_POSITION = list.size() - 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_workout, viewGroup, false);
        return new WorkoutsVH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_workout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        Category category = this.categories.get(i);
        int size = FitnessApp.getInstance().generalWorkoutPlans.get(category.getMainBodyGroup()).size();
        WorkoutsVH workoutsVH = (WorkoutsVH) viewHolder;
        workoutsVH.setCategory(category);
        workoutsVH.setCategoryTag(getTag(category, viewHolder.itemView));
        workoutsVH.setExercisesCounter(Integer.valueOf(size));
        workoutsVH.setIWorkoutFragment(this.mIHomePageListener);

        String string = workoutsVH.textView_itemWorkout_level.getResources().getString(R.string.workout_tag_message, Integer.valueOf(size), getTag(category, viewHolder.itemView));

        workoutsVH.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIHomePageListener.onItemClick(category);
            }
        });
        workoutsVH.textView_itemWorkout_type.setText(category.getMainBodyGroup().toUpperCase());
        workoutsVH.textView_itemWorkout_level.setText(string);
        ImageLoadingAdapter.loadImage(workoutsVH.imageViewItemWorkoutBackground, category.getImageLink());
    }

    private String getTag(Category category, View view) {
        return view.getContext().getResources().getString(R.string.category_tag_programs);
    }

    @Override
    public int getItemViewType(int i) {
        int i2 = this.TYPE_AD_THIRD_POSITION;
        if (i == i2) {
            return i2;
        }
        return i == this.categories.size() + -2 ? this.TYPE_AD_SECOND_LAST_POSITION : i;
    }

    @Override
    public int getItemCount() {
        return this.categories.size();
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        super.onViewRecycled(viewHolder);
    }

    class WorkoutsVH extends RecyclerView.ViewHolder {

        private TextView textView_itemWorkout_type;
        private TextView textView_itemWorkout_level;
        private SimpleDraweeView imageViewItemWorkoutBackground;
        private String mCategoryTag;
        private Category mCategory;
        private Integer mExercisesCounter;
        private IHomePageListener mIWorkoutFragment;

        WorkoutsVH(View view) {
            super(view);
            textView_itemWorkout_type = view.findViewById(R.id.textView_itemWorkout_type);
            textView_itemWorkout_level = view.findViewById(R.id.textView_itemWorkout_level);
            imageViewItemWorkoutBackground = view.findViewById(R.id.imageViewItemWorkoutBackground);
        }

        public void setCategoryTag(String str) {
            this.mCategoryTag = str;
        }

        public void setCategory(Category category) {
            this.mCategory = category;
        }

        public void setExercisesCounter(Integer num) {
            this.mExercisesCounter = num;
        }

        public void setIWorkoutFragment(IHomePageListener iHomePageListener) {
            this.mIWorkoutFragment = iHomePageListener;
        }
    }


}
