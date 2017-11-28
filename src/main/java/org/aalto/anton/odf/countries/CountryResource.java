
package org.aalto.anton.odf.countries;

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
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
//@JacksonXmlRootElement(namespace="http://www.opengroup.org/xsd/odf/1.0",localName="Object")
@JsonIgnoreProperties(ignoreUnknown = true)
//@JacksonStdImpl
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Countries"
})
//@XmlRootElement(name = "book")
//@XmlType(propOrder = {"Meta", "Countries"})
@XmlType(name = "", propOrder = {
	    "id"
	})

@XmlRootElement(name = "Object")

public class CountryResource {
    @XmlAttribute(name = "type")
    protected String type="https://iata.org/CountryResource";
    @XmlElement(name="id")
    protected String id="countries";

    @JsonProperty("Countries")
    @JacksonXmlElementWrapper(useWrapping=false)
    @XmlTransient
    private Countries countries;
//    @JsonProperty("Meta")
//    @XmlTransient
//    private Meta meta;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Countries")
    @XmlTransient
    public Countries getCountries() {
        return countries;
    }

    @JsonProperty("Countries")
    public void setCountries(Countries countries) {
        this.countries = countries;
    }
    @XmlElement(name="Object")
    public List<Country> getCountry()
    {
    	return countries.getCountry();
    }
    
//    @XmlTransient
//    @JsonProperty("Meta")
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
