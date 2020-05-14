package com.project.panahon.news_service.service.news;

import java.util.Map;

/**
 * Interface for the NewsService.
 *
 * @author christian
 */
public interface NewsService {
    Map<String, Object> newsAPI(String country);
}
