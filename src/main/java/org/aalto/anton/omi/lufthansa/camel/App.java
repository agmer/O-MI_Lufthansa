package org.aalto.anton.omi.lufthansa.camel;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.core.MediaType;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.camel.Body;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.ehcache.processor.idempotent.EhcacheIdempotentRepository;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.ehcache.CacheManager;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.spi.service.ServiceCreationConfiguration;
import org.ehcache.xml.XmlConfiguration;
//import org.apache.cxf.rs.security.oauth2.client.Consumer;
//import org.apache.cxf.rs.security.oauth2.grants.code.AuthorizationCodeGrant;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import io.swagger.api.ReferenceDataApi;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//    	CacheManager manager = CacheManagerBuilder.newCacheManager(new XmlConfiguration("ehcache.xml"));
    	PersistentCacheManager persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
    		    .with(CacheManagerBuilder.persistence(getStoragePath() + File.separator + "myData")) 
    		    .withCache("persistent-cache", CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
    		        ResourcePoolsBuilder.newResourcePoolsBuilder()
    		            .heap(10, EntryUnit.ENTRIES)
    		            .disk(10, MemoryUnit.MB, true)) 
    		        )
    		    .build(true);

    		persistentCacheManager.close();    	
    	EhcacheIdempotentRepository repo = new EhcacheIdempotentRepository(persistentCacheManager, "idempotent-cache");
    	    //	EhcacheIdempotentRepository repo = new EhcacheIdempotentRepository(manager, "idempotent-cache");
    	ReferenceDataApi restClient;
    	CamelContext ctx = new DefaultCamelContext();
      System.out.println( "Hello World!" );
        try {
			ctx.addRoutes(new RouteBuilder() {
				 
			    public void configure() {
			    	from("mqtt:paho1?host=tcp://127.0.0.1:11883&subscribeTopicNames=request/#").bean(new StoreTopicName())
//			    	.to("cxf:bean:Lufthansa")
			    	.recipientList(simple("mqtt:paho2?host=tcp://127.0.0.1:11883&publishTopicName=info/${header.TopicName}"));
//			    	from("jetty:http://127.0.0.1:8080/O-MI")
			    	from("jetty:http://127.0.0.1:8088/mockVaskiWSSoap")
			    	.process(new Processor() {
			    	    public void process(Exchange exchange) throws Exception {
//			    	    	exchange.setPattern(ExchangePattern.InOut);
//			    	        String payload = exchange.getIn().getBody(String.class);
			    	        // do something with the payload and/or exchange here
//			    	    	Map<String,Object> headers = exchange.getIn().getHeaders();
			    	    	Map<String, Object> props = exchange.getProperties();
		    	    		System.out.println("XXXProps:");
			    	    	for (String s : props.keySet())
			    	    	{
			    	    		System.out.println(s+":"+props.get(s));
			    	    	}
//			    	       exchange.getOut().setHeader("", props.get);;
			    	    	Map<String,Object> headers = exchange.getIn().getHeaders();
		    	    		System.out.println("YYYHeaders:");
			    	    	for (String s : headers.keySet())
			    	    	{
			    	    		System.out.println(s+":"+headers.get(s));
			    	    	}
				    	   }
				    	})
			    	.to("mqtt:OMIpub?host=tcp://127.0.0.1:11883&publishTopicName=forward/this")
			    	
			    	.bean(new ACK());
//			    	.threads(20)
//			    	.removeHeaders("CamelHttp*")
//			    	.to("jhc:http://127.0.0.1:9090/O-MI");
//			    	.bean(new StoreWriteAddressAndACK())
//			    	.bean(new ODFtoLufthansa())
//			    	.to("cxf:bean:Lufthansa").to("${header.WriteAddress}");
			    	from("mqtt:OMIsub?host=tcp://127.0.0.1:11883&subscribeTopicNames=forward/this").to("seda:quer");
			    	from("seda:quer")
			    	.process(new Processor() {
			    	    public void process(Exchange exchange) throws Exception {
//			    	    	exchange.setPattern(ExchangePattern.InOut);
//			    	        String payload = exchange.getIn().getBody(String.class);
			    	        // do something with the payload and/or exchange here
//			    	    	Map<String,Object> headers = exchange.getIn().getHeaders();
			    	    	Map<String, Object> props = exchange.getProperties();
		    	    		System.err.println("XXXProps:");
			    	    	for (String s : props.keySet())
			    	    	{
			    	    		System.out.println(s+":"+props.get(s));
			    	    	}
//			    	       exchange.getOut().setHeader("", props.get);;
			    	    	Map<String,Object> headers = exchange.getIn().getHeaders();
		    	    		System.err.println("YYYHeaders:");
			    	    	for (String s : headers.keySet())
			    	    	{
			    	    		System.out.println(s+":"+headers.get(s));
			    	    	}
				    	   }
				    	})
			    	.removeHeaders("CamelHttp*")
			    	//.dynamicRouter(method(DynamicRouter.class, "slip"))
			    	.to("jetty:http://127.0.0.1:9090/O-MI");
			    	
//			       from("file://C:/Stuff/camel/in/").to("file://C:/Stuff/camel/out/");
//			    	from("file://C:/Stuff/camel/in/?noop=true").transform(body().convertToString()).to("mqtt:cheese?host=tcp://127.0.0.1:11883&publishTopicName=t/foul");
//			       topic").transform(body().convertToString()).to("mock:result")
//			       from("mqtt:cheese?host=tcp://127.0.0.1:11883&subscribeTopicNames=test/file").to("file:///C:/Stuff/camel/out/our.xml");
//			       from("mqtt:cheese?host=tcp://127.0.0.1:11883&subscribeTopicNames=test/file").transform(body().convertToString()).bean(new SomeBean()).to("mqtt:cheese?host=tcp://127.0.0.1:11883&publishTopicName=test/fine");
			    	from("mqtt:cheese1?host=tcp://127.0.0.1:11883&subscribeTopicNames=test/foul")
			    	.process(new Processor() {
			    	    public void process(Exchange exchange) throws Exception {
//			    	    	exchange.setPattern(ExchangePattern.InOut);
//			    	        String payload = exchange.getIn().getBody(String.class);
			    	        // do something with the payload and/or exchange here
//			    	    	Map<String,Object> headers = exchange.getIn().getHeaders();
			    	    	Map<String, Object> props = exchange.getProperties();
		    	    		System.out.println("XXXProps:");
			    	    	for (String s : props.keySet())
			    	    	{
			    	    		System.out.println(s+":"+props.get(s));
			    	    	}
//			    	       exchange.getOut().setHeader("", props.get);;
			    	    	Map<String,Object> headers = exchange.getIn().getHeaders();
		    	    		System.out.println("YYYHeaders:");
			    	    	for (String s : headers.keySet())
			    	    	{
			    	    		System.out.println(s+":"+headers.get(s));
			    	    	}
			    	    	String x =((String) headers.get("CamelMQTTSubscribeTopic")).substring(5);
			    	    	exchange.getOut().setHeader("templateName", x);
//			    	    	exchange.getOut().setBody(x);
			    	    	
			    	   }
			    	})
//			    	.bean(new SomeBean())
//			    	.to("jaxrs://https://api.lufthansa.com/v1/references/?synchronous=true&amp;httpClientAPI=true")
//			    	.beanRef("restClient", "countries")
//			    	.to("cxf:bean:rsClient")
			    	.recipientList(simple(("log:${header.templateName}?level=ERROR")))
			    	.recipientList(simple("mqtt:cheese?host=tcp://127.0.0.1:11883&publishTopicName=test/feel/${header.templateName},mqtt:cheese?host=tcp://127.0.0.1:11883&publishTopicName=test/peel/${header.templateName}"))
//			    	.to("mqtt:cheese2?host=tcp://127.0.0.1:11883&publishTopicName=test/feel")
			    	.process(new Processor() {
			    	    public void process(Exchange exchange) throws Exception {
//			    	        String payload = exchange.getIn().getBody(String.class);
			    	        // do something with the payload and/or exchange here
			    	    	Map<String,Object> headers = exchange.getIn().getHeaders();
			    	    	for (String s : headers.keySet())
			    	    	{
			    	    		System.out.println(s+":"+headers.get(s));
			    	    	}
			    	   }
			    	});
			    }
   
     });
			ctx.start();
			// Maybe sleep a little here
			Thread.sleep(4000);
			ctx.stop();			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private static String getStoragePath() {
		// TODO Auto-generated method stub
		return "C://Temp//ehcache";
	}
	public static class SomeBean {
        public void someMethod(String header) {
            System.out.println("Received: " + header);
      	  io.swagger.api.ReferenceDataApi api = null;
  	  String address = "https://localhost:8080/oauth2/token";
  	  WebClient wc = WebClient.create(address);
  	  wc.type(MediaType.APPLICATION_FORM_URLENCODED).accept(MediaType.APPLICATION_JSON);
//	  Consumer consumer = null;
//	  AuthorizationCodeGrant codeGrant = null;
        JacksonJsonProvider provider = new JacksonJsonProvider();
        List providers = new ArrayList();
        providers.add(provider);
        api = JAXRSClientFactory.create("https://api.lufthansa.com/v1", ReferenceDataApi.class, providers);
        org.apache.cxf.jaxrs.client.Client client = WebClient.client(api);
      ClientConfiguration config = WebClient.getConfig(client);
      System.err.println("SetupDONE");
      String countryCode = "FI";
      String accept = null;
      String lang = null;
      String limit = null;
      String offset = null;
      try
      {
  String response = api.countries(countryCode, accept, lang, limit, offset);
  System.out.println("RESPONSE"+response);

  	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//        public void someMethod(String body) {
//            System.out.println("Received: " + body);
        }
    }
    public static class DynamicRouter {
    	/**
    	 * Use this method to compute dynamic where we should route next.
    	 *
    	 * @param body the message body
    	 * @return endpoints to go, or <tt>null</tt> to indicate the end
    	 */
    	public String slip(String body) {
    		// process body. If timed create it. 
    		// if immediate -> return seda
    		int invoked = 1;

    	    if (invoked == 1) {
    	        return "mock:a";
    	    } else if (invoked == 2) {
    	        return "mock:b,mock:c";
    	    } else if (invoked == 3) {
    	        return "direct:foo";
    	    } else if (invoked == 4) {
    	        return "mock:result";
    	    }

    	    // no more so return null
    	    return null;
    	}    	
    }
    public static class StoreTopicName {
    	
    }
    public static class ACK {
    	private static XPathFactory xpathFactory = XPathFactory.newInstance();
    	public String justACK(@Body Document body) throws javax.xml.xpath.XPathExpressionException {
          System.out.println("Received: " + body);
          String reply ="<?xml version=\"1.0\" encoding=\"UTF-8\"?><omiEnvelope version=\"0.2\" ttl=\"0\"><response><result><return returnCode=\"200\"></return></result></response></omiEnvelope>";
          
  		return reply;
    	}        
    	
    	
    }
    
}
