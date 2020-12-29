package org.wit.placemark.console.main

import mu.KotlinLogging
import org.wit.placemark.console.models.PlacemarkModel

private val logger = KotlinLogging.logger {}

//var placemark = PlacemarkModel()
val placemarks = ArrayList<PlacemarkModel>()

fun main(args: Array<String>) {
    logger.info { "Launching Placemark Console App" }
    println("Placemark Kotlin App Version 1.0")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addPlacemark()
            2 -> updatePlacemark()
            3 -> listPlacemarks()
            4 -> searchPlacemark()
            -99 -> dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Placemark Console App" }
}

fun menu() : Int {

    var option : Int
    var input: String?

    println(" === MAIN MENU === ")
    println(" 1. Add Placemark")
    println(" 2. Update Placemark")
    println(" 3. List All Placemarks")
    println(" 4. Search Placemark by id")
    // println(" -99. Place dummy data in the array")   // This option is purposely hidden - developer's piece of mind :)
    println("-1. Exit")
    println()
    print("Enter Option : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}

fun addPlacemark(){
    var aPlacemark = PlacemarkModel()
    println("Add Placemark")
    println()
    print("Enter a Title : ")
    aPlacemark.title = readLine()!!
    print("Enter a Description : ")
    aPlacemark.description = readLine()!!
    // Checking if there's no empty entries:
    if (aPlacemark.title.isNotEmpty() && aPlacemark.description.isNotEmpty()) {
        // adding +1 to id
        aPlacemark.id = placemarks.size.toLong()
        aPlacemark.id++
        placemarks.add(aPlacemark.copy())
        logger.info("Placemark Added : [ $aPlacemark ]")
    }
    else
        logger.info("Placemark Not Added")
}

fun updatePlacemark() {

//    println("Update Placemark")
//    println()
//    var placemark = PlacemarkModel()
//    print("Enter a new Title for [ " + placemark.title + " ] : ")
//    placemark.title = readLine()!!
//    print("Enter a new Description for [ " + placemark.description + " ] : ")
//    placemark.description = readLine()!!
//    println("You updated [ " + placemark.title + " ] for title " +
//            "and [ " + placemark.description + " ] for description")

    println("Update Placemark")
    println()
    listPlacemarks()
    var searchId = getId()
    val aPlacemark = search(searchId)

    if(aPlacemark != null) {
        print("Enter a new Title for [ " + aPlacemark.title + " ] : ")
        aPlacemark.title = readLine()!!
        print("Enter a new Description for [ " + aPlacemark.description + " ] : ")
        aPlacemark.description = readLine()!!
        println(
            "You updated [ " + aPlacemark.title + " ] for title " +
                    "and [ " + aPlacemark.description + " ] for description"
        )
    }
    else
        println("Placemark Not Updated...")
}


fun listPlacemarks() {
    println("List All Placemarks")
    println()
    placemarks.forEach { logger.info("${it}") }
    println()
}

fun searchPlacemark() {
    var searchId = getId()
    val aPlacemark = search(searchId)   // create Placemark object here and assign,
    // based on 'searchId' value passed to 'search()'
    if (aPlacemark != null)
        println("Placemark Details [ $aPlacemark ]")
    else
        println("Placemark not found.")
}

fun getId(): Long{
    var strId: String? // String to hold user input
    var searchId: Long // Long to hold converted id
    print("Enter id to search/Update : ")
    strId = readLine()!!
    searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId
}

fun search(id: Long) : PlacemarkModel? {
    var foundPlacemark: PlacemarkModel? = placemarks.find { p -> p.id == id }
    return foundPlacemark
}

fun dummyData() {
    placemarks.add(PlacemarkModel(1, "New York New York", "So Good They Named It Twice"))
    placemarks.add(PlacemarkModel(2, "Ring of Kerry", "Some place in the Kingdom"))
    placemarks.add(PlacemarkModel(3, "Waterford City", "You get great Blaas Here!!"))
}
