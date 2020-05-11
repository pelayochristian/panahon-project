package com.project.panahon.news_service.service.news.factory;

import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * This abstract class will be the parent of all <br>
 * related news provider instance.
 *
 * @author christian
 * @since 2020-05-11
 */
public abstract class News {
    public abstract Map<String, Object> parseNewsData(ResponseEntity<Object> newsResponse);
}
