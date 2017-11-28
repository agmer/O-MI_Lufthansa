package org.aalto.anton.odf.countries;

import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.rs.security.oauth2.client.BearerAuthSupplier;
import org.apache.cxf.rs.security.oauth2.client.Consumer;
import org.apache.cxf.rs.security.oauth2.client.OAuthClientUtils;
import org.apache.cxf.rs.security.oauth2.common.ClientAccessToken;
import org.apache.cxf.rs.security.oauth2.grants.code.AuthorizationCodeGrant;
import org.apache.cxf.rs.security.oauth2.tokens.bearer.BearerAccessToken;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import io.swagger.api.ReferenceDataApi;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.rs.security.oauth2.client.Consumer;
import org.apache.cxf.rs.security.oauth2.grants.code.AuthorizationCodeGrant;
//
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import io.swagger.api.ReferenceDataApi;

public class ARun {
	private static org.apache.cxf.rs.security.oauth2.client.BearerAuthSupplier bearerAuthSupplier;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//        countriesTest();
		try {
			loadJSONtoODFfile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	  public static void loadJSONtoODFfile() throws IOException {
		  long s,e,memStart,memEnd = 0;
	    	memStart = Runtime.getRuntime().freeMemory();
		  	String basePath = "C:\\cygwin64\\home\\panheant\\th_";
		  	String x ="countries";
//		  	String x ="countries_FI";
		  	//".json"
		  	String fileName =x.replace("/", "_");
		  	List<String> lines = Files.readAllLines(Paths.get(basePath+fileName+".json"),
						Charset.forName("UTF-8"));
				// O-DF
				String json = lines.toString();
//				System.err.println(json);
				s = System.currentTimeMillis();
				ObjectMapper jsonMapper = new ObjectMapper();
				jsonMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
				jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				ReferenceCountries refCountries = (ReferenceCountries) jsonMapper.readValue(new File(basePath+fileName+".json"), ReferenceCountries.class);
//				ReferenceCountries refCountries = (ReferenceCountries) jsonMapper.readValue(json, ReferenceCountries.class);
//				CountryResource refCountries = (CountryResource) jsonMapper.readValue(new File(basePath+fileName+".json"), CountryResource.class);
				//assertEquals("FI","FI",country.getCountryCode());
				//assertNotEquals("FI","FI",country.getCountryCode());
				//xmlMapper.writeValue(new File("/tmp/stuff.json"), new ReferenceCountries());        
				//System.out.println(response);
				// JSON input: {"name":"refCountries","bar":{"id":42}}

				ObjectMapper xmlMapper2 = new XmlMapper();
//				String xml2 = xmlMapper2.writeValueAsString(refCountries);
//				CountryResource countryResource =  refCountries.getCountryResource();
//				countryResource.setMeta(null);
				JaxbAnnotationModule module = new JaxbAnnotationModule();
				// configure as necessary
				ObjectMapper xmlMapper = new XmlMapper();
				xmlMapper.registerModule(module);
				xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
				CountryResource cRes = refCountries.getCountryResource();
//				cRes.setMeta(null);
				System.out.println(xmlMapper.writeValueAsString(cRes));
//				Country country = refCountries.getCountryResource().getCountries().getCountry();
//				System.out.println(xmlMapper.writeValueAsString(country));
//				Names names= refCountries.getCountryResource().getCountries().getCountry().getNames();
//				System.out.println(xmlMapper.writeValueAsString(names));
//				System.out.println(xmlMapper.writeValueAsString(countryResource));
		    	memEnd = Runtime.getRuntime().freeMemory();
				
//				System.out.println(e-s);
//				System.out.println(memEnd-memStart);
			  
		  }	
  public static void countriesTest() {
	  long s,e,memStart,memEnd = 0;
  	memStart = Runtime.getRuntime().freeMemory();
	s = System.currentTimeMillis();

	  io.swagger.api.ReferenceDataApi api = null;
//	    WebClient accessTokenService;
//	    private String authorizationServiceURI;
//	    private Consumer consumer3;
//	    private AuthorizationCodeGrant codeGrant3;
//	    private WebClient rs;
	  String address = "https://localhost:8080/oauth2/token";
	  WebClient wc = WebClient.create(address);
	  wc.type(MediaType.APPLICATION_FORM_URLENCODED).accept(MediaType.APPLICATION_JSON);
//    ClientAccessToken accessToken = OAuthClientUtils.getAccessToken(
	  Consumer consumer = null;
			  AuthorizationCodeGrant codeGrant = null;
		//bearerAuthSupplier.	  	   
//      ClientAccessToken accessToken = OAuthClientUtils.getAccessToken(wc,consumer,codeGrant);  
      JacksonJsonProvider provider = new JacksonJsonProvider();
      List providers = new ArrayList();
      providers.add(provider);
      api = JAXRSClientFactory.create("https://api.lufthansa.com/v1", ReferenceDataApi.class, providers);
      org.apache.cxf.jaxrs.client.Client client = WebClient.client(api);
//    client.header(HttpHeaders.AUTHORIZATION, 
    ClientConfiguration config = WebClient.getConfig(client);
//   config.getHttpConduit().setAuthorization(bearerAuthSupplier);
//    System.err.println("TOKEN:"+accessToken.toString());
    System.err.println("SetupDONE");
    String countryCode = "FI";
//    String countryCode = "";
    String accept = null;
    String lang = null;
    String limit = null;
    String offset = null;
    try
    {
String response = api.countries(countryCode, accept, lang, limit, offset);
System.out.println("RESPONSE:"+response.length());
ObjectMapper jsonMapper = new ObjectMapper();
ReferenceCountries refCountries = jsonMapper.readValue(response, ReferenceCountries.class);
CountryResource countryResource =  refCountries.getCountryResource();
//Country country = refCountries.getCountryResource().getCountries().getCountry();
//Names names = country.getNames();
//assertEquals("FI","FI",country.getCountryCode());
//assertNotEquals("FI","FI",country.getCountryCode());
//xmlMapper.writeValue(new File("/tmp/stuff.json"), new ReferenceCountries());        
//System.out.println(response);
// JSON input: {"name":"refCountries","bar":{"id":42}}

ObjectMapper xmlMapper = new XmlMapper();
//String xml = xmlMapper.writeValueAsString(new Country());
//String xml = xmlMapper.writeValueAsString(country);
//    System.out.println(xmlMapper.writeValueAsString(refCountries));
System.out.println(xmlMapper.writeValueAsString(countryResource));
//System.out.println(xml);
//String xml = xmlMapper.writeValueAsString(new Country());
//System.out.println(xmlMapper.writeValueAsString(refCountries));
//ObjectMapper xmlMapper2 = new XmlMapper();
//String xml2 = xmlMapper2.writeValueAsString(refCountries);
//System.err.println(xml2);
//System.err.println(xml2.length());

    //		ReferenceDataApi api;
//	    WebClient accessTokenService;
//	    String authorizationServiceURI;
//	    Consumer consumer3;
//	    AuthorizationCodeGrant codeGrant3;
//	    WebClient rs;
//        JacksonJsonProvider provider = new JacksonJsonProvider();
//        List providers = new ArrayList();
//        providers.add(provider);
//        api = JAXRSClientFactory.create("https://api.lufthansa.com/v1", ReferenceDataApi.class, providers);
//        
//        org.apache.cxf.jaxrs.client.Client client = WebClient.client(api);
//        ClientConfiguration config = WebClient.getConfig(client);
//        System.out.println("SetupDONE");
//  String countryCode = "FI";
//  String accept = null;
//  String lang = null;
//  String limit = null;
//  String offset = null;
//String response = api.countries(countryCode, accept, lang, limit, offset);
//System.out.println("RESPONSE"+response);
//	  try {
//	  ObjectMapper xmlMap = new XmlMapper();
//	  Simple value = xmlMap.readValue("<Simple><x>7</x><y>8</y></Simple>", Simple.class);
//	  ObjectMapper xmlMapper = new XmlMapper();
//		String xml = xmlMapper.writeValueAsString(value);
e = System.currentTimeMillis();
memEnd = Runtime.getRuntime().freeMemory();

System.out.println(e-s);
System.out.println(memEnd-memStart);
	} catch (Exception ex) {
		// TODO Auto-generated catch block
		ex.printStackTrace();
	}
  }
}
