package ribboneureka.client.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ribboneureka.client.dto.SearchResponse;

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