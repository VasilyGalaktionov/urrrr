package ru.test.ursip.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.test.ursip.entity.Test

interface TestRepository : JpaRepository<Test, Long>