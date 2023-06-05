package com.besteleben.irregularpenguin.data.repository;


import com.besteleben.irregularpenguin.data.objects.Vocabulary;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;


public class VocabularyRepositoryImplApi implements VocabularyRepository {
    /** http client for getting data from api */
    private HttpClient httpClient;
    /** GSON to work with the requested data */
    private Gson gson;
    /** Constructor to create the DAO */
    public VocabularyRepositoryImplApi() {
        httpClient = HttpClientBuilder.create().build();
        gson = new Gson();
    }

    /**
     * zum Suchen eines zufaelligen datensatzes aus der datenquelle
     *
     * @return gibt ein VocabularyDTO zurueck
     */
    @Override
    public Vocabulary getRandomVocabulary() {
        String apiUrl = "http://api.beste-leben.com/irregularverbs";
        String responsData = getDataFromApi(apiUrl);
        if(responsData != null){
            Type listType = new TypeToken<List<Vocabulary>>() {}.getType();
            List<Vocabulary> vocabulary = gson.fromJson(responsData,listType);
            return vocabulary.get(0);
        }
        return null;
    }

    public String getDataFromApi(String apiUrl) {
        String responseData = null;
        try{
            HttpGet httpGet = new HttpGet(apiUrl);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            if(entity != null) {
                responseData = EntityUtils.toString(entity);
            }
        } catch (IOException exception) {
            System.err.println("Api im moment nicht erreichbar");
        }
        return responseData;
    }
}
