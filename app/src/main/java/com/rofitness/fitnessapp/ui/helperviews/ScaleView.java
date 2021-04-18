package com.rofitness.fitnessapp.ui.helperviews;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import com.rofitness.fitnessapp.ui.listeners.IRulerValuePickListener;
import com.rofitness.fitnessapp.R;
import com.kevalpatel2106.rulerpicker.RulerValuePicker;
import com.kevalpatel2106.rulerpicker.RulerValuePickerListener;

@SuppressWarnings("ALL")
public class ScaleView {
    public static int SECONDARY_INVISIBLE = 0;
    public static int SECONDARY_VISIBLE = 1;
    public static int VIEW_AGE = 0;
    public static int VIEW_LENGTH = 2;
    public static int VIEW_WEIGHT = 1;
    RulerValuePicker rulerValuePickerScale;
    String scaleUnit;
    TextView textViewPrimaryValue;
    TextView textViewSecondaryValue;
    TextView textViewTitle;
    private int VIEW_TYPE;
    private Resources res;

    public ScaleView(View view) {
        this.res = view.getContext().getResources();
        this.textViewTitle = (TextView) view.findViewById(R.id.textView_scale_title);
        this.textViewPrimaryValue = (TextView) view.findViewById(R.id.textView_scale_primaryValue);
        this.textViewSecondaryValue = (TextView) view.findViewById(R.id.textView_scale_secondaryValue);
        this.rulerValuePickerScale = (RulerValuePicker) view.findViewById(R.id.rulerPicker_scale);
    }

    public ScaleView(View view, int i) {
        this.res = view.getContext().getResources();
        this.textViewTitle = (TextView) view.findViewById(R.id.textView_scale_title);
        this.textViewPrimaryValue = (TextView) view.findViewById(R.id.textView_scale_primaryValue);
        this.textViewSecondaryValue = (TextView) view.findViewById(R.id.textView_scale_secondaryValue);
        this.rulerValuePickerScale = (RulerValuePicker) view.findViewById(R.id.rulerPicker_scale);
        this.VIEW_TYPE = i;
        if (i == VIEW_AGE) {
            this.scaleUnit = this.res.getString(R.string.bmi_scale_unit_year);
            this.textViewSecondaryValue.setVisibility(android.view.View.INVISIBLE);
        } else if (i == VIEW_LENGTH) {
            this.scaleUnit = this.res.getString(R.string.bmi_scale_unit_cm);
        } else if (i == VIEW_WEIGHT) {
            this.scaleUnit = this.res.getString(R.string.bmi_scale_unit_ponds);
        }
        setCurrentValue(0);
    }

    public void setCurrentValue(int i) {
        this.rulerValuePickerScale.selectValue(i);
        setPrimaryValue(i);
        setSecondaryValue(i);
    }

    public void setTitle(String str) {
        this.textViewTitle.setText(str);
    }

    public void setPrimaryValue(int i) {
        this.textViewPrimaryValue.setText(this.res.getString(R.string.bmi_scale_primary_value, Integer.valueOf(i), this.scaleUnit));
    }

    public void setPrimaryValue(String str) {
        this.textViewPrimaryValue.setText(str);
    }

    public void setSecondaryValue(String str) {
        this.textViewSecondaryValue.setText(str);
    }

    public void setSecondaryValue(int i) {
        int i2 = this.VIEW_TYPE;
        if (i2 == VIEW_LENGTH) {
            double d = (double) i;
            Double.isNaN(d);
            int i3 = (int) (d / 2.54d);
            this.textViewSecondaryValue.setText(this.res.getString(R.string.bmi_scale_secondary_value_height, Integer.valueOf(i3 / 12), Integer.valueOf(i3 % 12)));
        } else if (i2 == VIEW_WEIGHT) {
            double d2 = (double) i;
            Double.isNaN(d2);
            TextView textView = this.textViewSecondaryValue;
            Resources resources = this.res;
            double round = (double) Math.round((d2 / 2.205d) * 10.0d);
            Double.isNaN(round);
            textView.setText(resources.getString(R.string.bmi_scale_secondary_value_weight, Double.valueOf(round / 10.0d)));
        }
    }

    public void setBackgroundColor(int i) {
        this.rulerValuePickerScale.setBackgroundColor(i);
    }

    public void setScaleUnit(String str) {
        this.scaleUnit = str;
    }

    public void setMinMaxValue(int i, int i2) {
        this.rulerValuePickerScale.setMinMaxValue(i, i2);
    }

    public void setSecondaryValueVisibility(int i) {
        if (i == SECONDARY_VISIBLE) {
            this.textViewSecondaryValue.setVisibility(android.view.View.VISIBLE);
        } else {
            this.textViewSecondaryValue.setVisibility(android.view.View.INVISIBLE);
        }
    }

    public void setValuePickerListener(final IRulerValuePickListener iRulerValuePickListener) {
        this.rulerValuePickerScale.setValuePickerListener(new RulerValuePickerListener() {


            @Override
            public void onValueChange(int i) {
                iRulerValuePickListener.onValueChange(i);
            }

            @Override
            public void onIntermediateValueChange(int i) {
                ScaleView.this.textViewPrimaryValue.setText(ScaleView.this.res.getString(R.string.bmi_scale_primary_value, Integer.valueOf(i), ScaleView.this.scaleUnit));
                ScaleView.this.setSecondaryValue(i);
                iRulerValuePickListener.onIntermediateValueChange(i);
            }
        });
    }
}
