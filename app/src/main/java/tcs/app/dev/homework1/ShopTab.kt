package tcs.app.dev.homework1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import tcs.app.dev.homework1.data.Cart
import tcs.app.dev.homework1.data.Item
import tcs.app.dev.homework1.data.MockData

//Component rendering shop items under Shoptab, one row for each item consisting of 1.image 2.Name+price 3.+ button
@Composable
fun ShopTabContent(items: List<Item>, cart: Cart, onAdd: (Item) -> Unit) {
    LazyColumn {
        items(items, key = { it.hashCode() })
        //one row for each item
        { item ->
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

        var expanded by remember { mutableStateOf(false) }
        var selectedAmount by remember { mutableStateOf(1u) }

        //image
        Image(
            painter = painterResource(MockData.getImage(item)),
            contentDescription = stringResource(MockData.getName(item)),
            modifier = Modifier.size(64.dp)
        )
        Spacer(Modifier.width(16.dp))

        //name+price
        Column(Modifier.weight(1f)) {
            Text(stringResource(MockData.getName(item)))
            Text(MockData.ExampleShop.prices[item].toString())
        }

        // select quantity with dropdown menu
        Box {
            Button(onClick = { expanded = true }) {
                Text(selectedAmount.toString())
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                (1..10).forEach { amount ->
                    DropdownMenuItem(
                        text = { Text(amount.toString()) },
                        onClick = {
                            selectedAmount = amount.toUInt()
                            expanded = false
                        }
                    )
                }
            }
        }


        Spacer(Modifier.width(8.dp))
        Button(onClick = onAdd) { Text("Add to cart") }
    }
}
