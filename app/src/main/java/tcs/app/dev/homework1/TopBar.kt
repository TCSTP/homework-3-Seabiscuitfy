package tcs.app.dev.homework1

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import tcs.app.dev.homework1.data.Cart

@Composable
fun ShopTopBar(
    selectedTab: ShopTab,
    cart: Cart,
    onCartClick: () -> Unit,
    onBack: () -> Unit
) {
    TopAppBar(
        title = { Text(if (selectedTab == ShopTab.CART) "Cart" else cart.shop.javaClass.simpleName) },
        navigationIcon = {
            if (selectedTab == ShopTab.CART) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        },
        actions = {
            if (selectedTab != ShopTab.CART) {
                IconButton(onClick = onCartClick, enabled = cart.itemCount > 0u) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                }
            }
        }
    )
}
