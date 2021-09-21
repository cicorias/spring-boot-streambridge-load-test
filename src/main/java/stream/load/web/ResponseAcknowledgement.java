package stream.load.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "ack"
})

@Data
public class ResponseAcknowledgement {
    @JsonProperty("ack")
    @JsonPropertyDescription("ack integer provided")
    private Integer evtSeq;
  
}
