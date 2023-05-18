package com.jorgenlohne.deviationTracker.service

import com.jorgenlohne.deviationTracker.model.Deviation
import org.springframework.stereotype.Service

@Service
class DeviationService(val scraperService: SkyssScraperService) {

    fun getAllDeviations(): Iterable<Deviation> {
        return scraperService.getCurrentDeviations()
    }

}