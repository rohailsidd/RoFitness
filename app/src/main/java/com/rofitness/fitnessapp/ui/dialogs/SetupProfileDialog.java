package com.rofitness.fitnessapp.ui.dialogs;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;

import com.facebook.drawee.view.SimpleDraweeView;
import com.rofitness.fitnessapp.AppPreferences;
import com.rofitness.fitnessapp.FitnessApp;
import com.rofitness.fitnessapp.Utils.BmiUtility;
import com.rofitness.fitnessapp.database.entities.UserAccount;
import com.rofitness.fitnessapp.ui.activities.HomePageActivity;
import com.rofitness.fitnessapp.R;

import java.io.File;

@SuppressWarnings("ALL")
public class SetupProfileDialog extends DialogFragment {
    UserAccount userAccount;
    private Button btnSave;
    private EditText edtAge;
    private EditText edtHeightCm;
    private EditText edtHeightFeet;
    private EditText edtHeightInches;
    private EditText edtName;
    private EditText edtWaist;
    private EditText edtWeight;
    private Spinner heightSpinner;
    private SimpleDraweeView profileImage;
    private View view;
    private Spinner waistSpinner;
    private Spinner weightSpinner;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.layout_setup_profile, viewGroup);
        this.view = inflate;
        this.btnSave = (Button) inflate.findViewById(R.id.button_setupProfile_save);
        this.edtName = (EditText) this.view.findViewById(R.id.editText_setupProfile_name);
        this.edtAge = (EditText) this.view.findViewById(R.id.editText_setupProfile_age);
        this.edtHeightCm = (EditText) this.view.findViewById(R.id.heightTextFieldInCM);
        this.edtHeightFeet = (EditText) this.view.findViewById(R.id.editText_setupProfile_height1);
        this.edtHeightInches = (EditText) this.view.findViewById(R.id.editText_setupProfile_height2);
        this.edtWeight = (EditText) this.view.findViewById(R.id.editText_setupProfile_weight);
        this.edtWaist = (EditText) this.view.findViewById(R.id.editText_setupProfile_waist);
        this.waistSpinner = (Spinner) this.view.findViewById(R.id.waistSpinner);
        this.weightSpinner = (Spinner) this.view.findViewById(R.id.spinner_setupProfile_weight);
        this.heightSpinner = (Spinner) this.view.findViewById(R.id.spinner_setupProfile_height);
        this.profileImage = (SimpleDraweeView) this.view.findViewById(R.id.imageView_setupProfile_profilePhoto);
        return this.view;
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.heightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    SetupProfileDialog.this.edtHeightCm.setVisibility(android.view.View.INVISIBLE);
                    SetupProfileDialog.this.edtHeightInches.setVisibility(android.view.View.VISIBLE);
                    SetupProfileDialog.this.edtHeightFeet.setVisibility(android.view.View.VISIBLE);
                    return;
                }
                SetupProfileDialog.this.edtHeightCm.setVisibility(android.view.View.VISIBLE);
                SetupProfileDialog.this.edtHeightInches.setVisibility(android.view.View.INVISIBLE);
                SetupProfileDialog.this.edtHeightFeet.setVisibility(android.view.View.INVISIBLE);
            }
        });
        this.profileImage.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                ((HomePageActivity) SetupProfileDialog.this.getActivity()).startImageCropper();
            }
        });
        FitnessApp.getInstance().fitnessRepository.getUserAccount().observe(this, new Observer<UserAccount>() {


            public void onChanged(UserAccount userAccount) {
                SetupProfileDialog.this.userAccount = userAccount;
                SetupProfileDialog.this.setUp();
            }
        });
        this.btnSave.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                double d;
                double d2;
                double d3;
                int selectedItemPosition = SetupProfileDialog.this.heightSpinner.getSelectedItemPosition();
                String str = "0";
                String str2 = "0.0";
                if (selectedItemPosition == 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(SetupProfileDialog.this.edtHeightFeet.getText().toString().length() > 0 ? SetupProfileDialog.this.edtHeightFeet.getText().toString() : str);
                    sb.append(".");
                    sb.append(SetupProfileDialog.this.edtHeightInches.getText().toString().length() > 0 ? SetupProfileDialog.this.edtHeightInches.getText() : str);
                    d2 = Double.parseDouble(sb.toString());
                    d = BmiUtility.convertFeetandInchesToCentimeter(SetupProfileDialog.this.edtHeightFeet.getText().toString(), SetupProfileDialog.this.edtHeightFeet.getText().toString());
                } else {
                    d2 = Double.parseDouble(SetupProfileDialog.this.edtHeightCm.getText().toString().length() > 0 ? SetupProfileDialog.this.edtHeightCm.getText().toString() : str2);
                    d = d2;
                }
                if (SetupProfileDialog.this.weightSpinner.getSelectedItemPosition() == 0) {
                    d3 = Double.parseDouble(SetupProfileDialog.this.edtWeight.getText().toString().length() > 0 ? SetupProfileDialog.this.edtWeight.getText().toString() : str2);
                } else {
                    d3 = BmiUtility.poundToKg(Double.parseDouble(SetupProfileDialog.this.edtWeight.getText().toString().length() > 0 ? SetupProfileDialog.this.edtWeight.getText().toString() : str2));
                }
                boolean z = true;
                if (SetupProfileDialog.this.userAccount == null) {
                    z = false;
                    SetupProfileDialog.this.userAccount = new UserAccount();
                }
                UserAccount userAccount = SetupProfileDialog.this.userAccount;
                if (SetupProfileDialog.this.edtAge.getText().toString().length() > 0) {
                    str = SetupProfileDialog.this.edtAge.getText().toString();
                }
                userAccount.setAge(Integer.parseInt(str));
                SetupProfileDialog.this.userAccount.setWeight(Double.parseDouble(SetupProfileDialog.this.edtWeight.getText().toString().length() > 0 ? SetupProfileDialog.this.edtWeight.getText().toString() : str2));
                UserAccount userAccount2 = SetupProfileDialog.this.userAccount;
                if (SetupProfileDialog.this.edtWaist.getText().toString().length() > 0) {
                    str2 = SetupProfileDialog.this.edtWaist.getText().toString();
                }
                userAccount2.setWaist(Double.parseDouble(str2));
                SetupProfileDialog.this.userAccount.setHeightUnit(SetupProfileDialog.this.heightSpinner.getSelectedItem().toString());
                SetupProfileDialog.this.userAccount.setWeightUnit(SetupProfileDialog.this.weightSpinner.getSelectedItem().toString());
                SetupProfileDialog.this.userAccount.setWaistUnit(SetupProfileDialog.this.waistSpinner.getSelectedItem().toString());
                SetupProfileDialog.this.userAccount.setName(SetupProfileDialog.this.edtName.getText().toString());
                SetupProfileDialog.this.userAccount.setHeight(d2);
                if (z) {
                    FitnessApp.getInstance().fitnessRepository.updateUserAccount(SetupProfileDialog.this.userAccount);
                } else {
                    FitnessApp.getInstance().fitnessRepository.insertUserAccount(SetupProfileDialog.this.userAccount);
                }
                if (d > 0.0d && d3 > 0.0d) {
                    FitnessApp.getInstance().appPreferences.put(AppPreferences.Key.bmi, BmiUtility.displayBMI((double) ((float) BmiUtility.calculationBMI(d3, d))));
                    HomePageActivity homePageActivity = (HomePageActivity) SetupProfileDialog.this.getActivity();
                    if (homePageActivity != null) {
                        homePageActivity.bmiChanged();
                    }
                }
                SetupProfileDialog.this.dismiss();
            }
        });
    }

    public void setUp() {
        UserAccount userAccount2 = this.userAccount;
        if (userAccount2 != null) {
            this.edtName.setText(userAccount2.getName());
            if (this.userAccount.getAge() > 0) {
                this.edtAge.setText(String.valueOf(this.userAccount.getAge()));
            }
            if (this.userAccount.getWaist() > 0.0d) {
                this.edtWaist.setText(String.valueOf(this.userAccount.getWaist()));
            }
            if (this.userAccount.getWeight() > 0.0d) {
                this.edtWeight.setText(String.valueOf(this.userAccount.getWeight()));
            }
            if (this.userAccount.getHeightUnit().equals("ft+in")) {
                String[] split = String.valueOf(this.userAccount.getHeight()).split("\\.");
                if (Integer.parseInt(split[0]) > 0) {
                    this.edtHeightFeet.setText(split[0]);
                }
                if (Integer.parseInt(split[1]) > 0) {
                    this.edtHeightInches.setText(split[1]);
                }
                this.heightSpinner.setSelection(0);
            } else {
                String valueOf = String.valueOf(this.userAccount.getHeight());
                if (Double.parseDouble(valueOf) > 0.0d) {
                    this.edtHeightCm.setText(valueOf);
                }
                this.heightSpinner.setSelection(1);
            }
            if (this.userAccount.getWeightUnit().equals("kg")) {
                this.weightSpinner.setSelection(0);
            } else {
                this.weightSpinner.setSelection(1);
            }
            if (this.userAccount.getWaistUnit().equals("in")) {
                this.waistSpinner.setSelection(0);
            } else {
                this.waistSpinner.setSelection(1);
            }
            if (this.userAccount.getImage() != null) {
                this.profileImage.setImageURI(Uri.fromFile(new File(this.userAccount.getImage())));
                return;
            }
            this.profileImage.setActualImageResource(R.drawable.all_camera_icon);
        }
    }
}
