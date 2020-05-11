package com.project.panahon.news_service.service.news;

import com.project.panahon.news_service.cache.RedisCacheManager;
import com.project.panahon.news_service.service.news.factory.News;
import com.project.panahon.news_service.service.news.factory.NewsFactory;
import com.project.panahon.news_service.service.news.factory.NewsSourceType;
import com.project.panahon.news_service.service.news.source.NewsAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Service class that implements method form {@link NewsService} <br>
 * interface. This will handle the scraping of data from different <br>
 * sources.
 *
 * @author christian
 * @since 2020-05-11
 */
@Service
public class NewsServiceImplementation implements NewsService {

    RedisCacheManager redisCacheManager;

    private int newsAPiCounter = 1;

    @Autowired
    public NewsServiceImplementation(RedisCacheManager redisCacheManager) {
        this.redisCacheManager = redisCacheManager;
    }

    /**
     * Service function use for scraping data form <tt>https://newsapi.org/</tt>.
     *
     * @param country {@link String}
     * @return {@link Map}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> newsAPI(String country) {
        // Get the cache
        Map<String, Object> cache = redisCacheManager.obtainCache(country, NewsAPI.class.getSimpleName(), Map.class);

        // Check if exist
        if (cache != null) {
            return cache;
        } else { // Else call the API to fetch data.
            return callNewsAPI(country, redisCacheManager);
        }
    }

    public Map<String, Object> callNewsAPI(String country, RedisCacheManager cacheManager) {
        newsAPiCounter++;

        // Response holder
        Map<String, Object> jsonReponse = new HashMap<>();

        // Construct URL for retrieving from API

        // Use Rest-Template to get the response
        RestTemplate restTemplate = new RestTemplate();

        try {

            // Get the Response entity via rest-template.
            ResponseEntity<Object> responseEntity = restTemplate.getForEntity("", Object.class);

            // Get the instance for NEWS_API.
            News news = NewsFactory.getNewsDataParser(NewsSourceType.NEWS_API);

            // Get the filtered data.
            jsonReponse = news.parseNewsData(responseEntity);

            // Add to cache
            cacheManager.putCache(country, NewsAPI.class.getSimpleName(), jsonReponse);

            // Set expiration
            cacheManager.obtainExpire(country);
        } catch (Exception e) {
            switch (newsAPiCounter) {
                case 2:
                    break;
                default:
                    jsonReponse.put(NewsAPI.class.getSimpleName(), e.toString());
                    break;
            }
        }
        return jsonReponse;
    }
}
