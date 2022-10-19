package com.bin.domain.model

import java.math.BigDecimal

fun nullBetragVon(myCurrency: MyCurrency) = Amount(BigDecimal.ZERO.setScale(myCurrency.getScale()), myCurrency)
fun nullBetragInHauswaehrung() = nullBetragVon(MyCurrency.LOCAL_CURRENCY)

fun Int.toBetragInHauswaehrung() = toBetrag(MyCurrency.LOCAL_CURRENCY)

fun Long.toBetragInHauswaehrung() = toBetrag(MyCurrency.LOCAL_CURRENCY)

fun Int.toBetrag(myCurrency: MyCurrency): Amount = this.toLong().toBetrag(myCurrency)

fun Long.toBetrag(myCurrency: MyCurrency): Amount =
    Amount(BigDecimal.valueOf(this, myCurrency.getScale()), myCurrency)

fun Amount.isZeroBetrag() = this.value.compareTo(BigDecimal.ZERO) == 0