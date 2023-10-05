package io.github.bruno130187.vendasapi.repository.projections;

import java.math.BigDecimal;

public interface VendaPorMes {
    Integer getMes();
    BigDecimal getValor();
}
