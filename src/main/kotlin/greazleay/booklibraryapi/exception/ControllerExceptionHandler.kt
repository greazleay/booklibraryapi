package greazleay.booklibraryapi.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@RestControllerAdvice
class ControllerExceptionHandler {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @ExceptionHandler(Exception::class)
    fun handleInternalError(e: Exception): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)

    @ExceptionHandler(Throwable::class)
    fun handleThrowable(e: Throwable): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalState(e: IllegalStateException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
}