package org.aalto.anton.omi.lufthansa;

import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.WebClient;
//import org.apache.cxf.rs.security.oauth2.client.BearerAuthSupplier;
//import org.apache.cxf.rs.security.oauth2.client.Consumer;
//import org.apache.cxf.rs.security.oauth2.client.OAuthClientUtils;
//import org.apache.cxf.rs.security.oauth2.common.ClientAccessToken;
//import org.apache.cxf.rs.security.oauth2.grants.code.AuthorizationCodeGrant;
//import org.apache.cxf.rs.security.oauth2.tokens.bearer.BearerAccessToken;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import io.swagger.api.ReferenceDataApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
//import org.apache.cxf.rs.security.oauth2.client.Consumer;
//import org.apache.cxf.rs.security.oauth2.grants.code.AuthorizationCodeGrant;
//
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import io.swagger.api.ReferenceDataApi;

public class ARun {
//	private static org.apache.cxf.rs.security.oauth2.client.BearerAuthSupplier bearerAuthSupplier;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        countriesTest();

	}
  public static void countriesTest() {
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
//	  Consumer consumer = null;
//			  AuthorizationCodeGrant codeGrant = null;
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
    String accept = null;
    String lang = null;
    String limit = null;
    String offset = null;
    try
    {
String response = api.countries(countryCode, accept, lang, limit, offset);
System.out.println("RESPONSE"+response);
ObjectMapper jsonMapper = new ObjectMapper();
ReferenceCountries refCountries = jsonMapper.readValue(response, ReferenceCountries.class);
// or
Country country = refCountries.getCountryResource().getCountries().getCountry();
Names names = country.getNames();
//assertEquals("FI","FI",country.getCountryCode());
//assertNotEquals("FI","FI",country.getCountryCode());
//xmlMapper.writeValue(new File("/tmp/stuff.json"), new ReferenceCountries());        
//System.out.println(response);
// JSON input: {"name":"refCountries","bar":{"id":42}}

ObjectMapper xmlMapper = new XmlMapper();
//String xml = xmlMapper.writeValueAsString(new Country());
String xml = xmlMapper.writeValueAsString(country);
//    System.out.println(xmlMapper.writeValueAsString(refCountries));
System.out.println(xml);
ObjectMapper xmlMapper2 = new XmlMapper();
//String xml = xmlMapper.writeValueAsString(new Country());
String xml2 = xmlMapper2.writeValueAsString(refCountries);
//    System.out.println(xmlMapper.writeValueAsString(refCountries));
System.err.println(xml2);

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
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}
