
package org.aalto.anton.odf.countries;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

//@JacksonXmlRootElement(namespace="http://www.opengroup.org/xsd/odf/1.0",localName="Object")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "CountryResource"
})
public class ReferenceCountries {

    @JsonProperty("CountryResource")
    private CountryResource countryResource;
//    @JacksonXmlCData
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("CountryResource")
    public CountryResource getCountryResource() {
        return countryResource;
    }

    @JsonProperty("CountryResource")
    public void setCountryResource(CountryResource countryResource) {
        this.countryResource = countryResource;
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
