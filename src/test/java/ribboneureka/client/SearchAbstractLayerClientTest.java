package ribboneureka.client;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import ribboneureka.client.dao.SearchDaoImpl;
import ribboneureka.client.dto.SearchResponse;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/*************
 * See http://stackoverflow.com/questions/39587317/difference-between-ribbonclient-and-loadbalanced
 *************/
//@RunWith(SpringRunner.class)
////@SpringBootTest(classes = {ClientConfig.class, SearchConfig.class})
//@EnableAutoConfiguration
public class SearchAbstractLayerClientTest {

    @Autowired
    SearchDaoImpl searchDao;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

//    @Test
    public void testGetSearch() throws InterruptedException {
        
        // Given
        stubFor(get(urlEqualTo("/")).willReturn(aResponse().withStatus(200)));  //ribbon will request this url to make sure the service is up.
        
        stubFor(get(urlEqualTo("/search?q=bed")).willReturn(aResponse().withStatus(200)   //request we want to mock
                .withHeader("Content-Type", "application/json").withBody("{\"title\":\"bed restraint\"}")));

        // When
        SearchResponse result = searchDao.getSearch("bed");

        // Then
        Assert.assertThat(result.getResponse(), Is.is("bed restraint"));
    }
}
