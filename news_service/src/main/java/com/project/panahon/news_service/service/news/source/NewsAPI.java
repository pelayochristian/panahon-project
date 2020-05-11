package com.project.panahon.news_service.service.news.source;

import com.project.panahon.news_service.service.news.factory.News;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * This abstract class extends {@link News}. This <br>
 * is use to parse the incoming response came from the <br>
 * source.
 *
 * @author christian
 * @since 2020-05-11
 */
public class NewsAPI extends News {

    /**
     * Override method form {@link News} abstract class.
     *
     * @param newsResponse {@link ResponseEntity}
     * @return {@link Map}
     */
    @Override
    public Map<String, Object> parseNewsData(ResponseEntity<Object> newsResponse) {
        return null;
    }
}
