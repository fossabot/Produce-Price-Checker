package com.jarvislin.producepricechecker.page.History;

import com.jarvislin.producepricechecker.R;
import com.jarvislin.producepricechecker.database.Produce;
import com.jarvislin.producepricechecker.model.ApiProduce;
import com.jarvislin.producepricechecker.path.PathLayout;

import java.util.ArrayList;

import flow.path.Path;

/**
 * Created by jarvis on 15/11/5.
 */
@PathLayout(R.layout.customer_history)
public class CustomerHistoryPath extends HistoryPath {

    public CustomerHistoryPath(ArrayList<ApiProduce> list) {
        super(list);
    }
}
