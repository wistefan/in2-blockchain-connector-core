package es.in2.blockchain.connector.core.service.impl;

import es.in2.blockchain.connector.core.domain.Transaction;
import es.in2.blockchain.connector.core.repository.TransactionRepository;
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
    public Transaction editTransactionAttribute(UUID id, String newAttributeValue) {

        Transaction existingTransaction = transactionRepository.findById(id);

        if (existingTransaction != null) {
            existingTransaction.setStatus(newAttributeValue);
            return transactionRepository.save(existingTransaction);
        }

        throw new NoSuchElementException("Transaction not found.");

    }
}
