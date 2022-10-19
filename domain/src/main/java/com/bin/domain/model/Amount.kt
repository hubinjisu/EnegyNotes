package com.bin.domain.model

import com.bin.domain.model.MyCurrency.Companion.LOCAL_CURRENCY
import com.fasterxml.jackson.annotation.JsonIgnore
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.Locale

data class Amount(
    val value: BigDecimal,
    val currency: MyCurrency = LOCAL_CURRENCY
) {
    operator fun unaryMinus(): Amount = copy(value = -value)

    operator fun minus(other: Amount): Amount = plus(-other)

    operator fun plus(other: Amount): Amount {
        check(currency == other.currency) {
            "Cannot add/subtract amounts with different currencies"
        }
        return copy(value = value + other.value)
    }

    operator fun compareTo(other: Amount): Int {
        check(currency == other.currency) {
            "Cannot compare amounts with different currencies"
        }
        return value.compareTo(other.value)
    }

    /**
     * Provides the remainder operator (the well-known modulo %).
     */
    operator fun rem(other: Amount): Amount {
        return Amount(value % other.value, currency)
    }

    /**
     * @return eg. Euro * 100 +  Cent : 1.05 -> 105
     */
    @JsonIgnore
    fun getIntValueExactly(): Int =
        value.scaleByPowerOfTen(currency.getScale())
            .setScale(0, RoundingMode.HALF_UP)
            .intValueExact()

    /**
     * @return eg. 60,00 -> 60
     */
    fun getRoundedIntValue(roundingMode: RoundingMode): Int =
        value.setScale(0, roundingMode).intValueExact()

    fun toFormattedString(locale: Locale = Locale.GERMANY) =
        String.format(locale, "%.${currency.getScale()}f %s", value.toFloat(), currency.getSymbol())

    fun toFormattedWertString(locale: Locale = Locale.GERMANY) =
        String.format(locale, "%.${this.currency.getScale()}f", value)
}