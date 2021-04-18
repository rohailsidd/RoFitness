package com.rofitness.fitnessapp.ui.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.FileUtils;
import com.ceylonlabs.imageviewpopup.ImagePopup;

import com.facebook.drawee.view.SimpleDraweeView;

import com.google.android.material.navigation.NavigationView;
import com.rofitness.fitnessapp.AppPreferences;
import com.rofitness.fitnessapp.FitnessApp;
import com.rofitness.fitnessapp.Utils.AppConstants;
import com.rofitness.fitnessapp.Utils.RunTimePermissions;
import com.rofitness.fitnessapp.database.dataobjects.Category;
import com.rofitness.fitnessapp.database.entities.UserAccount;
import com.rofitness.fitnessapp.imagehelper.ImageLoadingAdapter;

import com.rofitness.fitnessapp.managers.SharedPreferencesManager;
import com.rofitness.fitnessapp.ui.adapters.WorkoutsPageAdapter;
import com.rofitness.fitnessapp.ui.dialogs.SetupProfileDialog;
import com.rofitness.fitnessapp.ui.listeners.BmiListener;
import com.rofitness.fitnessapp.ui.listeners.IHomePageListener;
import com.rofitness.fitnessapp.ui.viewmodels.ViewModelFactory;
import com.rofitness.fitnessapp.ui.viewmodels.WorkoutsViewModel;
import com.rofitness.fitnessapp.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import org.greenrobot.eventbus.EventBus;

@SuppressWarnings("ALL")
public class HomePageActivity extends BaseActivity implements BmiListener, IHomePageListener {
    private static final int CROP_REQUEST = 2;
    private static final int GALLARY_REQUEST = 1;
    private static final int PERMISSION_REQUEST = 3;
    private final String TAG = HomePageActivity.class.getSimpleName();
    TextView bmiText;
    DrawerLayout drawerLayout;
    ImagePopup imagePopup;
    Dialog myDialog;
    NavigationView navigationView;
    SimpleDraweeView profile;
    TextView progressText;
    RecyclerView recyclerViewWorkouts;
    TextView userName;
    @Inject
    ViewModelFactory viewModelFactory;
    WorkoutsPageAdapter workoutsPageAdapter;
    private String currentLanguage = "";
    private AlertDialog exitDialog;
    private ImageView imageViewDrawer;
    private UserAccount userAccount;
    private WorkoutsViewModel workoutsViewModel;

