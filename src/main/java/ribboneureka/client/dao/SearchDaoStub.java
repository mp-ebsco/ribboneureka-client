package ribboneureka.client.dao;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ribboneureka.client.dto.SearchResponse;

@ConditionalOnProperty(name = "searchclient.useStubs", havingValue = "true")
@Component
public class SearchDaoStub implements SearchDao {
    
    @Override
    public SearchResponse getSearch(String query) {
        return new  SearchResponse(String.join("-","stub", query, "result"));
    }
}