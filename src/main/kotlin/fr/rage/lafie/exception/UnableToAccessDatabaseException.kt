package fr.rage.lafie.exception

class UnableToAccessDatabaseException(
    reason: String,
) : RuntimeException(
    "Unable to establish a connection with the database : $reason"
)