    static void lambda$prepareExitDialog$1(DialogInterface dialogInterface, int i) {
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_home_page);
        this.currentLanguage = FitnessApp.getInstance().appPreferences.getString(AppPreferences.Key.language, AppConstants.Settings.defaultLanguage);
        prepareExitDialog();

        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout_home_page);
        NavigationView navigationView2 = (NavigationView) findViewById(R.id.navigationView_home_page);
        this.navigationView = navigationView2;
        navigationView2.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override

            public boolean onNavigationItemSelected(MenuItem menuItem) {
                HomePageActivity.this.drawerLayout.closeDrawer(GravityCompat.START, false);
                switch (menuItem.getItemId()) {
                    case R.id.about:
                        HomePageActivity.this.startActivity(new Intent(HomePageActivity.this, AboutActivity.class));
                        break;
                    case R.id.calcBmi:
                        HomePageActivity.this.startActivity(new Intent(HomePageActivity.this, BmiCalculatorActivity.class));
                        break;
                    case R.id.settings:
                        HomePageActivity.this.startActivity(new Intent(HomePageActivity.this, SettingsActivity.class));
                        break;
                }
                return false;
            }
        });
        ImageView imageView = (ImageView) findViewById(R.id.imageView_home_drawer);
        this.imageViewDrawer = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                HomePageActivity.this.drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        if (!SharedPreferencesManager.getInstance().getBoolean("IsAdsFree")) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration(50);
            alphaAnimation.setStartOffset(20);
            alphaAnimation.setRepeatMode(2);
            alphaAnimation.setRepeatCount(-1);

        }
        this.myDialog = new Dialog(this);
        View headerView = this.navigationView.getHeaderView(0);
        this.profile = (SimpleDraweeView) headerView.findViewById(R.id.imageView_drawerHeader_profileImage);
        this.userName = (TextView) headerView.findViewById(R.id.textView_drawerHeader_userName);
        this.bmiText = (TextView) headerView.findViewById(R.id.textView_drawerHeader_bmiValue);
        this.progressText = (TextView) headerView.findViewById(R.id.textView_drawerHeader_progressTextValue);
        this.profile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                HomePageActivity.this.imagePopup = new ImagePopup(HomePageActivity.this);
                HomePageActivity.this.imagePopup.setHideCloseIcon(true);
                HomePageActivity.this.imagePopup.setImageOnClickClose(true);
                HomePageActivity.this.imagePopup.setBackgroundColor(ContextCompat.getColor(HomePageActivity.this.getApplicationContext(), 17170445));
                HomePageActivity.this.imagePopup.initiatePopup(HomePageActivity.this.profile.getDrawable());
                HomePageActivity.this.showOptions(view);
            }
        });

        ((TextView) headerView.findViewById(R.id.textView_darwerHeader_editProfile)).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                HomePageActivity.this.setupProfile();
            }
        });
        ((ImageButton) headerView.findViewById(R.id.imageButton_drawerHeader_editProfile)).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                HomePageActivity.this.setupProfile();
            }
        });
        FitnessApp.getInstance().fitnessRepository.getUserAccount().observe(this, new Observer<UserAccount>() {


            public void onChanged(UserAccount userAccount) {
                HomePageActivity.this.userAccount = userAccount;
                if (userAccount != null) {
                    HomePageActivity.this.showProfile();
                }
            }
        });
        if (FitnessApp.getInstance().appPreferences.getBoolean(AppPreferences.Key.tapTargetRun, true)) {
            //  startTap();
        }
        this.workoutsViewModel = (WorkoutsViewModel) ViewModelProviders.of(this, this.viewModelFactory).get(WorkoutsViewModel.class);
        initRecyclerView();
    }

    private void initRecyclerView() {
        List<Category> categories = this.workoutsViewModel.getCategories();
        this.recyclerViewWorkouts = (RecyclerView) findViewById(R.id.recyclerView_home_workoutsList);
        this.workoutsPageAdapter = new WorkoutsPageAdapter(this);
       /* categories.add(2, null);
        categories.add(categories.size() - 1, null);*/
        this.recyclerViewWorkouts.setAdapter(this.workoutsPageAdapter);
        this.workoutsPageAdapter.setCategories(categories);
        this.workoutsPageAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.bmiText.setText(FitnessApp.getInstance().appPreferences.getString(AppPreferences.Key.bmi, getResources().getString(R.string.text_n_a)));
        if (FitnessApp.getInstance().getPlanList().size() != 0) {
        }
        if (!FitnessApp.getInstance().appPreferences.getString(AppPreferences.Key.language, AppConstants.Settings.defaultLanguage).equalsIgnoreCase(this.currentLanguage)) {
            recreate();
        }
    }


    @Override

    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    public void showProfile() {
        if (this.userAccount.getImage() != null) {
            ImageLoadingAdapter.loadImage(this.profile, Uri.fromFile(new File(this.userAccount.getImage())).toString());
        } else {
            this.profile.setActualImageResource(R.drawable.all_camera_icon);
        }
        this.userName.setText(this.userAccount.getName());
    }

    public void setupProfile() {
        this.drawerLayout.closeDrawers();
        new SetupProfileDialog().show(getSupportFragmentManager(), SetupProfileDialog.class.getName());
    }

    public void showOptions(View view) {
        final PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.imagepopup, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {


            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.removePhoto) {
                    if (HomePageActivity.this.userAccount != null) {
                        profile.setImageURI(new Uri.Builder().scheme("res").path(String.valueOf((int) R.drawable.all_camera_icon)).build());

                        //   ImageLoadingAdapter.loadImage(HomePageActivity.this.profile, new Uri.Builder().scheme("res").path(String.valueOf((int) R.drawable.all_camera_icon)).build().toString());
                        FitnessApp.getInstance().fitnessRepository.updateUserAccount(HomePageActivity.this.userAccount);
                    }
                    return true;
                } else if (itemId == R.id.uploadPhoto) {
                    HomePageActivity.this.startImageCropper();
                    return true;
                } else if (itemId != R.id.viewPhoto) {
                    return false;
                } else {
                    popupMenu.dismiss();
                    HomePageActivity.this.imagePopup.viewPopup();
                    return true;
                }
            }
        });
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {

            @Override
            public void onDismiss(PopupMenu popupMenu) {
                popupMenu.dismiss();
            }
        });
        popupMenu.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        this.drawerLayout.openDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void bmiChanged() {
        onResume();
    }

    @Override
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 203) {
            CropImage.ActivityResult activityResult = CropImage.getActivityResult(intent);
            if (i2 == -1) {
                Uri uri = activityResult.getUri();
                File filesDir = getFilesDir();
                String str = filesDir.getPath() + AppConstants.userProfileImagePath;
                FileUtils.moveFile(uri.getPath(), str);
                UserAccount userAccount2 = this.userAccount;
                if (userAccount2 == null) {
                    UserAccount userAccount3 = new UserAccount();
                    this.userAccount = userAccount3;
                    userAccount3.setImage(str);
                    FitnessApp.getInstance().fitnessRepository.insertUserAccount(this.userAccount);
                    return;
                }
                userAccount2.setImage(str);
                FitnessApp.getInstance().fitnessRepository.updateUserAccount(this.userAccount);
            } else if (i2 == 204) {
                activityResult.getError();
            }
        }
    }

    @Override

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i != 10) {
            return;
        }
        if (iArr[0] == 0 && iArr[1] == 0) {
            startImageCropper();
        }
    }

    public void startImageCropper() {
        if (RunTimePermissions.checkStoragePermission()) {
            CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(this);
        } else if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 10);
        }
    }

    private void prepareExitDialog() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_exit, (ViewGroup) null);

        AlertDialog create = new AlertDialog.Builder(this).create();
        this.exitDialog = create;
        create.setView(inflate);
        this.exitDialog.setButton(-1, getText(17039379), new DialogInterface.OnClickListener() {


            @Override
            public final void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
    }

    private void showExitDialog() {
        AlertDialog alertDialog = this.exitDialog;
        if (alertDialog != null) {
            alertDialog.show();
            this.exitDialog.getButton(-3).setTextColor(ContextCompat.getColor(this, R.color.black));
            this.exitDialog.getButton(-3).setTransformationMethod(null);
            this.exitDialog.getButton(-2).setTextColor(ContextCompat.getColor(this, 17170444));
            this.exitDialog.getButton(-2).setTransformationMethod(null);
            this.exitDialog.getButton(-1).setTextColor(ContextCompat.getColor(this, R.color.black));
            this.exitDialog.getButton(-1).setTransformationMethod(null);
        }
    }

    @Override
    public void onBackPressed() {
        showExitDialog();
    }


    @Override
    public void onItemClick(Category category) {
        String mainBodyGroup = category.getMainBodyGroup();
        Intent intent = new Intent(this, TargetMuscleActivity.class);
        intent.putExtra(AppConstants.imageLinkKey, category.getImageLink());
        intent.putExtra(AppConstants.categoryNameKey, mainBodyGroup);
        startActivity(intent);
    }


    public void onBannerCrossPromotionAdItemClick(View view) {
        Toast.makeText(this, "ad clicked", Toast.LENGTH_SHORT).show();
    }
}
