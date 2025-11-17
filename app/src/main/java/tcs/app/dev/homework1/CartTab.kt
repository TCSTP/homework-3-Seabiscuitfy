package tcs.app.dev.homework1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import tcs.app.dev.homework1.data.Cart
import tcs.app.dev.homework1.data.Discount
import tcs.app.dev.homework1.data.Item
import tcs.app.dev.homework1.data.MockData
import tcs.app.dev.homework1.data.times

//component for cart: rendering all items then all discounts
@Composable
fun CartTabContent(
    cart: Cart,
    onInc: (Item) -> Unit,
    onDec: (Item) -> Unit,
    onRemoveDiscount: (Discount) -> Unit
) {
    LazyColumn {
        items(cart.items.entries.toList(), key = { it.key.hashCode() }) { (item, amount) ->
            CartItemRow(item = item, amount = amount, onInc = { onInc(item) }, onDec = { onDec(item) })
        }
        items(cart.discounts, key = { it.hashCode() }) { discount ->
            DiscountCartRow(discount = discount, onRemove = { onRemoveDiscount(discount) })
        }
    }
}

@Composable
fun CartItemRow(item: Item, amount: UInt, onInc: () -> Unit, onDec: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(MockData.getImage(item)),
            contentDescription = stringResource(MockData.getName(item)),
            modifier = Modifier.size(64.dp)
        )
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(1f)) {
            Text(stringResource(MockData.getName(item)))
            val price = MockData.ExampleShop.prices[item]!! * amount
            Text(price.toString())
        }
        Row {
            IconButton(onClick = onDec) { Icon(Icons.Default.Remove, contentDescription = "-") }
            Text("$amount", modifier = Modifier.align(Alignment.CenterVertically))
            IconButton(onClick = onInc) { Icon(Icons.Default.Add, contentDescription = "+") }
        }
    }
}

@Composable
fun DiscountCartRow(discount: Discount, onRemove: () -> Unit) {
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
        IconButton(onClick = onRemove) {
            Icon(Icons.Default.Delete, contentDescription = "Remove")
        }
    }
}
