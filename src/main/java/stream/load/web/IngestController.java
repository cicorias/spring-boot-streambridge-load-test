package stream.load.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageHandlingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class IngestController {

  Logger logger = LoggerFactory.getLogger(IngestController.class);

  private final ObjectMapper mapper;

    @PostMapping
    public ResponseEntity<ResponseAcknowledgement> sendMessage(@RequestBody String payload) throws MessageHandlingException {

      try {
        JsonNode jsonPayload = mapper.readTree(payload);
        String binding = jsonPayload.at("/data/direction").asText("nowhere");
        int ackValue = jsonPayload.at("/ack").asInt(-1);
        String correlationId = jsonPayload.at("/correlationId").asText("no-correlation");
        logger.info("using binding={} for correlationId={}", binding, correlationId);

        if (ackValue != -1) {
          ResponseAcknowledgement acknowledgement = new ResponseAcknowledgement();
          acknowledgement.setEvtSeq(ackValue);
          return ResponseEntity.ok(acknowledgement);
        } else {
          return ResponseEntity.noContent().build();
        }

      } catch (JsonProcessingException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
      }
    }
    
}
