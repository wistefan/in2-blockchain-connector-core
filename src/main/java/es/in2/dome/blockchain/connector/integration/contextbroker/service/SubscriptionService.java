package es.in2.dome.blockchain.connector.integration.contextbroker.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.concurrent.ExecutionException;

public interface SubscriptionService {
    void createSubscription() throws JsonProcessingException, ExecutionException, InterruptedException;


}
