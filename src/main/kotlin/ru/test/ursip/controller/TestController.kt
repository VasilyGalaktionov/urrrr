package ru.test.ursip.controller

import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import ru.test.ursip.entity.TestDto
import ru.test.ursip.exception.TestNotFoundException
import ru.test.ursip.service.TestService

@RestController
@RequestMapping("/test")
class TestController(private val testService: TestService) {

    @PostMapping
    fun create(@RequestBody testDto: TestDto) = testService.create(testDto)

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): TestDto = try {
        testService.findById(id)
    } catch (e: TestNotFoundException) {
        reportNotFoundResponseStatus("Not found record with id: $id")
    }

    @GetMapping("/all")
    fun findAll(): List<TestDto> = testService.findAll()

    @DeleteMapping("/{id}")
    fun removeById(@PathVariable id: Long) = testService.removeById(id)

    @PatchMapping("/{id}/{testName}")
    fun update(@PathVariable id: Long, @PathVariable testName: String) = try {
        testService.update(id, testName)
    } catch (e: TestNotFoundException) {
        reportNotFoundResponseStatus("Not found record with id: $id to be patched")
    }

    companion object {
        private val logger = KotlinLogging.logger {}
        private fun reportNotFoundResponseStatus(reason: String): Nothing =
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
                .also { logger.info { reason } }
    }
}