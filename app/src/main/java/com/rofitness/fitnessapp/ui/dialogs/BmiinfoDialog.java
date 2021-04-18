package com.rofitness.fitnessapp.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.rofitness.fitnessapp.R;

public class BmiinfoDialog extends DialogFragment {
    BmiInfoDialogListener mListener;
    private Button button;
    private BmiinfoDialog mDialogue;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.mListener = (BmiInfoDialogListener) context;
        } catch (ClassCastException unused) {
            throw new ClassCastException(getActivity().toString() + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.dialog_bmiinfodialog, (ViewGroup) null);
        button = inflate.findViewById(R.id.button_bmiInfoDialogue_gotIt);
        setDialogue(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickGotIt();
            }
        });
        builder.setView(inflate);
        return builder.create();
    }

    public void setDialogue(BmiinfoDialog bmiinfoDialog) {
        this.mDialogue = bmiinfoDialog;
    }

    public void OnClickGotIt() {
        this.mListener.onDialogClick(this);
    }

    public interface BmiInfoDialogListener {
        void onDialogClick(DialogFragment dialogFragment);
    }
}
