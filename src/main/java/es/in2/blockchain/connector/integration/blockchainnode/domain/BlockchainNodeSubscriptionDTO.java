package es.in2.blockchain.connector.integration.blockchainnode.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlockchainNodeSubscriptionDTO {

    @JsonProperty("eventTypes")
    private List<String> eventTypeList;

    @JsonProperty("notificationEndpoint")
    private String notificationEndpoint;

}
