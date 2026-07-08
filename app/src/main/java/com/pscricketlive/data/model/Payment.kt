package com.pscricketlive.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * An internal payment record — tracking only, never a real money transfer. Per SCOPE.md's
 * hard constraints: no payment gateway, no transaction fees. Someone marks this settled
 * manually once money has actually changed hands outside the app.
 */
@Entity(tableName = "payments")
data class Payment(
    @PrimaryKey
    val id: String,                // UUID, generated at creation time
    val matchId: String? = null,   // optional link — not every payment ties to one match
    val fromUserId: String,        // who owes
    val toUserId: String,          // who is owed
    val amount: Double,
    val description: String,       // e.g. "Ground booking fee", "Jersey order"
    val status: PaymentStatus = PaymentStatus.PENDING,
    val createdAt: Long,           // epoch millis
    val settledAt: Long? = null    // null until marked settled
)

enum class PaymentStatus {
    PENDING,
    SETTLED
}