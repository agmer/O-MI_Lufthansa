
package org.aalto.anton.odf.countries;
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

@XmlType(name = "", propOrder = {
    "id",
    "InfoItem",
})

//@XmlRootElement(name = "Object")
public class Country {
    @XmlAttribute(name = "type")
    protected String type="https://iata.org/Country";

    @JsonProperty("CountryCode")
	@XmlElement(name="id")
    private String countryCode;
    @JsonProperty("ZoneCode")
    @XmlTransient
    private String zoneCode;
    @JsonProperty("Names")
	@XmlElement(name="Object")
    private Names names;
//    @JsonIgnore
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@XmlElement(name="id")
    @JsonProperty("CountryCode")
    public String getCountryCode() {
        return countryCode;
    }

    @JsonProperty("CountryCode")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @JsonProperty("ZoneCode")
    @XmlTransient
    public String getZoneCode() {
        return zoneCode;
    }
    @XmlElement(name="InfoItem")
    public Zone getValue()
    {
    	return new Zone(zoneCode);
    }

    @JsonProperty("ZoneCode")
    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    @JsonProperty("Names")
	@XmlElement(name="Object")
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
