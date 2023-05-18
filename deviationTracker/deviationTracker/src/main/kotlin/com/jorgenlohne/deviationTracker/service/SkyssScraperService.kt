package com.jorgenlohne.deviationTracker.service

import com.jorgenlohne.deviationTracker.model.Deviation
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Service

@Service
class SkyssScraperService {

    val SKYSS_DEVIATON_URL = "https://www.skyss.no/avvik/" //TODO move to properties file
    val DEVIATION = "accordion--driftsmelding"
    val ROUTE = "accordion__route-numbers"
    val CONTENT = "accordion__content"


    /**
     * Scrapes deviations from Skyss maps them to deviation Objects
     * */
    fun getCurrentDeviations(): List<Deviation> {
        val doc: Document = Jsoup.connect(SKYSS_DEVIATON_URL).get();

        return mapDocumentToDeviations(doc)
    }


    private fun mapDocumentToDeviations(doc: Document): List<Deviation> {

        return doc.getElementsByClass(DEVIATION)
            .map { e ->
                Deviation(
                    e.getElementsByClass(ROUTE).text()
                        .replace(" ", "")
                        .replace(".", "")
                        .split(","),
                    e.getElementsByClass(CONTENT).text(),
                    true
                )
            }.toList()
    }


}