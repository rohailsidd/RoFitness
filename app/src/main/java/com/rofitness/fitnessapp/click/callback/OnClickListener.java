package com.rofitness.fitnessapp.click.callback;

import android.view.View;

public final class OnClickListener implements View.OnClickListener {
    final Listener mListener;
    final int mSourceId;

    public OnClickListener(Listener listener, int i) {
        this.mListener = listener;
        this.mSourceId = i;
    }

    @Override
    public void onClick(View view) {
        this.mListener._internalCallbackOnClick(this.mSourceId, view);
    }

    public interface Listener {
        void _internalCallbackOnClick(int i, View view);
    }
}
