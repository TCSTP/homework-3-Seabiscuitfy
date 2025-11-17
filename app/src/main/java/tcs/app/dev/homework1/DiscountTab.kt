package tcs.app.dev.homework1

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import tcs.app.dev.homework1.data.Cart
import tcs.app.dev.homework1.data.Discount
import tcs.app.dev.homework1.data.MockData

@Composable
fun DiscountTabContent(discounts: List<Discount>, cart: Cart, onAdd: (Discount) -> Unit) {
    LazyColumn {
        items(discounts, key = { it.hashCode() }) { discount ->
            val added = cart.discounts.contains(discount)
            DiscountRow(discount, added = added, onAdd = { if (!added) onAdd(discount) })
        }
    }
}

@Composable
fun DiscountRow(discount: Discount, added: Boolean, onAdd: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = when (discount) {
                is Discount.Fixed -> Icons.Default.AttachMoney
                is Discount.Percentage -> Icons.Default.Percent
                is Discount.Bundle -> Icons.Default.LocalOffer
            },
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = when (discount) {
                is Discount.Fixed -> "Fixed ${discount.amount}"
                is Discount.Percentage -> "${discount.value}% Off"
                is Discount.Bundle -> "Buy ${discount.amountItemsGet} ${stringResource(MockData.getName(discount.item))}, pay ${discount.amountItemsPay}"
            },
            modifier = Modifier.weight(1f)
        )
        Button(onClick = onAdd, enabled = !added) { Text("Add") }
    }
}
