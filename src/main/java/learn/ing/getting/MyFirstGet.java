package learn.ing.getting;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.codemahal.netizen.common.GetPostUtils;
import org.codemahal.netizen.common.ReadUtils;

public class MyFirstGet {
	static Logger LOG = Logger.getLogger(MyFirstGet.class);
	URI target;
	
	public MyFirstGet(String url) throws ClientProtocolException, IOException, URISyntaxException{
		target = new URI(url);
		LOG.info("Received Target -> "+target) ;
		long startedAt = System.currentTimeMillis();
		HttpResponse resp = GetPostUtils.getPage(target);
		LOG.info("Time Taken: "+(System.currentTimeMillis() - startedAt));
		String content = ReadUtils.getContentAsText(resp.getEntity().getContent());
		
	}
	
	public static void main(String[] args) throws URISyntaxException, ClientProtocolException, IOException {
		BasicConfigurator.configure();
		LOG.getRootLogger().setLevel(Level.INFO);
/*		MyFirstGet mfg = null;
		if(args.length==0){
			mfg = new MyFirstGet("http://www.redbus.in");
		}
		else mfg = new MyFirstGet(args[0]);
*/	
		String content = GetPostUtils.getContentAsText("http://175.41.130.244:80/");
		System.out.println(content);
		System.out.println("Content length:"+content.length());
		}

}
