package localproject.elastic;

import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.UnknownHostException;

import static org.testng.Assert.assertEquals;

public class ApplicationTest {

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void givenJsonString_whenJavaObject_thenIndexDocument() {
        String jsonObject = "{\"age\":10,\"dateOfBirth\":1471466076564,"
                +"\"fullName\":\"John Doe\"}";
        Application app = new Application();
        Client client = null;
        try {
            client = app.createClient();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        IndexResponse response = client.prepareIndex("people", "Doe")
                .setSource(jsonObject, XContentType.JSON)
                .get();
        String id = response.getId();
        String index = response.getIndex();
        String type = response.getType();
        long version = response.getVersion();

        assertEquals(DocWriteResponse.Result.CREATED, response.getResult());
        assertEquals(0, version);
        assertEquals("people", index);
        assertEquals("Doe", type);
    }
}