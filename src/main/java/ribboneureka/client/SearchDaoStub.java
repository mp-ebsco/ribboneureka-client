package ribboneureka.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

//@Profile("dev")
@ConditionalOnProperty(name = "searchclient.useStubs", havingValue = "true")
@Component
public class SearchDaoStub implements SearchDao {
    
    private static Logger logger = LoggerFactory.getLogger(SearchDaoStub.class);

    @Override
    public SearchResponse getSearch(String query) {
        return new  SearchResponse(String.join("-","stub", query, "result"));
    }
}