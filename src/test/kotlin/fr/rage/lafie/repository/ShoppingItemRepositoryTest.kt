package fr.rage.lafie.repository

import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import fr.rage.lafie.data.database.dao.ShoppingItemDao
import fr.rage.lafie.data.database.entity.ShoppingItemEntity
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
class ShoppingItemRepositoryTest {

    @InjectMockKs
    private lateinit var repository: ShoppingItemRepository

    @MockK
    private lateinit var dao: ShoppingItemDao

    @Test
    fun `create an item in database and except a correct behavior`() = runTest {
        // Init
        val shoppingItemEntity = mockk<ShoppingItemEntity>()

        // Mock
        coEvery { dao.create(any(), any(), any(), any()) } returns shoppingItemEntity

        // Exec
        val result = repository.create(
            label = "label",
            count = 0f,
            unit = "unit",
            shoppingList = mockk<ShoppingListEntity>()
        )

        // Assert
        assertThat(result).isNotNull().isEqualTo(shoppingItemEntity)

        // Verify
        coVerify { dao.create(any(), any(), any(), any()) }
    }

    @Test
    fun `Retrieve an item by its ID and expect a correct behavior`() = runTest {
        // Init
        val shoppingItemEntity = mockk<ShoppingItemEntity>()

        // Mock
        coEvery { dao.getById(any()) } returns shoppingItemEntity

        // Exec
        val result = repository.getById(UUID.randomUUID())

        // Assert
        assertThat(result).isNotNull().isEqualTo(shoppingItemEntity)

        // Verify
        coVerify { dao.getById(any()) }
    }

    @Test
    fun `Get shopping items by their associated shopping list id and expect them to be returned`() = runTest {
        // Init
        val firstShoppingItemEntity = mockk<ShoppingItemEntity>()
        val secondShoppingItemEntity = mockk<ShoppingItemEntity>()
        val shoppingItems = listOf(firstShoppingItemEntity, secondShoppingItemEntity)

        // Mock
        coEvery { dao.getByShoppingListId(any()) } returns shoppingItems

        // Exec
        val result = repository.getByShoppingListId(UUID.randomUUID())

        // Assert
        assertThat(result).isNotNull().containsExactly(
            firstShoppingItemEntity,
            secondShoppingItemEntity,
        )

        // Verify
        coVerify { dao.getByShoppingListId(any()) }
    }

    @Test
    fun `update an item and check if the changes has been correctly proceeded`() = runTest {
        // Init
        val shoppingItem = mockk<ShoppingItemEntity>()
        val shoppingList = mockk<ShoppingListEntity>()

        // Mock
        coEvery { dao.update(any(), any(), any(), any()) } returns shoppingItem

        // Exec
        val result = repository.update(UUID.randomUUID(), "", 0f, "")

        // Assert
        assertThat(result).isNotNull().isEqualTo(shoppingItem)

        // Verify
        coVerify { dao.update(any(), any(), any(), any()) }
    }

    @Test
    fun `delete an existing item and expect it to be deleted`() = runTest {
        // Mock
        coEvery { dao.delete(any()) } returns Unit

        // Exec
        val result = repository.delete(UUID.randomUUID())

        // Assert
        assertThat(result).isNotNull()

        // Verify
        coVerify { dao.delete(any()) }
    }

    @Test
    fun `delete an nonexistent item and expect the result to be null`() = runTest {
        // Mock
        coEvery { dao.delete(any()) } returns null

        // Exec
        val result = repository.delete(UUID.randomUUID())

        // Assert
        assertThat(result).isNull()

        // Verify
        coVerify { dao.delete(any()) }
    }
}