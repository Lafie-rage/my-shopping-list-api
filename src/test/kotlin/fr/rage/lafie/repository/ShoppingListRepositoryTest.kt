package fr.rage.lafie.repository

import fr.rage.lafie.data.database.dao.ShoppingListDao
import fr.rage.lafie.data.database.entity.ShoppingListEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
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
    fun `Get an item by its ID and expect it to exists`() = runTest {
        // Init
        val shoppingList = mockk<ShoppingListEntity>()

        // Mock
        coEvery { dao.getById(any()) } returns shoppingList

        // Exec
        val result = repository.getByIdOrCreate(UUID.randomUUID())

        // Assert
        assertNotNull(result)
        assertEquals(shoppingList, result)

        // Verify
        coVerify { dao.getById(any()) }
    }


    @Test
    fun `Get an item by its ID and expect it to be not found then create it`() = runTest {
        // Init
        val shoppingList = mockk<ShoppingListEntity>()

        // Mock
        coEvery { dao.getById(any()) } returns null
        coEvery { dao.create() } returns shoppingList

        // Exec
        val result = repository.getByIdOrCreate(UUID.randomUUID())

        // Assert
        assertNotNull(result)
        assertEquals(shoppingList, result)

        // Verify
        coVerify { dao.getById(any()) }
        coVerify { dao.create() }
    }
}