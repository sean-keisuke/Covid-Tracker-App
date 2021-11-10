package com.sean.p3

class StateMap {
    private val statesMap: MutableMap<String, String> = HashMap()
    init{
        statesMap["Alabama"] = "AL"
        statesMap["Alaska"] = "AK"
        statesMap["Arizona"] = "AZ"
        statesMap["Arkansas"] = "AR"
        statesMap["California"] = "CA"
        statesMap["Colorado"] = "CO"
        statesMap["Connecticut"] = "CT"
        statesMap["Delaware"] = "DE"
        statesMap["Florida"] = "FL"
        statesMap["Georgia"] = "GA"
        statesMap["Hawaii"] = "HI"
        statesMap["Idaho"] = "ID"
        statesMap["Illinois"] = "IL"
        statesMap["Indiana"] = "IN"
        statesMap["Iowa"] = "IA"
        statesMap["Kansas"] = "KS"
        statesMap["Kentucky"] = "KY"
        statesMap["Louisiana"] = "LA"
        statesMap["Maine"] = "ME"
        statesMap["Maryland"] = "MD"
        statesMap["Massachusetts"] = "MA"
        statesMap["Michigan"] = "MI"
        statesMap["Minnesota"] = "MN"
        statesMap["Mississippi"] = "MS"
        statesMap["Missouri"] = "MO"
        statesMap["Montana"] = "MT"
        statesMap["Nebraska"] = "NE"
        statesMap["Nevada"] = "NV"
        statesMap["New Hampshire"] = "NH"
        statesMap["New Jersey"] = "NJ"
        statesMap["New Mexico"] = "NM"
        statesMap["New York"] = "NY"
        statesMap["North Carolina"] = "NC"
        statesMap["North Dakota"] = "ND"
        statesMap["Ohio"] = "OH"
        statesMap["Oklahoma"] = "OK"
        statesMap["Oregon"] = "OR"
        statesMap["Pennsylvania"] = "PA"
        statesMap["Rhode Island"] = "RI"
        statesMap["South Carolina"] = "SC"
        statesMap["South Dakota"] = "SD"
        statesMap["Tennessee"] = "TN"
        statesMap["Texas"] = "TX"
        statesMap["Utah"] = "UT"
        statesMap["Vermont"] = "VT"
        statesMap["Virginia"] = "VA"
        statesMap["Washington"] = "WA"
        statesMap["West Virginia"] = "WV"
        statesMap["Wisconsin"] = "WI"
        statesMap["Wyoming"] = "WY"
    }

    fun getStateID(state: String): String? {
        return statesMap[state]
    }
}