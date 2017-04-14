package ribboneureka.client.dao;

import ribboneureka.client.dto.SearchResponse;

public interface SearchDao {
    SearchResponse getSearch(String query);
}
