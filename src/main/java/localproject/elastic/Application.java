package localproject.elastic;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Application {

    public Client createClient() throws UnknownHostException {
        Client client = new PreBuiltTransportClient(
                Settings.builder().put("client.transport.sniff", true)
                                .put("cluster.name", "elasticsearch")
                                .build())
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        return client;
    }
}
