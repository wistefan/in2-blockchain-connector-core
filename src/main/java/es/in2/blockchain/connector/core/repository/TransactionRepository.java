package es.in2.blockchain.connector.core.repository;

import es.in2.blockchain.connector.core.domain.Transaction;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends RevisionRepository<Transaction, UUID, Integer> {
    void save(Transaction transaction);
    Transaction findById(UUID uuid);
}
