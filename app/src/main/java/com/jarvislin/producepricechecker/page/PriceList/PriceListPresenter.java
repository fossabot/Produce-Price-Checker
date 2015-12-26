package com.jarvislin.producepricechecker.page.PriceList;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.jarvislin.producepricechecker.ApiClient;
import com.jarvislin.producepricechecker.BlankActivity;
import com.jarvislin.producepricechecker.BlankActivity_;
import com.jarvislin.producepricechecker.MainActivity_;
import com.jarvislin.producepricechecker.bean.DataLoader;
import com.jarvislin.producepricechecker.database.Produce;
import com.jarvislin.producepricechecker.model.ApiProduce;
import com.jarvislin.producepricechecker.model.HistoryDirectory;
import com.jarvislin.producepricechecker.model.ProduceData;
import com.jarvislin.producepricechecker.page.History.CustomerHistoryPath;
import com.jarvislin.producepricechecker.page.History.MerchantHistoryPath;
import com.jarvislin.producepricechecker.page.Index.IndexPath;
import com.jarvislin.producepricechecker.page.Presenter;
import com.jarvislin.producepricechecker.path.HandlesBack;
import com.jarvislin.producepricechecker.util.Constants;
import com.jarvislin.producepricechecker.util.Preferences_;
import com.jarvislin.producepricechecker.util.ToolsHelper;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.ArrayList;

import flow.Flow;
import flow.History;
import flow.path.Path;

/**
 * Created by jarvis on 15/9/23.
 */
@EBean
public class PriceListPresenter extends Presenter implements DataLoader.OnReceiveDataListener, HandlesBack {
    private PriceListPage page;
    private ProduceDataGetter path;
    @Bean
    protected DataLoader dataLoader;
    @Pref
    protected Preferences_ preferences;

    @Override
    protected void init(Path path, View view) {
        this.path = (ProduceDataGetter) path;
        this.page = (PriceListPage) view;
    }


    @Background
    public void loadData(String marketNumber) {
        ToolsHelper.showProgressDialog(getContext(), false);
        dataLoader.setOnReceiveDataListener(this);
        dataLoader.loadLatestData(getContext(), path.getData().getCategory(), marketNumber, path.getData().getUpdateDate(marketNumber), path.getData().getBookmarkCategory());
        ToolsHelper.closeProgressDialog(false);
    }

    @Background
    public void loadData() {
        dataLoader.setOnReceiveDataListener(this);
        ToolsHelper.showProgressDialog(getContext(), false);
        dataLoader.loadLatestData(getContext(), "", "", "", "");
        ToolsHelper.closeProgressDialog(false);
    }

    public ProduceData getProduceData() {
        return this.path.getData();
    }

    public boolean isLoaderAlive() {
        return dataLoader.isAlive();
    }

    @Override
    public void OnReceived(ArrayList<Produce> produces) {
        page.handleData(produces);
    }

    @Override
    public void OnFailed() {
        showToast("似乎是網路或伺服器出了點問題。", Toast.LENGTH_SHORT);
        onBackPressed();
    }

    public String getMarketNumber() {
        return dataLoader.getMarketNumber();
    }

    @Override
    public boolean onBackPressed() {
        Flow.get(getContext()).setHistory(History.single(new IndexPath()), Flow.Direction.BACKWARD);
        return false;
    }

    @Background
    public void fetchHistoryDirectory() {
        ToolsHelper.showProgressDialog(getContext(), false);
        HistoryDirectory directory = dataLoader.getHistoryDirectory();
        ToolsHelper.closeProgressDialog(false);
        if(directory != null) {
            page.showHistoryDialog(directory);
        }
    }

    @Background
    public void fetchHistory(String year, String date) {
        ToolsHelper.showProgressDialog(getContext(), false);
        ArrayList<ApiProduce> list = dataLoader.getHistory(year, date);
        ToolsHelper.closeProgressDialog(false);
        Intent intent = new Intent();
        intent.setClass(getContext(), BlankActivity_.class);
        intent.putExtra("historyPath", (preferences.userMode().get().equals(Constants.CUSTOMER)) ? new CustomerHistoryPath(list) : new MerchantHistoryPath(list));
        getContext().startActivity(intent);
    }
}
