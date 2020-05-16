package com.zyd.resources;

import com.zyd.models.CatalogItem;
import com.zyd.models.Movie;
import com.zyd.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webclientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        UserRating userRating = restTemplate.getForObject("http://movie-rating-service/rating/users/" + userId,
                                                          UserRating.class);

        return userRating.getUserRatings().stream()
                         .map(rating -> {
                             Movie movie = webclientBuilder.build()
                                                           .get()
                                                           .uri("http://movie-info-service/movies/" + rating.getMovieId())
                                                           .retrieve()
                                                           .bodyToMono(Movie.class)
                                                           .block();
                             return new CatalogItem(movie.getName(), "Desc", rating.getRating());
                         })
                         .collect(Collectors.toList());

    }
}
