package cl.mmoscoso.practice.entity

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithProducts(
    @Embedded val user: User,
    @Relation(
        parentColumn = "uid",
        entityColumn = "user_id"
    )
    val products: List<Product>
)