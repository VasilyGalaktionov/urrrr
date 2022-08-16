package ru.test.ursip

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UrsipApplication

fun main(args: Array<String>) {
	runApplication<UrsipApplication>(*args)
}
