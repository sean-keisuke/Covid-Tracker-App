package com.sean.p3

import java.util.ArrayList
import java.util.HashMap

object CovidHistoryRepository {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<CovidHistoryItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, CovidHistoryItem> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(createDummyItem(i))
        }
    }

    private fun addItem(item: CovidHistoryItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.date, item)
    }

    private fun createDummyItem(position: Int): CovidHistoryItem {
        return CovidHistoryItem("Covid Stat Date", "covid pos stat", "covid death stat")
    }


    /**
     * A dummy item representing a piece of content.
     * date, postive, and death stats
     */
    data class CovidHistoryItem(val date: String, val covidPositive: String, val covidDeath: String) {
        override fun toString(): String = "$covidPositive: -  $covidDeath."
    }
}