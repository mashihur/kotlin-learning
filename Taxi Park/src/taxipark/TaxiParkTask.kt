package taxipark
/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> = allDrivers.filter { it !in (trips.map { it.driver }) } .toSet()
//{
//    var drivers = mutableSetOf<Driver>()
//    drivers.addAll(allDrivers)
//    for (trip in trips) {
//        if (allDrivers.contains(trip.driver)) drivers.remove(trip.driver)
//    }
//    return drivers
//}

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> = allPassengers.filter { passenger -> trips.count {passenger in it.passengers} >= minTrips }.toSet()
//{
//    var faithfulPassenger = mutableSetOf<Passenger>()
//    var count : Int
//    for (passenger in allPassengers) {
//        count = 0
//        for(trip in trips) {
//            if (trip.passengers.contains(passenger)) {
//               count++
//            }
//            if (count >= minTrips) {
//                faithfulPassenger.add(passenger)
//                break
//            }
//        }
//    }
//
//    return faithfulPassenger
//}

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> = allPassengers.filter {passenger ->  trips.count {passenger in it.passengers && it.driver == driver } > 1}.toSet()
//{
//    var frequentPassenger = mutableSetOf<Passenger>()
//    var passengerList = mutableListOf<Passenger>()
//    for (trip in trips) {
//        if (trip.driver.equals(driver)) {
//            for (passenger in trip.passengers) {
//                if (!passengerList.contains(passenger)) {
//                    passengerList.add(passenger)
//                } else {
//                    frequentPassenger.add(passenger)
//                }
//            }
//        }
//    }
//
//    return frequentPassenger
//}

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> = allPassengers.filter {passenger ->  trips.count {passenger in it.passengers && it.discount != null } > trips.count {passenger in it.passengers} / 2}.toSet()
//{
//    val smartPassengers = mutableSetOf<Passenger>()
//    val discPassMap = mutableMapOf<Passenger, Int>()
//    val nonDiscPassMap = mutableMapOf<Passenger, Int>()
//    for (trip in trips) {
//        if (trip.discount == null) {
//            for (passenger in trip.passengers) {
//                nonDiscPassMap[passenger] = (nonDiscPassMap[passenger] ?: 0) + 1
//            }
//        } else {
//            for (passenger in trip.passengers) {
//                discPassMap[passenger] = (discPassMap[passenger] ?: 0) + 1
//            }
//        }
//    }
//    for (passenger in allPassengers) {
//        if (discPassMap.containsKey(passenger)) {
//            if ((discPassMap[passenger] ?: 0) > (nonDiscPassMap[passenger] ?: 0)) {
//                smartPassengers.add(passenger)
//            }
//        }
//    }
//    return smartPassengers
//}

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    var range : IntRange? = null
    var map = mutableMapOf<Int, Int>()
    for (trip in trips) {
        if (trip.duration != null) {
            var temp = (trip.duration / 10)
            map[temp] = (map[temp] ?: 0) + 1
            map.toSortedMap()
        }
    }
    if (map.size > 0) {
        var maxEntry = map.maxBy { it.value }
        var firstRange = (maxEntry?.key ?: 0) * 10
        range = firstRange..(firstRange + 9)
    }

    return range
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    var map = mutableMapOf<String, Double>()
    var check = false
    var totalCost = 0.0
    for (trip in trips) {
        var temp = (map[trip.driver.name] ?: 0.0) + trip.cost
        map[trip.driver.name] = temp
        totalCost += trip.cost
    }
    var sortedMap = map.toList().sortedByDescending { it.second }.toMap()
    var counter = 0
    var cost = 0.0
    for (entry in sortedMap) {
        counter++
        cost += entry.value
        if ((counter.toDouble() / allDrivers.size.toDouble()) <= 0.2) {
            if ((cost / totalCost) >= 0.8) {
                check = true
                break
            }
        }

    }

    return check
}