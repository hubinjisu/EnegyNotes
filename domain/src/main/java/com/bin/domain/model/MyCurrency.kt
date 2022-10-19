package com.bin.domain.model

import androidx.annotation.VisibleForTesting
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName
import java.util.Currency

/**
 * @param isoCode Der ISO 4217 Currency Code
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
sealed class MyCurrency(
    isoCode: String,
    private val scale: Int
) {

    private val currency: Currency = Currency.getInstance(isoCode)

    @JsonTypeName("EUR")
    object EURO : MyCurrency("EUR", 2)

    // Currently JUST for testing
    @VisibleForTesting
    @JsonTypeName("USD")
    internal object USD : MyCurrency("USD", 2)

    @JsonIgnore
    fun getIsoCode(): String = currency.currencyCode

    // ATTENTION: Based on the locale different symbols can be returned here; on JDK 8 symbol resolution doesn't
    // seem to be possible, so "EUR" (the code) is returned, on JDK 11 the resolution works and "€" is returned.
    // This is however only important in Unit Tests, as on device we have a different JDK implementation obviously.
    @JsonIgnore
    fun getSymbol(): String = currency.symbol

    @JsonIgnore
    internal fun getScale(): Int = scale

    override fun toString(): String = getIsoCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MyCurrency) return false

        if (currency != other.currency) return false
        return true
    }

    override fun hashCode(): Int = currency.hashCode()

    companion object {
        private val currencies by lazy {
            setOf(EURO, USD).map {
                it.getIsoCode() to it
            }.toMap()
        }

        fun fromIsoCode(code: String): MyCurrency =
            currencies[code] ?: error("unknown currency code $code")

        internal val LOCAL_CURRENCY by lazy { EURO }
    }
}