
package org.aalto.anton.odf.cities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
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
    "Coordinate"
})
@XmlType(name = "", propOrder = {
	    "id"
	})

public class Position implements Serializable
{
	   @XmlAttribute(name = "type")
	    protected String type="http://schema.org/GeoCoordinates";
	    @XmlElement(name = "id")
	  private String id="geogoordinates";

    @JsonProperty("Coordinate")
    @XmlTransient
    private Coordinate coordinate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -9030451384610050092L;

    @JsonProperty("Coordinate")
    @XmlTransient
    public Coordinate getCoordinate() {
        return coordinate;
    }
    @XmlElement(name = "InfoItem")
    public Lat getLat() {
        return new Lat(coordinate.getLatitude().toString());
    }
    @XmlElement(name = "InfoItem2")
    public Long getLong() {
        return new Long(coordinate.getLongitude().toString());
    }

    @JsonProperty("Coordinate")
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
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
