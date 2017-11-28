package org.aalto.anton.omi.lufthansa.camel;

import java.io.File;
import java.io.StringReader;
import java.lang.management.MemoryUsage;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.StringTokenizer;

import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.camel.Body;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.apache.camel.builder.BuilderSupport;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.ehcache.processor.idempotent.EhcacheIdempotentRepository;
import org.apache.camel.component.metrics.messagehistory.MetricsMessageHistoryFactory;
import org.apache.camel.component.metrics.messagehistory.MetricsMessageHistoryService;
import org.apache.camel.component.metrics.routepolicy.MetricsRoutePolicyFactory;
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
import org.xml.sax.InputSource;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import org.aalto.anton.odf.airports.AirportResource;
import org.aalto.anton.odf.airports.ReferenceAirports;
import org.aalto.anton.odf.cities.CityResource;
import org.aalto.anton.odf.cities.ReferenceCities;
import org.aalto.anton.odf.countries.CountryResource;
import org.aalto.anton.odf.countries.ReferenceCountries;

import org.apache.camel.component.metrics.routepolicy.MetricsRegistryService; 
import org.apache.camel.component.metrics.routepolicy.MetricsRoutePolicyFactory; 
import io.swagger.api.ReferenceDataApi;

import org.apache.camel.spring.Main;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Hello world!
 *
 */
public class CamelApp 
{
	private static final Log log = LogFactory.getLog(CamelApp.class);
	static long memStart;
	static long memEnd;
	long timeStart;
	long timeEnd = 0;
//	private static CountriesBean countries = CamelApp.CountriesBean;
	private static XPathFactory xpathFactory = XPathFactory.newInstance();
	private static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	private ReferenceDataApi api;
    static {
	dbf.setNamespaceAware(false);
    }

