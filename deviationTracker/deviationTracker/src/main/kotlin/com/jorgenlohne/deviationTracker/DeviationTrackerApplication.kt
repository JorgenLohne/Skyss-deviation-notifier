package com.jorgenlohne.deviationTracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DeviationTrackerApplication

fun main(args: Array<String>) {
	runApplication<DeviationTrackerApplication>(*args)
}
