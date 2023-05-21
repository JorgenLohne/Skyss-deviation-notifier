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
    val HEADING = "accordion__heading"


    /**
     * Get all routes that are experiencing a deviation.
     * */
    fun getCurrentDeviationRoutes(): Iterable<String> {
        val doc: Document = Jsoup.connect(SKYSS_DEVIATON_URL).get();
        return doc.getElementsByClass(DEVIATION)
            .flatMap {
                it.getElementsByClass(ROUTE).text()
                    .replace(" ", "")
                    .replace(".", "")
                    .split(",")
            }
    }


    /**
     * Scrapes deviations from Skyss and maps to deviation Objects.
     * */
    fun getCurrentDeviations(): Iterable<Deviation> {
        val doc: Document = Jsoup.connect(SKYSS_DEVIATON_URL).get();

        return mapDocumentToDeviations(doc)
    }


    private fun mapDocumentToDeviations(doc: Document): Iterable<Deviation> {

        return doc.getElementsByClass(DEVIATION)
            .map { e ->
                Deviation(
                    e.getElementsByClass(ROUTE).text()
                        .replace(" ", "")
                        .replace(".", "")
                        .split(","),
                    e.getElementsByClass(HEADING).text(),
                    e.getElementsByClass(CONTENT).text(),
                    true
                )
            }.toList()
    }


}