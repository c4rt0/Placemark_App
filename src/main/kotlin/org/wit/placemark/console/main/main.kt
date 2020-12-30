package org.wit.placemark.console.main

import mu.KotlinLogging
import org.wit.placemark.console.controllers.PlacemarkController
import org.wit.placemark.console.models.PlacemarkMemStore
import org.wit.placemark.console.models.PlacemarkModel

internal val logger = KotlinLogging.logger {}

val placemarks = PlacemarkMemStore()

fun main(args: Array<String>) {
    PlacemarkController().start()
}

fun search(id: Long) : PlacemarkModel? {
    var foundPlacemark = placemarks.findOne(id)
    return foundPlacemark
}