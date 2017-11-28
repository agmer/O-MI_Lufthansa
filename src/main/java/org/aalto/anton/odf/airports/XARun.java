package org.aalto.anton.odf.airports;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.aalto.anton.odf.countries.CountryResource;
import org.aalto.anton.odf.countries.ReferenceCountries;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

public class XARun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			loadJSONtoODFfile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
	public static void loadJSONtoODFfile() throws IOException {
	  	String basePath = "C:\\cygwin64\\home\\panheant\\th_";
	  	String x ="airports";
	  	//".json" th_airports_HEL.json
	  	String fileName =x.replace("/", "_");
	  	List<String> lines = Files.readAllLines(Paths.get(basePath+fileName+".json"),
					Charset.forName("UTF-8"));
			// O-DF
			String json = lines.toString();
//			System.err.println(json);
			ObjectMapper jsonMapper = new ObjectMapper();
//			CitiesFoul citiesF = (CitiesFoul) jsonMapper.readValue(new File(basePath+fileName+".json"), CitiesFoul.class);
			jsonMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			ReferenceAirports ae = (ReferenceAirports) jsonMapper.readValue(new File(basePath+fileName+".json"), ReferenceAirports.class);

			ObjectMapper xmlMapper2 = new XmlMapper();
			JaxbAnnotationModule module = new JaxbAnnotationModule();
			AirportResource ar = ae.getAirportResource();
			// configure as necessary
//			Airport c =cr.getCities().getAirport();
//			c.setNames(null);
			ObjectMapper xmlMapper = new XmlMapper();
			xmlMapper.registerModule(module);
			xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
	//		cr.setMeta(null);
//			System.out.println(xmlMapper.writeValueAsString(ar));
			String ot = xmlMapper.writeValueAsString(ar).replaceAll("Object[0-9]", "Object").replaceAll("InfoItem[0-9]", "InfoItem");
//			String ot2 = xmlMapper.writeValueAsString(ar).replaceAll("InfoItem[0-9]", "InfoItem");
			System.out.println(ot);

	}

}
