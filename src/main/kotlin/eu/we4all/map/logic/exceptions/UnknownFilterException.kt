package eu.we4all.map.logic.exceptions

class UnknownFilterException(filterName: String) : Exception("Unknown filter: $filterName")