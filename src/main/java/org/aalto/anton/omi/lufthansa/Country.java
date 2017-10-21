
package org.aalto.anton.omi.lufthansa;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.*;
//import javax.xml.bind.annotation.XmlElemen;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "CountryCode",
    "ZoneCode",
    "Names"
})
//@XmlRootElement(namespace="http://www.example.org/type",name="CountrCode")
//@XmlType(propOrder = { "id", "firstName", "lastName", "email", "telephone" })
//@JacksonXmlRootElement(namespace="http://www.example.org/type",localName="Maa")
//@XmlType(namespace="http://www.example.org/type")
public class Country {

//    @JacksonXmlProperty(namespace="http://www.e.org/type",localName="CountrCode")
  @JacksonXmlProperty(namespace="http://www.e.org/type")
    @JsonProperty("CountryCode")
    private String countryCode;
    @JsonProperty("ZoneCode")
    private String zoneCode;
    @JsonProperty("Names")
    private Names names;
//    @JsonIgnore
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("CountryCode")
    public String getCountryCode() {
        return countryCode;
    }

    @JsonProperty("CountryCode")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @JsonProperty("ZoneCode")
    public String getZoneCode() {
        return zoneCode;
    }

    @JsonProperty("ZoneCode")
    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    @JsonProperty("Names")
    public Names getNames() {
        return names;
    }

    @JsonProperty("Names")
    public void setNames(Names names) {
        this.names = names;
    }

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