    public static void main( String[] args )
    {
//    	PersistentCacheManager persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
//    		    .with(CacheManagerBuilder.persistence(getStoragePath() + File.separator + "myData")) 
//    		    .withCache("persistent-cache", CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
//    		        ResourcePoolsBuilder.newResourcePoolsBuilder()
//    		            .heap(10, EntryUnit.ENTRIES)
//    		            .disk(10, MemoryUnit.MB, true)) 
//    		        )
//    		    .build(true);
//
//    		persistentCacheManager.close();    	
//    	EhcacheIdempotentRepository repo = new EhcacheIdempotentRepository(persistentCacheManager, "idempotent-cache");
    	    //	EhcacheIdempotentRepository repo = new EhcacheIdempotentRepository(manager, "idempotent-cache");
    	CamelContext ctx = new DefaultCamelContext();
    	
        try {
			ctx.addRoutes(new RouteBuilder() {
				
			    public void configure() {
			    	onException(Exception.class).continued(false).to("log:ERROR");

					
			    	// MQTT
//			    	from("mqtt:paholainen1?host=tcp://127.0.0.1:11883&subscribeTopicNames=request/#")
//					 .bean(new ExtractURI())
//			    	.recipientList(simple("mqtt:paho2?host=tcp://127.0.0.1:11883&publishTopicName=info${header.TopicName}"));
			    	//O-MI / O-DF
			    	from("jetty:http://127.0.0.1:9090/Objects?matchOnUriPrefix=true")
			    	.bean(new ODFToLufthansa())
			    	// writeback address if applicable
			    	.recipientList(simple("${header.WriteAddress}"));
			    	// just API passthrough
					from("jetty:http://127.0.0.1:9090/api?matchOnUriPrefix=true")
			    	.bean(new ExtractURIandCallAPI());

			    	
			    }
   
     });
//        	MetricsMessageHistoryFactory mmhf = new MetricsMessageHistoryFactory();
            MetricsRoutePolicyFactory mrpf = new MetricsRoutePolicyFactory(); 
            ctx.addRoutePolicyFactory(mrpf);      	
//        	ctx.setMessageHistoryFactory(mmhf);
	
			
			ctx.start();
//	        MetricsRegistryService regSvc = (MetricsRegistryService) ctx.hasService(MetricsRegistryService.class); 
			// Maybe sleep a little here (60s)
			Thread.sleep(120000);
			// metrics
//	        if(regSvc != null) { 
//	            // Dump the statistics to the log in JSON format. 
//	            regSvc.setPrettyPrint(true); 
//	            System.err.println(regSvc.dumpStatisticsAsJson()); 
//
//	            // Do it again, retrieving the map of counters from the MetricRegistry object. 
//	            System.err.println("----------"); 
//	            MetricRegistry reg = regSvc.getMetricsRegistry(); 
//	            SortedMap<String, Counter> counters = reg.getCounters(); 
////	            System.err.println("Keys found {{}]", counters.keySet().size()); 
////	            for(String key : counters.keySet()) { 
////	                System.err.println("--- key [{}] - value [{}]", key, counters.get("key").getCount()); 
////	            }                 
//	        } else { 
//	            System.out.println("Couldn't find MetricsRegisteryService instance"); 
//	        } 			
			ctx.stop();
//			MetricsMessageHistoryService service = ctx.hasService(MetricsMessageHistoryService.class);
//			String json = service.dumpStatisticsAsJson();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private static String getStoragePath() {
		return "C://Temp//ehcache";
	}
    public static class ExtractURI implements Processor {
		@Override
		public void process(Exchange exchange) throws Exception {
	    	//String cPath = (String)exchange.getIn().getHeader("CamelHttpPath");
			String mainLevelQuery ="";
			String subLevelQuery ="";
			String response= "";

	    	String cPath =((String) exchange.getIn().getHeaders().get("CamelMQTTSubscribeTopic")).substring(7);
	    	//".json"
	    	exchange.getOut().setHeader("TopicName", cPath);
  			StringTokenizer cPtokens;
			cPtokens = new StringTokenizer(cPath, "/");
			mainLevelQuery = cPtokens.nextToken();
			if (cPtokens.countTokens() == 1) {
				while (cPtokens.hasMoreTokens()) {
					subLevelQuery = cPtokens.nextToken();
				}
			}
			if (mainLevelQuery.lastIndexOf("countries") != -1)
			{
				response= countryCall(subLevelQuery);
				
			}
			if (mainLevelQuery.lastIndexOf("cities") != -1)
			{
				response= cityCall(subLevelQuery);
			}
			if (mainLevelQuery.lastIndexOf("airports") != -1)
			{
				response= airportCall(subLevelQuery);
			}

			exchange.getOut().setBody(response);

		}
    	
    }

 //    public static class LufthansaToODF {
//        
//    }
    public static class ExtractURIandCallAPI implements Processor {
		@Override
		public void process(Exchange exchange) throws Exception {
			// TODO Auto-generated method stub
			String mainLevelQuery ="";
			String subLevelQuery ="";
			String response= "";
	    	String cPath = (String)exchange.getIn().getHeader("CamelHttpPath");
  			StringTokenizer cPtokens;
			cPtokens = new StringTokenizer(cPath, "/");
			mainLevelQuery = cPtokens.nextToken();
			if (cPtokens.countTokens() == 1) {
				while (cPtokens.hasMoreTokens()) {
					subLevelQuery = cPtokens.nextToken();
				}
			}
			if (mainLevelQuery.lastIndexOf("countries") != -1)
			{
				response= countryCall(subLevelQuery);
				
			}
			if (mainLevelQuery.lastIndexOf("cities") != -1)
			{
				response= cityCall(subLevelQuery);
			}
			if (mainLevelQuery.lastIndexOf("airports") != -1)
			{
				response= airportCall(subLevelQuery);
			}

			exchange.getOut().setBody(response);
		}
    	
    }
    public static class ODFToLufthansa implements Processor {

		@Override
	    public void process(Exchange exchange) throws Exception {
			String mainLevelQuery ="";
			String subLevelQuery ="";
	      	  io.swagger.api.ReferenceDataApi api = null;
	      	  boolean omiEnvelope = false;
  			StringTokenizer cPtokens;
        String payload = exchange.getIn().getBody(String.class);
    	String cPath = (String)exchange.getIn().getHeader("CamelHttpPath");
    	if (payload.length() == 0 && (cPath.equals("/") || cPath.equals("")))
    	{
    		replyObjects(exchange);
    	}
    	else
    	{
    		if(payload.length() > 0)
    		{
    		    Document doc = dbf.newDocumentBuilder().parse(new InputSource(new StringReader(payload)));
    	        // this should read all nodes and submit asynchronously to Lufthansa via Scatter-Gather.
//    	        NodeList objectNodes = (NodeList)xpathFactory.newXPath().evaluate("/Objects/Object/id",doc,XPathConstants.NODESET);
    	        if (xpathFactory.newXPath().evaluate("/omiEnvelope",doc) != null)
    	        {
    	        	omiEnvelope = true;
    	        }
    	        Node objectNode = (Node)xpathFactory.newXPath().evaluate("//Objects/Object",doc,XPathConstants.NODE);
//    		    System.err.println("DOCnum:"+objectNode.getNodeName());
    		    // assign for later to check validity
    		    mainLevelQuery = xpathFactory.newXPath().evaluate("./id",objectNode);
    		    Node on = (Node)xpathFactory.newXPath().evaluate("./Object",objectNode,XPathConstants.NODE);
    		    if (on != null)
    		    {
    		    subLevelQuery = xpathFactory.newXPath().evaluate("./id",on);
    		    }
    		}
				else {
					cPtokens = new StringTokenizer(cPath, "/");
					mainLevelQuery = cPtokens.nextToken();
					if (cPtokens.countTokens() == 1) {
						while (cPtokens.hasMoreTokens()) {
							subLevelQuery = cPtokens.nextToken();
						}
					}
				}
    		if (mainLevelQuery.lastIndexOf("countries") != -1 || mainLevelQuery.lastIndexOf("cities") != -1||mainLevelQuery.lastIndexOf("airports") != -1)
    		{
    			String response= "";
    			String rawODF ="";
    			String odf = "";
    			ObjectMapper jsonMapper = new ObjectMapper();
    			jsonMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    			jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    			JaxbAnnotationModule module = new JaxbAnnotationModule();
    			ObjectMapper xmlMapper = new XmlMapper();
    			xmlMapper.registerModule(module);
    			xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
    			if (mainLevelQuery.lastIndexOf("countries") != -1)
    			{
    				response= countryCall(subLevelQuery);
        			ReferenceCountries refCountries = jsonMapper.readValue(response, ReferenceCountries.class);
    				CountryResource cRes = refCountries.getCountryResource();
    				rawODF = xmlMapper.writeValueAsString(cRes);
    				
    			}
    			if (mainLevelQuery.lastIndexOf("cities") != -1)
    			{
    				response= cityCall(subLevelQuery);
        			ReferenceCities refCities = jsonMapper.readValue(response, ReferenceCities.class);
    				CityResource cRes = refCities.getCityResource();
    				rawODF = xmlMapper.writeValueAsString(cRes);
    			}
    			if (mainLevelQuery.lastIndexOf("airports") != -1)
    			{
    				response= airportCall(subLevelQuery);
        			ReferenceAirports refAirports = jsonMapper.readValue(response, ReferenceAirports.class);
    				AirportResource aRes = refAirports.getAirportResource();
    				rawODF = xmlMapper.writeValueAsString(aRes);
    			}
    	  
    			// rename objects as Jackson serialization does not allow same name element in same level 
    			odf  = "<Objects>" + rawODF.replaceAll("Object[0-9]", "Object").replaceAll("InfoItem[0-9]", "InfoItem") + "</Objects>";
    			if (omiEnvelope)
    			{
    				odf = "<omi:omiEnvelope xmlns:omi=\"omi.xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"omi.xsd omi.xsd\" version=\"1.0\" ttl=\"10\"><omi:response>"
        +"<omi:result msgformat=\"odf\"><omi:return returnCode=\"200\"></omi:return><omi:msg xmlns=\"odf.xsd\" xsi:schemaLocation=\"odf.xsd odf.xsd\">"
    			+odf+"</omi:msg></omi:result></omi:response></omi:omiEnvelope>";
    			}
    			exchange.getOut().setBody(odf);
    		}
    		// here is error situation not found query / Object
    		else
    		{
    		exchange.getOut().setBody("<omi:omiEnvelope xmlns:omi=\"omi.xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"omi.xsd omi.xsd\" version=\"1.0\" ttl=\"10\"><omi:response>"
        +"<omi:result msgformat=\"odf\"><omi:return returnCode=\"404\"></omi:return>"+"</omi:msg></omi:result></omi:response></omi:omiEnvelope>");
    		}
    	}
	   }

		private void replyObjects(Exchange exchange) {
			exchange.getOut().setBody("<Objects xmlns=\"http://www.opengroup.org/xsd/odf/1.0/\""
	+ "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
	+ "xsi:schemaLocation=\"http://www.opengroup.org/xsd/odf/1.0/odf.xsd\">"
+ "<Objects><Object type=\"https://iata.org/CountryResource\"><id>countries</id></Object>"
+"<Object type=\"https://iata.org/CityResource\"><id>cities</id></Object><Object type=\"https://iata.org/AirportResource\"><id>airports</id></Object></Objects>");
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
    public static class ACK {
    	private static XPathFactory xpathFactory = XPathFactory.newInstance();
    	public String justACK(@Body Document body) throws javax.xml.xpath.XPathExpressionException {
          System.out.println("Received: " + body);
          String reply ="<?xml version=\"1.0\" encoding=\"UTF-8\"?><omiEnvelope version=\"0.2\" ttl=\"0\"><response><result><return returnCode=\"200\"></return></result></response></omiEnvelope>";
          
  		return reply;
    	}        
    	
    	
    }
	public static String countryCall(String subLevelQuery) {
    	  io.swagger.api.ReferenceDataApi api = null;
        JacksonJsonProvider provider = new JacksonJsonProvider();
        List providers = new ArrayList();
        providers.add(provider);
        api = JAXRSClientFactory.create("https://api.lufthansa.com/v1", ReferenceDataApi.class, providers);
        org.apache.cxf.jaxrs.client.Client client = WebClient.client(api).header("Authorization","Bearer 4zbhferyr6hw2qg25ruukebv");
     ClientConfiguration config = WebClient.getConfig(client);
      String countryCode = subLevelQuery;
      String accept = null;
      String lang = null;
      String limit = null;
      String offset = null;
      return api.countries(countryCode, accept, lang, limit, offset);
		
	}
    
	private static String airportCall(String subLevelQuery) {
  	  io.swagger.api.ReferenceDataApi api = null;
      JacksonJsonProvider provider = new JacksonJsonProvider();
      List providers = new ArrayList();
      providers.add(provider);
      api = JAXRSClientFactory.create("https://api.lufthansa.com/v1", ReferenceDataApi.class, providers);
      org.apache.cxf.jaxrs.client.Client client = WebClient.client(api).header("Authorization","Bearer 4zbhferyr6hw2qg25ruukebv");
   ClientConfiguration config = WebClient.getConfig(client);
      String airportCode = subLevelQuery;
      String accept = null;
      String lang = null;
      String limit = null;
      String offset = null;
      Boolean lhoperated = null;
return api.airports(airportCode, accept, lang, limit, offset, lhoperated);
	}

	private static String cityCall(String subLevelQuery) {
	  	  io.swagger.api.ReferenceDataApi api = null;
	      JacksonJsonProvider provider = new JacksonJsonProvider();
	      List providers = new ArrayList();
	      providers.add(provider);
	      api = JAXRSClientFactory.create("https://api.lufthansa.com/v1", ReferenceDataApi.class, providers);
	      org.apache.cxf.jaxrs.client.Client client = WebClient.client(api).header("Authorization","Bearer 4zbhferyr6hw2qg25ruukebv");
        String cityCode = subLevelQuery;
        String accept = null;
        String lang = null;
        String limit = null;
        String offset = null;
	return  api.cities(cityCode, accept, lang, limit, offset);
	      }
    
}
