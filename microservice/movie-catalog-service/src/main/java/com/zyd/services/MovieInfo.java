package com.zyd.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zyd.models.CatalogItem;
import com.zyd.models.Movie;
import com.zyd.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MovieInfo {

    @Autowired
    private WebClient.Builder webclientBuilder;

    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = webclientBuilder.build()
                                      .get()
                                      .uri("http://movie-info-service/movies/" + rating.getMovieId())
                                      .retrieve()
                                      .bodyToMono(Movie.class)
                                      .block();
        return new CatalogItem(movie.getTitle(), "Desc", rating.getRating());
    }

    public CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("No movie found", "", rating.getRating());
    }
}
