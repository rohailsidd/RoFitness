package com.rofitness.fitnessapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.facebook.drawee.view.SimpleDraweeView;
import com.rofitness.fitnessapp.dto.TargetMuscle;
import com.rofitness.fitnessapp.ui.listeners.ITragetMuscleActivity;
import com.rofitness.fitnessapp.R;

import java.util.List;

public class TargetMuscleAdapter extends RecyclerView.Adapter<TargetMuscleAdapter.TargetMuscleViewHolder> {
    private static int ITEM_TYPE_LEFT = 2;
    private static int ITEM_TYPE_RIGHT = 1;
    ITragetMuscleActivity iRecyclerViewClickListener;
    List<TargetMuscle> targetMuscleList;

    public TargetMuscleAdapter(List<TargetMuscle> list, ITragetMuscleActivity iTragetMuscleActivity) {
        this.targetMuscleList = list;
        this.iRecyclerViewClickListener = iTragetMuscleActivity;
    }

    @Override
    public TargetMuscleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view;
        if (i == ITEM_TYPE_RIGHT) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_target_muscle_right, viewGroup, false);
        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_target_muscle_left, viewGroup, false);
        }
        return new TargetMuscleViewHolder(view, i);
    }

    public void onBindViewHolder(TargetMuscleViewHolder targetMuscleViewHolder, int i) {
        TargetMuscle targetMuscle = this.targetMuscleList.get(i);
        targetMuscleViewHolder.textViewMuscleName.setText(targetMuscle.getMuscleName());
        targetMuscleViewHolder.imageViewBackground.setImageURI(targetMuscle.getBackgroundImage());
        TextView textView = targetMuscleViewHolder.textViewProgressPercentage;
        textView.setText(Math.round(targetMuscle.getProgress()) + targetMuscleViewHolder.itemView.getContext().getString(R.string.text_percentage_completed));
        targetMuscleViewHolder.progressBar.setProgress(targetMuscle.getProgress());
    }

    @Override
    public int getItemViewType(int i) {
        if (i % 2 == 0) {
            return ITEM_TYPE_RIGHT;
        }
        return ITEM_TYPE_LEFT;
    }

    @Override
    public int getItemCount() {
        return this.targetMuscleList.size();
    }

    public class TargetMuscleViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView imageViewBackground;
        RoundCornerProgressBar progressBar;
        TextView textViewMuscleName;
        TextView textViewProgressPercentage;

        public TargetMuscleViewHolder(View view, int i) {
            super(view);
            if (i == TargetMuscleAdapter.ITEM_TYPE_RIGHT) {
                this.textViewMuscleName = (TextView) view.findViewById(R.id.textView_targetMuscleRight_muscleName);
                this.textViewProgressPercentage = (TextView) view.findViewById(R.id.textView_targetMuscleRight_progressPercentage);
                this.imageViewBackground = (SimpleDraweeView) view.findViewById(R.id.imageView_targetMuscleRight_background);
                this.progressBar = (RoundCornerProgressBar) view.findViewById(R.id.roundCornerProgressBar_targetMuscleRight_progress);
            } else {
                this.textViewMuscleName = (TextView) view.findViewById(R.id.textView_targetMuscleLeft_muscleName);
                this.textViewProgressPercentage = (TextView) view.findViewById(R.id.textView_targetMuscleLeft_progressPercentage);
                this.imageViewBackground = (SimpleDraweeView) view.findViewById(R.id.imageView_targetMuscleLeft_background);
                this.progressBar = (RoundCornerProgressBar) view.findViewById(R.id.roundCornerProgressBar_targetMuscleLeft_progress);
            }
            view.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    TargetMuscleAdapter.this.iRecyclerViewClickListener.onClick(TargetMuscleViewHolder.this.getAdapterPosition());
                }
            });
        }
    }
}
