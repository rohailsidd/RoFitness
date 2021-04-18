package com.rofitness.fitnessapp.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.DialogFragment;

import com.rofitness.fitnessapp.AppPreferences;
import com.rofitness.fitnessapp.FitnessApp;
import com.rofitness.fitnessapp.Utils.BmiUtility;

import com.rofitness.fitnessapp.ui.dialogs.BmiinfoDialog;
import com.rofitness.fitnessapp.ui.helperviews.BMICalculator;
import com.rofitness.fitnessapp.ui.helperviews.ScaleView;
import com.rofitness.fitnessapp.ui.listeners.IRulerValuePickListener;
import com.rofitness.fitnessapp.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class BmiCalculatorActivity extends BaseActivity implements BmiinfoDialog.BmiInfoDialogListener {
    double[] biasednessIntervels = {5.0d, 23.0d, 41.0d, 59.0d, 77.0d, 95.0d};
    BMICalculator bmiCalculator;
    double[] bmiRangeIntervals = {15.0d, 25.0d, 30.0d, 35.0d, 39.0d, 40.0d};
    double bmiValue;
    BmiinfoDialog dialog;
    View heightScale;
    ConstraintLayout.LayoutParams layoutParamsViewScale;
    ScaleView scaleViewHeight;
    ScaleView scaleViewWeight;
    TextView textViewBMI;
    View viewBmiValuePointer;
    double viewScaleBiasednes;
    View weightScale;

    private int getBiasednessInterval(double d) {
        if (d < 15.0d) {
            return -1;
        }
        if (d > 40.0d) {
            return 5;
        }
        if (d < 25.0d) {
            return 0;
        }
        if (d < 30.0d) {
            return 1;
        }
        if (d < 35.0d) {
            return 2;
        }
        if (d < 39.0d) {
            return 3;
        }
        return d <= 40.0d ? 4 : 0;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_bmi_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_bmiCalculator_appBar);
        toolbar.setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {


            @Override
            public final void onClick(View view) {
                BmiCalculatorActivity.this.lambda$onCreate$0$BmiCalculatorActivity(view);
            }
        });
        this.bmiCalculator = new BMICalculator();
        View findViewById = findViewById(R.id.include_bmiCalculator_heightScale);
        this.heightScale = findViewById;
        ScaleView scaleView = new ScaleView(findViewById, ScaleView.VIEW_LENGTH);
        this.scaleViewHeight = scaleView;
        setScaleColor(scaleView, R.color.bmi_height_scale_color);
        this.scaleViewHeight.setTitle(getResources().getString(R.string.text_height));
        this.scaleViewHeight.setMinMaxValue(122, 245);
        this.scaleViewHeight.setValuePickerListener(new IRulerValuePickListener() {


            @Override
            public void onValueChange(int i) {
            }

            @Override
            public void onIntermediateValueChange(int i) {
                BmiCalculatorActivity.this.bmiCalculator.setHeightInCM((double) i);
                BmiCalculatorActivity bmiCalculatorActivity = BmiCalculatorActivity.this;
                bmiCalculatorActivity.bmiValue = bmiCalculatorActivity.bmiCalculator.getBMIValue();
                BmiCalculatorActivity bmiCalculatorActivity2 = BmiCalculatorActivity.this;
                bmiCalculatorActivity2.viewScaleBiasednes = bmiCalculatorActivity2.updateBMIGraph(bmiCalculatorActivity2.bmiValue);
                BmiCalculatorActivity.this.textViewBMI.setText(BmiCalculatorActivity.this.bmiValueAsString());
                BmiCalculatorActivity bmiCalculatorActivity3 = BmiCalculatorActivity.this;
                bmiCalculatorActivity3.setBmiStatus(bmiCalculatorActivity3.bmiValue);
                BmiCalculatorActivity.this.layoutParamsViewScale.horizontalBias = (float) BmiCalculatorActivity.this.viewScaleBiasednes;
                BmiCalculatorActivity.this.viewBmiValuePointer.setLayoutParams(BmiCalculatorActivity.this.layoutParamsViewScale);
            }
        });
        View findViewById2 = findViewById(R.id.include_bmiCalculator_weightScale);
        this.weightScale = findViewById2;
        ScaleView scaleView2 = new ScaleView(findViewById2, ScaleView.VIEW_WEIGHT);
        this.scaleViewWeight = scaleView2;
        setScaleColor(scaleView2, R.color.bmi_weight_scale_color);
        this.scaleViewWeight.setTitle(getResources().getString(R.string.text_weight));
        this.scaleViewWeight.setMinMaxValue(66, 441);
        this.scaleViewWeight.setValuePickerListener(new IRulerValuePickListener() {


            @Override
            public void onValueChange(int i) {
            }

            @Override
            public void onIntermediateValueChange(int i) {
                BmiCalculatorActivity.this.bmiCalculator.setWeightInPounds((double) i);
                BmiCalculatorActivity bmiCalculatorActivity = BmiCalculatorActivity.this;
                bmiCalculatorActivity.bmiValue = bmiCalculatorActivity.bmiCalculator.getBMIValue();
                BmiCalculatorActivity bmiCalculatorActivity2 = BmiCalculatorActivity.this;
                bmiCalculatorActivity2.viewScaleBiasednes = bmiCalculatorActivity2.updateBMIGraph(bmiCalculatorActivity2.bmiValue);
                BmiCalculatorActivity.this.textViewBMI.setText(String.format(BmiCalculatorActivity.this.bmiValueAsString(), new Object[0]));
                BmiCalculatorActivity bmiCalculatorActivity3 = BmiCalculatorActivity.this;
                bmiCalculatorActivity3.setBmiStatus(bmiCalculatorActivity3.bmiValue);
                BmiCalculatorActivity.this.layoutParamsViewScale.horizontalBias = (float) BmiCalculatorActivity.this.viewScaleBiasednes;
                BmiCalculatorActivity.this.viewBmiValuePointer.setLayoutParams(BmiCalculatorActivity.this.layoutParamsViewScale);
            }
        });
        BMICalculator bMICalculator = new BMICalculator();
        this.bmiCalculator = bMICalculator;
        bMICalculator.setHeightInCM(200.0d);
        this.bmiCalculator.setWeightInPounds(100.0d);
        TextView textView = (TextView) findViewById(R.id.textView_bmiCalculator_bmi);
        this.textViewBMI = textView;
        textView.setText(String.format(bmiValueAsString(), new Object[0]));
        setBmiStatus(this.bmiCalculator.getBMIValue());
        View findViewById3 = findViewById(R.id.view_bmiCalculator_pointerPosition);
        this.viewBmiValuePointer = findViewById3;
        this.layoutParamsViewScale = (ConstraintLayout.LayoutParams) findViewById3.getLayoutParams();
    }

    public void lambda$onCreate$0$BmiCalculatorActivity(View view) {
        onBackPressed();
    }

    public void setBmiStatus(double d) {
        String displayBMI = BmiUtility.displayBMI(d);
        ((TextView) findViewById(R.id.textView_bmiCalculator_bmi_status)).setText("(" + displayBMI + ")");
    }

    private void setScaleColor(ScaleView scaleView, int i) {
        scaleView.setBackgroundColor(ResourcesCompat.getColor(getResources(), i, null));
    }

    public double updateBMIGraph(double d) {
        float f;
        int biasednessInterval = getBiasednessInterval(d);
        if (biasednessInterval == -1) {
            f = 0.025f;
        } else if (biasednessInterval == 5) {
            f = 0.975f;
        } else {
            double d2 = this.biasednessIntervels[biasednessInterval];
            double[] dArr = this.bmiRangeIntervals;
            f = ((float) (((((d - dArr[biasednessInterval]) / (dArr[biasednessInterval + 1] - dArr[biasednessInterval])) * 100.0d) * 0.16d) + d2)) / 100.0f;
        }
        return (double) f;
    }

    public void infoClick(View view) {
        BmiinfoDialog bmiinfoDialog = new BmiinfoDialog();
        this.dialog = bmiinfoDialog;
        bmiinfoDialog.show(getSupportFragmentManager(), BmiinfoDialog.class.getName());
    }

    @Override
    public void onDialogClick(DialogFragment dialogFragment) {
        dialogFragment.dismiss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public String bmiValueAsString() {
        double bMIValue = this.bmiCalculator.getBMIValue();
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(new Locale(FitnessApp.getInstance().appPreferences.getString(AppPreferences.Key.language, Locale.getDefault().getLanguage()))));
        return decimalFormat.format(bMIValue);
    }
}
