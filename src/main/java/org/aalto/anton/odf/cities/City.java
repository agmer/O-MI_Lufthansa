
package org.aalto.anton.odf.cities;

import java.io.Serializable;
import java.util.HashMap;
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
    "CityCode",
    "CountryCode",
    "Position",
    "Names",
    "Airports"
})
@XmlType(name = "", propOrder = {
	    "id",
	    "InfoItem",
	})
//@XmlRootElement(name = "Object")
public class City implements Serializable
{
	   @XmlAttribute(name = "type")
	    protected String type="https://iata.org/City";

    @JsonProperty("CityCode")
	@XmlElement(name="id")
    private String cityCode;
    @JsonProperty("CountryCode")
    @XmlTransient
    private String countryCode;
    @JsonProperty("Position")
	@XmlElement(name="Object2")
    private Position position;
    @JsonProperty("Names")
	@XmlElement(name="Object")
    private Names names;
    @JsonProperty("Airports")
	@XmlElement(name="Object3")
    private Airports airports;

    @JsonProperty("CityCode")
	@XmlElement(name="id")
    public String getCityCode() {
        return cityCode;
    }

    @JsonProperty("CityCode")
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    @JsonProperty("CountryCode")
    @XmlTransient
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

    @JsonProperty("Position")
	@XmlElement(name="Object2")
    public Position getPosition() {
        return position;
    }

    @JsonProperty("Position")
    public void setPosition(Position position) {
        this.position = position;
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

    @JsonProperty("Airports")
	@XmlElement(name="Object3")
    public Airports getAirports() {
        return airports;
    }

    @JsonProperty("Airports")
    public void setAirports(Airports airports) {
        this.airports = airports;
    }


}
