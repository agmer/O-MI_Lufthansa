
package org.aalto.anton.odf.airports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Airports",
    "Meta"
})

@XmlType(name = "", propOrder = {
	    "id"
	})

@XmlRootElement(name = "Object")

public class AirportResource {
    @XmlAttribute(name = "type")
    protected String type="https://iata.org/Airports";
    @XmlElement(name="id")
    protected String id="airports";

    @JsonProperty("Airports")
    @XmlTransient
    private Airports airports;
//    @XmlTransient
//    @JsonProperty("Meta")
//    private Meta meta;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Airports")
    @XmlTransient
   public Airports getAirports() {
        return airports;
    }
//    @XmlElement(name="Object")
//    public Airport getAirport()
//    {
//    	return airports.getAirport();
//    }
    @XmlElement(name="Object")
    public List<Airport> getAirport()
    {
    	return airports.getAirport();
    }

    @JsonProperty("Airports")
    public void setAirports(Airports airports) {
        this.airports = airports;
    }

//    @JsonProperty("Meta")
//    @XmlTransient
//        public Meta getMeta() {
//        return meta;
//    }
//
//    @JsonProperty("Meta")
//    public void setMeta(Meta meta) {
//        this.meta = meta;
//    }
//
//    @JsonAnyGetter
//    public Map<String, Object> getAdditionalProperties() {
//        return this.additionalProperties;
//    }
//
//    @JsonAnySetter
//    public void setAdditionalProperty(String name, Object value) {
//        this.additionalProperties.put(name, value);
//    }

}
