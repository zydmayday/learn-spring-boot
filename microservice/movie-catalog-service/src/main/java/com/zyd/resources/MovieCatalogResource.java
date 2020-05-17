package com.zyd.resources;

import com.zyd.models.CatalogItem;
import com.zyd.models.UserRating;
import com.zyd.services.MovieInfo;
import com.zyd.services.UserRatingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private WebClient.Builder webclientBuilder;

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    UserRatingInfo userRatingInfo;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        UserRating userRating = userRatingInfo.getUserRatings(userId);

        return userRating.getUserRatings().stream()
                         .map(rating -> movieInfo.getCatalogItem(rating))
                         .collect(Collectors.toList());

    }

}
