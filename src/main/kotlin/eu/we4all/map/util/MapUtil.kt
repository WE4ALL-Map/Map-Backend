package eu.we4all.map.util

/**
 * Helper function so that `transform` doesn't require a Map.Entry<K, V> value as the argument.
 */
inline fun <K, V, R> Map<out K, V>.map(transform: (K, V) -> R): List<R> =
    this.map { entry -> transform(entry.key, entry.value) }
