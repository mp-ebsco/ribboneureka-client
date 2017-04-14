package ribboneureka.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "search")
public class SearchController {

    @Autowired
    private SearchDao searchDao;

    @RequestMapping(method = RequestMethod.GET, path = "/{query}")
    public SearchResponse home(
            @PathVariable(name = "query", required = true) String query) {
        SearchResponse response = searchDao.getSearch(query);
        return response;
    }
}
