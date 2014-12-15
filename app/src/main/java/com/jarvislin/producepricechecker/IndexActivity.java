package com.jarvislin.producepricechecker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.jarvislin.producepricechecker.util.GoogleAnalyticsSender;
import com.jarvislin.producepricechecker.util.ToolsHelper;


public class IndexActivity extends Activity {

    private GoogleAnalyticsSender mSender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        if(!ToolsHelper.isNetworkAvailable(this))
            ToolsHelper.showNetworkErrorMessage(this);

        mSender = new GoogleAnalyticsSender(this);
    }

    public void fruit(View view) {
        mSender.send("click_fruit");
        Intent intent = new Intent(IndexActivity.this, DataListActivity.class);
        intent.putExtra("type", -1);
        IndexActivity.this.startActivity(intent);
    }

    public void vegetable(View view) {
        mSender.send("click_vegetable");
        Intent intent = new Intent(IndexActivity.this, DataListActivity.class);
        intent.putExtra("type", 1);
        IndexActivity.this.startActivity(intent);
    }

    public void settings(View view) {
        mSender.send("click_settings");
        Intent intent = new Intent(IndexActivity.this, SettingsActivity.class);
        IndexActivity.this.startActivity(intent);
    }
}
