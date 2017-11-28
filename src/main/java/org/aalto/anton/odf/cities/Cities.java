
package org.aalto.anton.odf.cities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "City"
})
public class Cities implements Serializable
{

    @JsonProperty("City")
//    private City city;
    private List<City> city;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

//    @JsonProperty("City")
//    public City getCity() {
//        return city;
//    }
//
//    @JsonProperty("City")
//    public void setCity(City city) {
//        this.city = city;
//    }
    @JsonProperty("City")
    public List<City> getCity() {
        return city;
    }


@JsonProperty("City")
    public void setCity(List<City> city) {
        this.city = city;
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
