
package o.masllo.wsgeocoding.model.dto;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "inTimestamp",
    "outTimestamp",
    "timeLapse:"
})
public class Audit {

    @JsonProperty("inTimestamp")
    private Integer inTimestamp;
    @JsonProperty("outTimestamp")
    private Integer outTimestamp;
    @JsonProperty("timeLapse:")
    private Integer timeLapse;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("inTimestamp")
    public Integer getInTimestamp() {
        return inTimestamp;
    }

    @JsonProperty("inTimestamp")
    public void setInTimestamp(Integer inTimestamp) {
        this.inTimestamp = inTimestamp;
    }

    @JsonProperty("outTimestamp")
    public Integer getOutTimestamp() {
        return outTimestamp;
    }

    @JsonProperty("outTimestamp")
    public void setOutTimestamp(Integer outTimestamp) {
        this.outTimestamp = outTimestamp;
    }

    @JsonProperty("timeLapse:")
    public Integer getTimeLapse() {
        return timeLapse;
    }

    @JsonProperty("timeLapse:")
    public void setTimeLapse(Integer timeLapse) {
        this.timeLapse = timeLapse;
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
