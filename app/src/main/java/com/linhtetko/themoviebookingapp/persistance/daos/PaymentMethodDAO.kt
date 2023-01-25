package com.linhtetko.themoviebookingapp.persistance.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.PaymentMethodVO

@Dao
interface PaymentMethodDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPaymentMethods(paymentMethods: List<PaymentMethodVO>)

    @Query("SELECT * FROM payment_methods")
    fun getPaymentMethods(): List<PaymentMethodVO>

    @Query("SELECT * FROM payment_methods WHERE id = :paymentMethodId")
    fun getPaymentMethod(paymentMethodId: Int): PaymentMethodVO?
}