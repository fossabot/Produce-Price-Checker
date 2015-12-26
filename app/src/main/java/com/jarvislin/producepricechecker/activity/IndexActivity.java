package com.jarvislin.producepricechecker.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jarvislin.producepricechecker.BuildConfig;
import com.jarvislin.producepricechecker.R;
import com.jarvislin.producepricechecker.util.Constants;
import com.jarvislin.producepricechecker.util.GoogleAnalyticsSender;
import com.jarvislin.producepricechecker.util.Preferences_;
import com.jarvislin.producepricechecker.util.ToolsHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_index)
public class IndexActivity extends AppCompatActivity {

    @Pref
    Preferences_ prefs;
    @ViewById
    Button vegetable;
    @ViewById
    Button fruit;
    @ViewById
    Button settings;

    @AfterViews
    protected void showNews(){
        if(prefs.versionCode().get() != BuildConfig.VERSION_CODE){
            ToolsHelper.showDialog(this, "新功能",
                    "1. 資料來源改用開放資料。\n" +
                    "2. 選單新增分享功能。\n" + "3. 批發市場依蔬果分類。");
            prefs.versionCode().put(BuildConfig.VERSION_CODE);
        }
    }

    @Click
    protected void fruit() {
        GoogleAnalyticsSender.getInstance(this).send("click_fruit");
        Intent intent = new Intent(this, (prefs.userMode().get().equals(Constants.CUSTOMER) ? CustomerActivity_.class : MerchantActivity_.class));
        intent.putExtra("category", Constants.FRUIT);
        startActivity(intent);
    }

    @Click
    public void vegetable(View view) {
        GoogleAnalyticsSender.getInstance(this).send("click_vegetable");
        Intent intent = new Intent(this, (prefs.userMode().get().equals(Constants.CUSTOMER) ? CustomerActivity_.class : MerchantActivity_.class));
        intent.putExtra("category", Constants.VEGETABLE);
        IndexActivity.this.startActivity(intent);
    }

    @Click
    public void settings(View view) {
        GoogleAnalyticsSender.getInstance(this).send("click_settings");
        Intent intent = new Intent(IndexActivity.this, SettingsActivity_.class);
        IndexActivity.this.startActivity(intent);
    }
}