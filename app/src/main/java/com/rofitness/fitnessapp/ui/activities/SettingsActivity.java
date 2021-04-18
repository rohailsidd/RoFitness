package com.rofitness.fitnessapp.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;

import com.rofitness.fitnessapp.AppPreferences;
import com.rofitness.fitnessapp.FitnessApp;
import com.rofitness.fitnessapp.Utils.AppConstants;
import com.rofitness.fitnessapp.database.rohailwork.entities.Language;

import com.rofitness.fitnessapp.ui.viewmodels.SettingsViewModel;
import com.rofitness.fitnessapp.ui.viewmodels.ViewModelFactory;
import com.rofitness.fitnessapp.R;

import java.util.List;

import javax.inject.Inject;

public class SettingsActivity extends BaseActivity implements View.OnClickListener {
    @Inject
    ViewModelFactory viewModelFactory;
    private ConstraintLayout languageClick;
    private List<String> languageList;
    private SettingsViewModel settingsViewModel;
    private TextView txt_about;
    private TextView txt_language;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.settingsViewModel = (SettingsViewModel) ViewModelProviders.of(this, this.viewModelFactory).get(SettingsViewModel.class);
        setContentView(R.layout.activity_settings);
        this.txt_about = (TextView) findViewById(R.id.textView_settings_about);
        this.languageClick = (ConstraintLayout) findViewById(R.id.constraintLayout_settings_selectLanguageClick);
        this.txt_language = (TextView) findViewById(R.id.textView_settings_languageName);
        this.languageList = this.settingsViewModel.getLanguages();
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar_settings_appBar);
        toolbar2.setNavigationOnClickListener(new View.OnClickListener() {


            @Override
            public final void onClick(View view) {
                SettingsActivity.this.lambda$onCreate$0$SettingsActivity(view);
            }
        });
        this.txt_about.setOnClickListener(this);
        this.languageClick.setOnClickListener(this);
        this.txt_language.setText(FitnessApp.getInstance().getLanguageName(FitnessApp.getInstance().appPreferences.getString(AppPreferences.Key.language, AppConstants.Settings.defaultLanguage)));
    }

    public void lambda$onCreate$0$SettingsActivity(View view) {
        onBackPressed();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.constraintLayout_settings_selectLanguageClick) {
            showChangeLanguageDialog();
        } else if (id == R.id.textView_settings_about) {
            startActivity(new Intent(this, AboutActivity.class));
        } /*else if (id == R.id.textView_settings_privacyPolicy) {
            startActivity(new Intent(this, PrivacyPolicyActivity.class));
        }*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void showChangeLanguageDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.change_language_dialog_title));
        builder.setSingleChoiceItems((String[]) this.languageList.toArray(new String[0]), -1, new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Language language = SettingsActivity.this.settingsViewModel.getLanguage((String) SettingsActivity.this.languageList.get(i));
                if (language != null) {
                    dialogInterface.dismiss();
                    FitnessApp.getInstance().setNewLocale(SettingsActivity.this.getApplicationContext(), language.getLanguageCode(), true);
                    FitnessApp.getInstance().appPreferences.put(AppPreferences.Key.languageId, language.getId().longValue());
                    SettingsActivity.this.recreate();
                }
            }
        });
        builder.create().show();
    }
}

