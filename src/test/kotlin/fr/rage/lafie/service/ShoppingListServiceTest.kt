package fr.rage.lafie.service

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import fr.rage.lafie.data.database.entity.ShoppingListEntity
import fr.rage.lafie.dto.response.ShoppingList
import fr.rage.lafie.mapper.toDto
import fr.rage.lafie.repository.ShoppingListRepository
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class ShoppingListServiceTest {
    @InjectMockKs
    private lateinit var service: ShoppingListService

    @MockK
    private lateinit var repository: ShoppingListRepository

    @MockK
    private lateinit var shoppingItemService: ShoppingItemService


    @Test
    fun `Get a shopping list by its ID and expect it to be returned`() = runTest {
        // Init
        val shoppingListEntity = mockk<ShoppingListEntity>()
        val shoppingList = mockk<ShoppingList>()
        mockkStatic(ShoppingListEntity::toDto)

        // Mock
        coEvery { repository.getByIdOrCreate(any()) } returns shoppingListEntity
        coEvery { shoppingItemService.getByShoppingListId(any()) } returns listOf()
        every { shoppingListEntity.toDto(any()) } returns shoppingList

        // Exec
        val result = service.getByIdOrCreate(UUID.randomUUID())

        // Assert
        assertThat(result).isNotNull().isEqualTo(shoppingList)

        // Verify
        coVerify { repository.getByIdOrCreate(any()) }
        coVerify { shoppingItemService.getByShoppingListId(any()) }
        verify { shoppingListEntity.toDto(any()) }
    }
}