
package org.aalto.anton.odf.cities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Name"
})
@XmlRootElement(name = "Object")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Names {

    @XmlAttribute(name = "type")
    protected String type="https://iata.org/CityNames";
    @XmlElement(name="id")
    protected String id="citynames";
    @JsonProperty("Name")
    @JacksonXmlElementWrapper(useWrapping=false)
    @XmlElement(name = "InfoItem")
    private List<Name> name = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

   
    @JsonProperty("Name")
    @XmlElement(name = "InfoItem")
    public List<Name> getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(List<Name> name) {
        this.name = name;
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
