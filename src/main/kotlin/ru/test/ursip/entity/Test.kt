package ru.test.ursip.entity

import javax.persistence.*

@Entity
@Table(schema = "ursip", name = "test")
class Test(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Column(name = "test_name")
    var testName: String = "test"
) {
    override fun toString(): String {
        return "Test(id=$id, testName='$testName')"
    }
}

data class TestDto(
    val testName: String
)

fun Test.toDto() = TestDto(testName = testName)
fun TestDto.toEntity() = Test(testName = testName)
fun List<Test>.toDtos() = map { it.toDto() }
