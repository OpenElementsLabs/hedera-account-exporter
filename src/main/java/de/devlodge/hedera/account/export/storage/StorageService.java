package de.devlodge.hedera.account.export.storage;

import de.devlodge.hedera.account.export.exchange.ExchangePair;
import de.devlodge.hedera.account.export.model.Transaction;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public interface StorageService {

    void addNote(final Transaction transaction, final String note);

    Optional<String> getNote(final Transaction transaction);

    void addExchangeRate(final ExchangePair pair, final LocalDate date, BigDecimal rate);

    Optional<BigDecimal> getExchangeRate(final ExchangePair pair, final LocalDate date);
}
