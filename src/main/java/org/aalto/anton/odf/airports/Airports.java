
package org.aalto.anton.odf.airports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Airport"
})
public class Airports {

    @JsonProperty("Airport")
    private List<Airport> airport;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

//    @JsonProperty("Airport")
//    public Airport getAirport() {
//        return airport;
//    }
//
//    @JsonProperty("Airport")
//    public void setAirport(Airport airport) {
//        this.airport = airport;
//    }
    
    @JsonProperty("Airport")
    public List<Airport> getAirport() {
        return airport;
    }
    @JsonProperty("Airport")
    public void setAirport(List<Airport> airport) {
        this.airport = airport;
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
