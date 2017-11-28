
package org.aalto.anton.odf.cities;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "@LanguageCode",
    "$"
})
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "", propOrder = {
//    "value"
//})
//@XmlRootElement(name = "InfoItem")
//@XmlAccessorType(XmlAccessType.NONE)

public class Name {

//	@XmlElement(name="name")
    @JsonProperty("@LanguageCode")
    @JsonIgnore
//    @JacksonXmlProperty(isAttribute = true)
    private String languageCode;
//        @JacksonXmlProperty(localName="value")
//	@XmlAttribute(name="str")
    @JsonProperty("$")
    @XmlTransient
//	@XmlElement(name="value")
//	@XmlAttribute(name="str")  
    private String $;
 //   @XmlAttribute(name = "type",)
 //   private String type ="String";
    @XmlValue

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

//    @JacksonXmlProperty(localName="name",isAttribute=true)
    @JsonProperty("@LanguageCode")
//    @XmlValue
	@XmlAttribute(name="name")  
    public String getLanguageCode() {
        return languageCode;
    }

//	@XmlElement(name="lag")
//    @JacksonXmlProperty(localName="Lang")
//    @JacksonXmlProperty(localName="name",isAttribute=true)
    @JsonProperty("@LanguageCode")
    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
    

//    @JacksonXmlProperty(localName="value")
//    @XmlElement(name="value2")
    @JsonProperty("$")
//	@XmlElement(name="value",type=Value.class)
  //@XmlValue
    @XmlTransient
    public String get$() {
        return $;
    }
//    @XmlElement(name="value")
 //   @XmlAttribute(name="str")
    public Value getValue()
    {
    	return new Value($);
    }

//    @JacksonXmlProperty(localName="value")
//    @XmlElement(name="value2")
    @JsonProperty("$")
    public void set$(String $) {
        this.$ = $;
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
