package com.jarvislin.producepricechecker;

import com.jarvislin.producepricechecker.model.ApiProduce;

import org.androidannotations.annotations.rest.Accept;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;

/**
 * Created by Jarvis Lin on 2015/8/3.
 */
@Rest(converters = {FormHttpMessageConverter.class, GsonHttpMessageConverter.class, StringHttpMessageConverter.class})
@Accept("application/json")
public interface ApiClient extends RestClientErrorHandling {
    @Post("http://produce.jarvislin.com/provider")
    ArrayList<ApiProduce> getData(MultiValueMap<String, String> map);

    @Get("http://m.coa.gov.tw/OpenData/FarmTransData.aspx?$top=10000&$skip=0&StartDate={startDate}&EndDate={endDate}&Crop={produceName}&Market={marketName}")
    String getOpenData(String startDate, String endDate, String produceName, String marketName);
}