package ru.test.ursip.service

import mu.KotlinLogging
import org.springframework.stereotype.Service
import ru.test.ursip.entity.TestDto
import ru.test.ursip.entity.toDto
import ru.test.ursip.entity.toDtos
import ru.test.ursip.entity.toEntity
import ru.test.ursip.exception.TestNotFoundException
import ru.test.ursip.repository.TestRepository

interface TestService {
    fun create(testDto: TestDto)
    fun findById(id: Long): TestDto
    fun findAll(): List<TestDto>
    fun removeById(id: Long)
    fun update(id: Long, testName: String): TestDto
}

@Service
class TestServiceImpl(private val testRepository: TestRepository) : TestService {

    override fun create(testDto: TestDto) {
        val test = testDto.toEntity()
        testRepository.save(test)
        logger.info { "Created record: $test" }
    }

    override fun findById(id: Long): TestDto {
        val test = testRepository.findById(id).orElseThrow { TestNotFoundException("No record with id: $id found") }
        logger.info { "Got record: $test" }
        return test.toDto()
    }

    override fun findAll(): List<TestDto> {
        val tests = testRepository.findAll()
        logger.info { "Got ${tests.size} records" }
        return tests.toDtos()
    }

    override fun removeById(id: Long) {
        testRepository.deleteById(id)
        logger.info { "Record with id: $id was removed from DB successfully" }
    }

    override fun update(id: Long, testName: String): TestDto {
        val test = testRepository.findById(id).orElseThrow { TestNotFoundException("No record with id: $id found") }
        test.testName = testName
        testRepository.save(test)
        logger.info { "Record with id: $id was updated successfully" }
        return test.toDto()
    }

    companion object {
        private val logger = KotlinLogging.logger {}
    }
}