package fr.rage.lafie.repository

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import fr.rage.lafie.data.database.dao.ShoppingListDao
import fr.rage.lafie.data.database.entity.ShoppingListEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class ShoppingListRepositoryTest {

    @InjectMockKs
    private lateinit var repository: ShoppingListRepository

    @MockK
    private lateinit var dao: ShoppingListDao

    @Test
    fun `Get a shopping list by its ID and expect it to exists`() = runTest {
        // Init
        val shoppingList = mockk<ShoppingListEntity>()

        // Mock
        coEvery { dao.getById(any()) } returns shoppingList

        // Exec
        val result = repository.getById(UUID.randomUUID())

        // Assert
        assertThat(result).isNotNull().isEqualTo(shoppingList)

        // Verify
        coVerify { dao.getById(any()) }
    }


    @Test
    fun `Get a shopping list by its ID and expect it to be not found`() = runTest {
        // Mock
        coEvery { dao.getById(any()) } returns null

        // Exec
        val result = repository.getById(UUID.randomUUID())

        // Assert
        assertThat(result).isNull()

        // Verify
        coVerify { dao.getById(any()) }
    }

    @Test
    fun `Create a shopping list and expect it to be returned`() = runTest {
        // Init
        val shoppingList = mockk<ShoppingListEntity>()

        // Mock
        coEvery { dao.create() } returns shoppingList

        // Exec
        val result = repository.create()

        // Assert
        assertThat(result).isNotNull().isEqualTo(shoppingList)
    }
}