package ribboneureka.client;

import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

//@Profile("!dev")
@ConditionalOnProperty(name = "searchclient.useStubs", matchIfMissing = true, havingValue = "false")
@Primary
@Component
public class SearchDaoImpl implements SearchDao {
    
    private static Logger logger = LoggerFactory.getLogger(SearchDaoImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${searchservice.vip}")
    private String searchVip;

    @Override
    public SearchResponse getSearch(String query){
        try {
            final String url = new URIBuilder()
                    .setScheme("http").setHost(searchVip)
                    .setPath("/" + query)
                    .build().toString();
            
            logger.info("url: {}", url);
            
            ResponseEntity<SearchResponse> response = restTemplate.getForEntity(
                    url, SearchResponse.class);

            return response.getBody();
            
        } catch (URISyntaxException e) {
            logger.error("Could not parse url", e);
            throw new RuntimeException(e);
        }
    }
}