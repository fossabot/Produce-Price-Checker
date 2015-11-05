package com.jarvislin.producepricechecker.page.PriceList;

import android.content.Context;
import android.util.AttributeSet;

import com.jarvislin.producepricechecker.adapter.CustomerAdapter;
import com.jarvislin.producepricechecker.adapter.MerchantAdapter;
import com.jarvislin.producepricechecker.database.Produce;
import com.jarvislin.producepricechecker.util.Preferences_;

import org.androidannotations.annotations.EView;

import java.util.ArrayList;

import flow.path.Path;

/**
 * Created by jarvis on 15/9/23.
 */
@EView
public class MerchantPage extends PriceListPage {

    public MerchantPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        Path.get(context);
    }

    @Override
    protected CustomerAdapter getAdapter(Context context, ArrayList<Produce> list, Preferences_ prefs, String bookmarkCategory) {
        return new MerchantAdapter(context, list, prefs, bookmarkCategory);
    }

    @Override
    protected boolean enableSpinner() {
        return true;
    }

}
