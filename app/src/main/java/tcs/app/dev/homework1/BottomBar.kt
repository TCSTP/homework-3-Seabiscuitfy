package tcs.app.dev.homework1

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tcs.app.dev.homework1.data.Euro

@Composable
fun ShopBottomBar(selected: ShopTab, onSelect: (ShopTab) -> Unit) {
    NavigationBar {
        NavigationBarItem(
            selected = selected == ShopTab.SHOP,
            onClick = { onSelect(ShopTab.SHOP) },
            icon = { Icon(Icons.Default.Store, contentDescription = null) },
            label = { Text("Shop") }
        )
        NavigationBarItem(
            selected = selected == ShopTab.DISCOUNTS,
            onClick = { onSelect(ShopTab.DISCOUNTS) },
            icon = { Icon(Icons.Default.LocalOffer, contentDescription = null) },
            label = { Text("Discounts") }
        )
    }
}

@Composable
fun CartBottomBar(totalPrice: Euro, canPay: Boolean, onPay: () -> Unit) {
    BottomAppBar {
        Text("Total: $totalPrice", modifier = Modifier.padding(16.dp))
        Spacer(Modifier.weight(1f))
        Button(onClick = onPay, enabled = canPay, modifier = Modifier.padding(16.dp)) {
            Text("Pay")
        }
    }
}
