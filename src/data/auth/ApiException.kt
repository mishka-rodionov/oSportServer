package data.auth

class ApiException(val apiMessage: String, val internalMessage: String = "") : Exception(internalMessage)
