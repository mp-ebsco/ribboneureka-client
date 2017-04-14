package ribboneureka.client.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResponse {
    private String response;

    @JsonCreator()
    public SearchResponse(@JsonProperty("response") String response) {
        this.response = response;

    }

    public String getResponse() {
        return response;
    }
}