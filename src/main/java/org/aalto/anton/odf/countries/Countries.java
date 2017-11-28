
package org.aalto.anton.odf.countries;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.aalto.anton.odf.cities.AirportCode;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

//@JacksonXmlRootElement(namespace="http://www.opengroup.org/xsd/odf/1.0",localName="Object")

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Country"
})
@XmlTransient
public class Countries {

    @JsonProperty("Country")
//    private Country country;
    private List<Country> country;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

//    @JsonProperty("Country")
//    public Country getCountry() {
//        return country;
//    }
//    @JsonProperty("Country")
//    public void setCountry(Country country) {
//        this.country = country;
//    }
  @JsonProperty("Country")
    public List<Country> getCountry() {
        return country;
    }


@JsonProperty("Country")
    public void setCountry(List<Country> country) {
        this.country = country;
    }

    
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
