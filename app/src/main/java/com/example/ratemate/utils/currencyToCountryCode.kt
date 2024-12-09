package com.example.ratemate.utils

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
