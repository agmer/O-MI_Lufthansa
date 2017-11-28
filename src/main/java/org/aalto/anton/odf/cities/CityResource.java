
package org.aalto.anton.odf.cities;

import java.io.Serializable;
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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonPropertyOrder({
//    "Cities",
//    "Meta"
//})
@JsonPropertyOrder({
    "Cities"
})

@XmlType(name = "", propOrder = {
	    "id"
	})

@XmlRootElement(name = "Object")

public class CityResource implements Serializable
{
    @XmlAttribute(name = "type")
    protected String type="https://iata.org/CityResource";
    @XmlElement(name="id")
    protected String id="cities";

    @JsonProperty("Cities")
    @XmlTransient
    private Cities cities;
//    @JsonProperty("Meta")
//    @XmlTransient
//    private Meta meta;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 522909081346630519L;

    @JsonProperty("Cities")
    @XmlTransient
    public Cities getCities() {
        return cities;
    }
    @XmlElement(name="Object")
    public List<City> getCity()
    {
    	return cities.getCity();
    }

    @JsonProperty("Cities")
    public void setCities(Cities cities) {
        this.cities = cities;
    }

//    @JsonProperty("Meta")
//    @XmlTransient
//    public Meta getMeta() {
//        return meta;
//    }
//
//    @JsonProperty("Meta")
//    public void setMeta(Meta meta) {
//        this.meta = meta;
//    }

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
