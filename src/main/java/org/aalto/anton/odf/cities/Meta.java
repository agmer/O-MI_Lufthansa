
package org.aalto.anton.odf.cities;

import java.io.Serializable;
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
    "@Version",
    "Link"
})
public class Meta implements Serializable
{

    @JsonProperty("@Version")
    private String version;
//    @JsonProperty("Link")
//    private List<Link> link = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -6679063728039494264L;

    @JsonProperty("@Version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("@Version")
    public void setVersion(String version) {
        this.version = version;
    }

//    @JsonProperty("Link")
//    public List<Link> getLink() {
//        return link;
//    }
//
//    @JsonProperty("Link")
//    public void setLink(List<Link> link) {
//        this.link = link;
//    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
