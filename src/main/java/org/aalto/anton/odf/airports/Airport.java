
package org.aalto.anton.odf.airports;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.aalto.anton.odf.cities.CountryC;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "AirportCode",
    "Position",
    "CityCode",
    "CountryCode",
    "LocationType",
    "Names",
    "UtcOffset",
    "TimeZoneId"
})
@XmlType(name = "", propOrder = {
	    "id",
	    "InfoItem",
	})

public class Airport {

   @XmlAttribute(name = "type")
    protected String type="https://iata.org/Airport";
    @JsonProperty("AirportCode")
	@XmlElement(name="id")
    private String airportCode;
    @JsonProperty("Position")
	@XmlElement(name="Object2")
    private Position position;
    @XmlTransient
    @JsonProperty("CityCode")
    private String cityCode;
    @XmlTransient
    @JsonProperty("CountryCode")
    private String countryCode;
    @JsonProperty("LocationType")
    @XmlTransient
    private String locationType;
    @JsonProperty("Names")
	@XmlElement(name="Object")
    private Names names;
    @JsonProperty("UtcOffset")
    @XmlTransient
    private Integer utcOffset;
    @JsonProperty("TimeZoneId")
    @XmlTransient
    private String timeZoneId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("AirportCode")
	@XmlElement(name="id")
    public String getAirportCode() {
        return airportCode;
    }

    @JsonProperty("AirportCode")
    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    @JsonProperty("Position")
	@XmlElement(name="Object2")
    public Position getPosition() {
        return position;
    }

    @JsonProperty("Position")
    public void setPosition(Position position) {
        this.position = position;
    }

    @JsonProperty("CityCode")
    @XmlTransient
    public String getCityCode() {
        return cityCode;
    }

    @JsonProperty("CityCode")
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
    @XmlElement(name="Object5")
    public CityC getCityC() {
        return new CityC(cityCode);
    }
    
    @XmlTransient
    @JsonProperty("CountryCode")
    public String getCountryCode() {
        return countryCode;
    }
    @XmlElement(name="Object4")
    public CountryC getCountryC() {
        return new CountryC(countryCode);
    }

    @JsonProperty("CountryCode")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @JsonProperty("LocationType")
    @XmlTransient
    public String getLocationType() {
        return locationType;
    }

    @JsonProperty("LocationType")
    public void setLocationType(String locationType) {
        this.locationType = locationType;
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
	@XmlElement(name="Object6")
    public Timezone getTimezone()
    {
    	return new Timezone(timeZoneId, utcOffset.toString());
    }

    @JsonProperty("UtcOffset")
    @XmlTransient
    public Integer getUtcOffset() {
        return utcOffset;
    }

    @JsonProperty("UtcOffset")
    public void setUtcOffset(Integer utcOffset) {
        this.utcOffset = utcOffset;
    }

    @JsonProperty("TimeZoneId")
    @XmlTransient
    public String getTimeZoneId() {
        return timeZoneId;
    }

    @JsonProperty("TimeZoneId")
    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
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
