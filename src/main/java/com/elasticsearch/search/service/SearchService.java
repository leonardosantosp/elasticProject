package com.elasticsearch.search.service;

import co.elastic.clients.elasticsearch.core.search.Hit;
import com.elasticsearch.search.api.model.Result;
import com.elasticsearch.search.api.model.Results;
import com.elasticsearch.search.domain.EsClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    private final EsClient esClient;

    public SearchService(EsClient esClient) {
        this.esClient = esClient;
    }

    public Results search(String query, Integer page) {
        var searchResponse = esClient.search(query, page);
        Results results = new Results();

        List<Result> resultList = new ArrayList<>();
        List<Hit<Result>> hits = searchResponse.hits().hits();

        for (Hit<Result> hit : hits) {
            Result result = hit.source();
            resultList.add(result);
        }

        results.setResultsList(resultList);
        return results;
    }



}
