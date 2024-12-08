package com.example.ratemate.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ratemate.R
import com.example.ratemate.data.api.ExchangeRatesViewModel
import com.example.ratemate.ui.components.CurrencyCard
import com.murgupluoglu.flagkit.FlagKit
import androidx.compose.ui.platform.LocalContext


//import com.murgupluoglu.flagkit.Flagkit


@Composable
fun HomeScreen(viewModel: ExchangeRatesViewModel = viewModel()) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LaunchedEffect(Unit) {
            viewModel.fetchAndSaveExchangeRates()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(text="Popular")

            val currencies = viewModel.currencies.collectAsState(initial = emptyList())
            LazyColumn  {
                currencies.value.forEach { currency ->
                    if (currency.currencyCode == "CAD" || currency.currencyCode == "USD" || currency.currencyCode == "CNY" || currency.currencyCode == "IDR") {
                        item {
                            val context = LocalContext.current
                            val flagResource = FlagKit.getResId(context, currencyToCountryCode(currency.currencyCode))

                            val percentageChange = calculatePercentageChange(currency.rate)

                            CurrencyCard(
                                flag = painterResource(id = flagResource),
                                countryName = currency.currencyCode,
                                exchangeRate = currency.rate.toString(),
                                isPositive = percentageChange.startsWith("+"),
                                percentageChange = percentageChange,
                                isFavorited = currency.isFavorited,
                                onFavoriteClick = {
                                    viewModel.toggleFavoriteStatus(
                                        currency.currencyCode,
                                        !currency.isFavorited
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

fun calculatePercentageChange(rate: Double): String {
    // Placeholder for actual percentage change calculation logic
    // For now, let's assume it's "+1.00%"
    return "+1.00%"
}

fun currencyToCountryCode(currencyCode: String): String {
    return when (currencyCode) {
        "AED" -> "AE"
        "AFN" -> "AF"
        "ALL" -> "AL"
        "AMD" -> "AM"
        "ANG" -> "AN"
        "AOA" -> "AO"
        "ARS" -> "AR"
        "AUD" -> "AU"
        "AWG" -> "AW"
        "AZN" -> "AZ"
        "BAM" -> "BA"
        "BBD" -> "BB"
        "BDT" -> "BD"
        "BGN" -> "BG"
        "BHD" -> "BH"
        "BIF" -> "BI"
        "BMD" -> "BM"
        "BND" -> "BN"
        "BOB" -> "BO"
        "BRL" -> "BR"
        "BSD" -> "BS"
        "BTN" -> "BT"
        "BWP" -> "BW"
        "BYN" -> "BY"
        "BZD" -> "BZ"
        "CAD" -> "CA"
        "CDF" -> "CD"
        "CHF" -> "CH"
        "CLP" -> "CL"
        "CNY" -> "CN"
        "COP" -> "CO"
        "CRC" -> "CR"
        "CUP" -> "CU"
        "CVE" -> "CV"
        "CZK" -> "CZ"
        "DJF" -> "DJ"
        "DKK" -> "DK"
        "DOP" -> "DO"
        "DZD" -> "DZ"
        "EGP" -> "EG"
        "ERN" -> "ER"
        "ETB" -> "ET"
        "EUR" -> "EU"
        "FJD" -> "FJ"
        "FKP" -> "FK"
        "GBP" -> "GB"
        "GEL" -> "GE"
        "GHS" -> "GH"
        "GIP" -> "GI"
        "GMD" -> "GM"
        "GNF" -> "GN"
        "GTQ" -> "GT"
        "GYD" -> "GY"
        "HKD" -> "HK"
        "HNL" -> "HN"
        "HRK" -> "HR"
        "HTG" -> "HT"
        "HUF" -> "HU"
        "IDR" -> "ID"
        "ILS" -> "IL"
        "INR" -> "IN"
        "IQD" -> "IQ"
        "IRR" -> "IR"
        "ISK" -> "IS"
        "JMD" -> "JM"
        "JOD" -> "JO"
        "JPY" -> "JP"
        "KES" -> "KE"
        "KGS" -> "KG"
        "KHR" -> "KH"
        "KPW" -> "KP"
        "KRW" -> "KR"
        "KWD" -> "KW"
        "KYD" -> "KY"
        "KZT" -> "KZ"
        "LAK" -> "LA"
        "LBP" -> "LB"
        "LKR" -> "LK"
        "LRD" -> "LR"
        "LSL" -> "LS"
        "LTL" -> "LT"
        "LVL" -> "LV"
        "LYD" -> "LY"
        "MAD" -> "MA"
        "MDL" -> "MD"
        "MGA" -> "MG"
        "MKD" -> "MK"
        "MMK" -> "MM"
        "MNT" -> "MN"
        "MOP" -> "MO"
        "MRU" -> "MR"
        "MUR" -> "MU"
        "MVR" -> "MV"
        "MWK" -> "MW"
        "MXN" -> "MX"
        "MYR" -> "MY"
        "MZN" -> "MZ"
        "NAD" -> "NA"
        "NGN" -> "NG"
        "NIO" -> "NI"
        "NOK" -> "NO"
        "NPR" -> "NP"
        "NZD" -> "NZ"
        "OMR" -> "OM"
        "PAB" -> "PA"
        "PEN" -> "PE"
        "PGK" -> "PG"
        "PHP" -> "PH"
        "PKR" -> "PK"
        "PLN" -> "PL"
        "PYG" -> "PY"
        "QAR" -> "QA"
        "RON" -> "RO"
        "RSD" -> "RS"
        "RUB" -> "RU"
        "RWF" -> "RW"
        "SAR" -> "SA"
        "SBD" -> "SB"
        "SCR" -> "SC"
        "SDG" -> "SD"
        "SEK" -> "SE"
        "SGD" -> "SG"
        "SHP" -> "SH"
        "SLL" -> "SL"
        "SOS" -> "SO"
        "SRD" -> "SR"
        "STD" -> "ST"
        "SVC" -> "SV"
        "SYP" -> "SY"
        "SZL" -> "SZ"
        "THB" -> "TH"
        "TJS" -> "TJ"
        "TMT" -> "TM"
        "TND" -> "TN"
        "TOP" -> "TO"
        "TRY" -> "TR"
        "TTD" -> "TT"
        "TWD" -> "TW"
        "TZS" -> "TZ"
        "UAH" -> "UA"
        "UGX" -> "UG"
        "USD" -> "US"
        "UYU" -> "UY"
        "UZS" -> "UZ"
        "VES" -> "VE"
        "VND" -> "VN"
        "VUV" -> "VU"
        "WST" -> "WS"
        "XAF" -> "XF"
        "XCD" -> "XC"
        "XOF" -> "XO"
        "XPF" -> "XP"
        "YER" -> "YE"
        "ZAR" -> "ZA"
        "ZMW" -> "ZM"
        "ZWL" -> "ZW"
        else -> "Unknown"
    }
}

