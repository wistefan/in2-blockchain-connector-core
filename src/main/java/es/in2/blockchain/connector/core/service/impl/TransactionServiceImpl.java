package es.in2.blockchain.connector.core.service.impl;

import es.in2.blockchain.connector.core.domain.Transaction;
import es.in2.blockchain.connector.core.repository.TransactionRepository;
import es.in2.blockchain.connector.core.service.HashLinkService;
import es.in2.blockchain.connector.core.service.TransactionService;
import es.in2.blockchain.connector.core.utils.AuditStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final HashLinkService hashLinkService;

    @Override
    public Transaction createTransaction(String entityId, String entityHash, String datalocation) {
        Transaction transaction = Transaction.builder()
                .entityId(entityId)
                .entityHash(entityHash)
                .dataLocation(datalocation)
                .status(AuditStatus.RECEIVED.getDescription())
                .build();

        return  transactionRepository.save(transaction);
    }

    @Override
    public Transaction editTransactionStatus(UUID id, String newAttributeValue) {

        Transaction transactionFound = transactionRepository.findById(id);
        checkIfTransactionExist(transactionFound);
        transactionFound.setStatus(newAttributeValue);
        return transactionRepository.save(transactionFound);
    }

    @Override
    public Transaction editTransactionHash(UUID id, String datalocation) {
        Transaction transactionFound = transactionRepository.findById(id);
        checkIfTransactionExist(transactionFound);
        transactionFound.setEntityHash(hashLinkService.extractHashLink(datalocation));
        return transactionRepository.save(transactionFound);
    }

    private void checkIfTransactionExist(Transaction transactionFound) {
        if (transactionFound == null) {
            throw new NoSuchElementException("Transaction not found.");
        }
    }
}
