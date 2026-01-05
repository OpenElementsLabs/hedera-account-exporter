package de.devlodge.hedera.account.export.exchange;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ExchangeClient {

    BigDecimal getCurrentExchangeRate(ExchangePair pair) throws Exception;


    BigDecimal getExchangeRate(final ExchangePair pair, final LocalDate date) throws Exception;
}
