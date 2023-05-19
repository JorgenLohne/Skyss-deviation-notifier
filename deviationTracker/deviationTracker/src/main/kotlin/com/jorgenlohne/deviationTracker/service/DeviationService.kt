package com.jorgenlohne.deviationTracker.service

import com.jorgenlohne.deviationTracker.model.Deviation
import org.springframework.stereotype.Service

@Service
class DeviationService(val scraperService: SkyssScraperService) {

    fun getAllDeviations(): Iterable<Deviation> {
        return scraperService.getCurrentDeviations()
    }

    fun getDeviationsByRoute(queryRoutes: Iterable<String>): Iterable<Deviation> {
        val currentDeviations: Iterable<Deviation> = scraperService.getCurrentDeviations()

        return currentDeviations.filter { deviation: Deviation ->
            deviation.routes.any { route -> queryRoutes.contains(route) }
        }

    }

}