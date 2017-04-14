package ribboneureka.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    @HystrixCommand(fallbackMethod = "searchFallback")
    public SearchResponse getSearch(String query){
        try {
            if (query.equalsIgnoreCase("boom")) {
//                try { Thread.sleep(20000);} catch(InterruptedException ie){}
                throw new RuntimeException("Boom goes the dynamite");
            }

            final String url = new URIBuilder()
                    .setScheme("http").setHost(searchVip)
                    .setPath("/search/" + query)
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

    public SearchResponse searchFallback(String query) {
        return new SearchResponse("<Failed to execute search>");
    }
}