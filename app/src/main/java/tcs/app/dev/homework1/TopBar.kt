package tcs.app.dev.homework1

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack

import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import tcs.app.dev.homework1.data.Cart


//Component rendering the shop/cart icon as topbar for navigation.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopTopBar(
    selectedTab: ShopTab,
    cart: Cart,
    onCartClick: () -> Unit,
    onBack: () -> Unit
) {
    TopAppBar(
        title = { Text(if (selectedTab == ShopTab.CART) "Cart" else "Shop") },
        navigationIcon = {
            //"<-Back” if in cart
            if (selectedTab == ShopTab.CART) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        },
        actions = {
            if (selectedTab != ShopTab.CART) {
                //cart icon enabled if >0 items selected
                IconButton(onClick = onCartClick, enabled = cart.itemCount > 0u) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                }
            }
        }
    )
}
