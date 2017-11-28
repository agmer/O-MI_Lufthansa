
package org.aalto.anton.odf.cities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "AirportCode"
})
public class Airports implements Serializable
{
	   @XmlAttribute(name = "type")
	    protected String type="https://iata.org/Airports";
	    @XmlElement(name = "id")
//	    private String airportCode;
	  private String id="airports";

    @JsonProperty("AirportCode")
//    @XmlElement(name = "Object")
//  private AirportCode airportCode;
//  private String airportCode;

    @JacksonXmlElementWrapper(useWrapping=false)
    @XmlElement(name = "InfoItem")
  private List<AirportCode> airportCode = new ArrayList<AirportCode>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

   
    @JsonProperty("AirportCode")
    @XmlElement(name = "InfoItem")
    public List<AirportCode> getAirportCode() {
        return airportCode;
    }

    @JsonProperty("AirportCode")
    public void setAirportCode(List<AirportCode> airportCode) {
        this.airportCode = airportCode;
    }
    
//    @JsonProperty("AirportCode")
//    @XmlElement(name = "Object")
//    public AirportCode getAirportCode() {
//        return airportCode;
//    }
//
//    @JsonProperty("AirportCode")
//    public void setAirportCode(AirportCode airportCode) {
//        this.airportCode = airportCode;
//    }
    


}
