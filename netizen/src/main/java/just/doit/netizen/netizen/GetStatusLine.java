package just.doit.netizen.netizen;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Hello world!
 *
 */
public class GetStatusLine 
{
    public static void main( String[] args ) throws URISyntaxException, ClientProtocolException, IOException
    {
    	CloseableHttpClient netizen = HttpClients.createDefault();
        URI url = new URIBuilder()
        			  .setScheme("http")
        			  .setHost("www.flipkart.com")
        			  .setPath("/tablets")
        			  .setParameter("otracker","hp_mod_electronics_vis_Tablets")
        			  .build();
        HttpGet getIt = new HttpGet(url);
        CloseableHttpResponse response = netizen.execute(getIt);
        System.out.println(response.getStatusLine().getProtocolVersion());
        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(response.getStatusLine().getReasonPhrase());
        System.out.println(response.getStatusLine().toString());
    }
}
