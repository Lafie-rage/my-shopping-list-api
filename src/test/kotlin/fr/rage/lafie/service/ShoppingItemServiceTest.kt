package fr.rage.lafie.service

import fr.rage.lafie.data.database.entity.ShoppingItemEntity
import fr.rage.lafie.data.database.entity.ShoppingListEntity
import fr.rage.lafie.dto.request.ShoppingItemToCreate
import fr.rage.lafie.dto.response.ShoppingItem
import fr.rage.lafie.mapper.toDto
import fr.rage.lafie.repository.ShoppingItemRepository
import fr.rage.lafie.repository.ShoppingListRepository
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class ShoppingItemServiceTest {

    @InjectMockKs
    private lateinit var service: ShoppingItemService

    @MockK
    private lateinit var repository: ShoppingItemRepository

    @MockK
    private lateinit var shoppingListRepository: ShoppingListRepository

    @Test
    fun `create a new item on a shopping list and expect it to be created`() = runTest {
        // Init
        val shoppingItemToCreate = mockk<ShoppingItemToCreate>()
        val shoppingList = mockk<ShoppingListEntity>()
        val shoppingItemEntity = mockk<ShoppingItemEntity>()
        val shoppingItem = mockk<ShoppingItem>()
        mockkStatic(ShoppingItemEntity::toDto)

        // Mock
        coEvery { shoppingListRepository.getByIdOrCreate(any()) } returns shoppingList
        every { shoppingItemToCreate.label } returns "label"
        every { shoppingItemToCreate.count } returns 0f
        every { shoppingItemToCreate.unit } returns "unit"
        coEvery { repository.create(any(), any(), any(), any()) } returns shoppingItemEntity
        every { shoppingItemEntity.toDto() } returns shoppingItem


        // Exec
        val result = service.createItemOnListShoppingList(UUID.randomUUID(), shoppingItemToCreate)

        // Assert
        assertEquals(shoppingItem, result)

        // Verify
        coVerify { shoppingListRepository.getByIdOrCreate(any()) }
        verify { shoppingItemToCreate.label }
        verify { shoppingItemToCreate.count }
        verify { shoppingItemToCreate.unit }
        coVerify { repository.create(any(), any(), any(), any()) }
        verify { shoppingItemEntity.toDto() }
    }

    @Test
    fun getById() = runTest {
        // Init
        val shoppingItemEntity = mockk<ShoppingItemEntity>()
        val shoppingItem = mockk<ShoppingItem>()
        mockkStatic(ShoppingItemEntity::toDto)

        // Mock
        coEvery { repository.getById(any()) } returns shoppingItemEntity
        every { shoppingItemEntity.toDto() } returns shoppingItem

        // Exec
        val result = service.getById(UUID.randomUUID())

        // Assert
        assertEquals(shoppingItem, result)

        // Verify
        coVerify { repository.getById(any()) }
        verify { shoppingItemEntity.toDto() }
    }
}