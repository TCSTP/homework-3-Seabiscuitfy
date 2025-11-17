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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import tcs.app.dev.homework1.data.Cart
import tcs.app.dev.homework1.data.Item
import tcs.app.dev.homework1.data.MockData

@Composable
fun ShopTabContent(items: List<Item>, cart: Cart, onAdd: (Item) -> Unit) {
    LazyColumn {
        items(items, key = { it.hashCode() }) { item ->
            ShopItemRow(item = item, onAdd = { onAdd(item) })
        }
    }
}

@Composable
fun ShopItemRow(item: Item, onAdd: () -> Unit) {
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
            Text(MockData.ExampleShop.prices[item].toString())
        }
        Button(onClick = onAdd) { Text("+") }
    }
}
