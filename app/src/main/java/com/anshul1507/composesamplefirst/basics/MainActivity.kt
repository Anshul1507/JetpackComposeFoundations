package com.anshul1507.composesamplefirst.basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.anshul1507.composesamplefirst.R
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        val viewmodel: MainViewModel = MainViewModel()

        setContent {
            //Phase 1
            //StatusBanner(isOnline = true) //Day 1
            //UserProfileCard() //Day 2
            //CounterScreenContainer() //Day 3
            //TransactionHistoryScreen() //Day 4

            //Phase 2
            //HomeScreenWithViewModel(viewmodel) //Day 5
            //AppNavigation() //Day 6
            //ThemeDemoScreen() //Day 7
            // Hybrid Views (android -> compose || compose -> android views) //Day 8
            // ExpandableCard() //Day 9
            // CustomProgressRingScreen() //Day 10
            OptimizedDashboardScreen() //Day 11
        }
    }

    // Day 11
    @Composable
    fun OptimizedDashboardScreen() {
        val listState = rememberLazyListState()
        val isScrolledPastFirstFrame by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(100) { index ->
                    Text("Ledger Transaction Reference #$index",
                        modifier = Modifier.padding(16.dp),
                        fontSize = 16.sp
                        )
                }
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(24.dp)
                    .graphicsLayer {
                        alpha = if (isScrolledPastFirstFrame) 1.0f else 0.0f
                        translationY = if(isScrolledPastFirstFrame) 0f else 100f
                    }
            ) {
                Button(onClick = {  }) {
                    Text("Scroll to Top")
                }
            }
        }
    }

    //Day 10
    @Composable
    fun CustomProgressRingScreen() {
        var targetProgress by remember { mutableStateOf(0f) }
        val animatedProgress by animateFloatAsState(
            targetValue = targetProgress,
            animationSpec = tween(durationMillis = 1000),
            label = "ProgressAnimation"
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Canvas(
                modifier = Modifier
                    .size(200.dp)
                    .padding(16.dp)
            ) {
                val componentSize = size
                val centerCoordinates = Offset(x = componentSize.width / 2f, y = componentSize.height/2f)
                val radius = componentSize.width / 2f
                val strokeWidth = 16.dp.toPx()

                drawCircle(
                    color = Color(0xFFE0E0E0),
                    radius = radius - (strokeWidth/2),
                    center = centerCoordinates,
                    style = Stroke(width = strokeWidth)
                )

                drawArc(
                    color = Color(0xFF1E88E5),
                    startAngle = -90f,
                    sweepAngle = 360f * animatedProgress,
                    useCenter = false,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(

            ) {
                Row (
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = { targetProgress = 0.25f }) { Text("25%") }
                    Button(onClick = { targetProgress = 0.5f }) { Text("50%") }
                }

                Row (
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = { targetProgress = 0.75f }) { Text("75%") }
                    Button(onClick = { targetProgress = 1.0f }) { Text("100%") }
                }
            }

        }
    }

    //Day 9
    @Composable
    fun ExpandableCard() {
        var isExpanded by remember { mutableStateOf(false) }
        val rotationAngle by animateFloatAsState(
            targetValue = if (isExpanded) 180f else 0f,
            label = "ArrowRotation"
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { isExpanded = !isExpanded },
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFAFAFA))
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .animateContentSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Transaction Order Details",
                        style = MaterialTheme.typography.titleMedium)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Toggle Arrow",
                        modifier = Modifier.rotate(rotationAngle))
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "This section holds multi-line ledger logs or nested B2B distribution metrics. When closed, it safely clips overflowing rows to clean up layout hierarchies, expanding on user interaction parameters seamlessly.",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = if(isExpanded) Int.MAX_VALUE else 1,
                    overflow = TextOverflow.Ellipsis)
            }
        }
    }


    //Day 7
    private val LightColors = lightColorScheme(
        primary = Color(0xFF006644), // Emerald green style for enterprise/B2B
        background = Color(0xFFF4F6F4),
        surface = Color(0xFFFFFFFF)
    )

    private val DarkColors = darkColorScheme(
        primary = Color(0xFF66C299),
        background = Color(0xFF121212),
        surface = Color(0xFF1E1E1E)
    )

    @Composable
    fun CustomAppTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit
    ) {
        val colors = if (darkTheme) DarkColors else LightColors

        MaterialTheme(colorScheme = colors, content = content)
    }

    @Composable
    fun ThemeDemoScreen() {
        var isDarkMode by remember { mutableStateOf(false) }
        CustomAppTheme(darkTheme = isDarkMode) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                "Adaptive Card Layout",
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                "This text color adjusts dynamically based on the current active theme color schema definition.",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(onClick = {isDarkMode = !isDarkMode}) {
                        Text(if (isDarkMode) "Switch to Light Mode" else "Switch to Dark Mode")
                    }
                }
            }
        }
    }

    //Day 6
    @Serializable
    object ProductList
    @Serializable
    data class ProductDetails(val productId: String, val price: Double)

    @Composable
    fun AppNavigation() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = ProductList
        ) {
            //Product List Screen Destination
            composable<ProductList> {
                ProductListingScreen(
                    onProductClick = { id, cost ->
                        navController.navigate(ProductDetails(id, cost)) {
                            launchSingleTop = true
                        }
                    }
                )
            }

            //Product Details Screen Destination
            composable<ProductDetails> { backStackEntry ->
                val argDetails = backStackEntry.toRoute<ProductDetails>()
                ProductDetailsScreen(argDetails.productId, argDetails.price, onBack = {
                    navController.popBackStack()
                })
            }
        }
    }

    @Composable
    fun ProductListingScreen(onProductClick: (String, Double) -> Unit) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Product Inventory", fontSize = 22.sp, modifier = Modifier.padding(16.dp))
            Button(
                onClick = { onProductClick("PROD-9981", 1499.50) }
            ) {
                Text("View Product: PROD-9981")
            }
        }
    }

    @Composable
    fun ProductDetailsScreen(productId: String, price: Double, onBack: () -> Unit) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Product ID: $productId", fontSize = 20.sp)
            Text("Price: Rs.$price", fontSize = 18.sp, modifier = Modifier.padding(8.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onBack) {
                Text("Go Back")
            }

        }
    }

    //Day 5
    @Composable
    fun HomeScreenWithViewModel(viewModel: MainViewModel) {
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        LaunchedEffect(key1 = true) {
            viewModel.loadData()
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (val currentState = state) {
                is HomeUiState.Loading -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Fetching Configurations....")
                    }
                }

                is HomeUiState.Success -> {
                    Text(currentState.data)
                }

                is HomeUiState.Failure -> {
                    Text(currentState.errorMessage)
                }
            }
        }
    }

    //Day 4
    data class Transaction(
        val id: String,
        val title: String,
        val amount: String,
        val isCredit: Boolean,
        val date: String
    )

    @Composable
    fun TransactionHistoryScreen() {
        // Mock data list
        val transactions = listOf(
            Transaction("1", "Prepaid Tag Top-up", "₹2,500.00", true, "Today, 11:30 AM"),
            Transaction("2", "Parking Fee - Sector 29", "₹60.00", false, "Today, 09:15 AM"),
            Transaction("3", "Fuel Purchase", "₹4,200.00", false, "Yesterday"),
            Transaction("4", "Refund - Corporate Pass", "Word", true, "May 30, 2026"),
            Transaction("5", "SBI Wallet Recharge", "₹1,000.00", true, "May 28, 2026"),
            Transaction("6", "Prepaid Tag Top-up", "₹2,500.00", true, "Today, 11:30 AM"),
            Transaction("7", "Parking Fee - Sector 29", "₹60.00", false, "Today, 09:15 AM"),
            Transaction("8", "Fuel Purchase", "₹4,200.00", false, "Yesterday"),
            Transaction("9", "Refund - Corporate Pass", "Word", true, "May 30, 2026"),
            Transaction("10", "SBI Wallet Recharge", "₹1,000.00", true, "May 28, 2026"),
            Transaction("11", "Prepaid Tag Top-up", "₹2,500.00", true, "Today, 11:30 AM"),
            Transaction("12", "Parking Fee - Sector 29", "₹60.00", false, "Today, 09:15 AM"),
            Transaction("13", "Fuel Purchase", "₹4,200.00", false, "Yesterday"),
            Transaction("14", "Refund - Corporate Pass", "Word", true, "May 30, 2026"),
            Transaction("15", "SBI Wallet Recharge", "₹1,000.00", true, "May 28, 2026")
        )

        Column(modifier = Modifier.fillMaxSize()) {
            //Header
            Text(
                "Recent Activity",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(20.dp)
            )
            //List
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(transactions, key = { it.id }) { txn ->
                    TransactionRow(txn)
                }
            }
        }
    }

    @Composable
    fun TransactionRow(item: Transaction) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(item.title, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(item.date, color = Color.Gray, fontSize = 12.sp)
                }

                Text(
                    (if (item.isCredit) "+" else "-") + item.amount,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (item.isCredit) Color(0xFF2E7D32) else Color(0xFFC62828)
                )
            }
        }
    }

    //Day 3
    @Composable
    fun CounterScreenContainer() {
        var counterState by rememberSaveable { mutableStateOf(0) }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CounterContent(
                currentCount = counterState,
                onIncrementClicked = { counterState++ },
                onDecrementClicked = {
                    if (counterState > 0) counterState--
                }
            )
        }
    }

    @Composable
    fun CounterContent(
        currentCount: Int,
        onIncrementClicked: () -> Unit,
        onDecrementClicked: () -> Unit
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                "Click Count: $currentCount",
                fontSize = 24.sp,
                modifier = Modifier.padding(24.dp)
            )
            Row {
                Button(onClick = onDecrementClicked) {
                    Text("Decrease")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = onIncrementClicked) {
                    Text("Increase")
                }
            }
        }

    }

    // Day 2
    @Composable
    fun UserProfileCard() {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "AG", color = Color.White, fontWeight = FontWeight.Bold)
                }

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Anshul Gupta",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "Android Developer",
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFF2196F3))
                        .clickable {}
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(text = "View", color = Color.White, fontSize = 12.sp)
                }
            }
        }
    }

    // Day 1
    @Composable
    fun StatusBanner(isOnline: Boolean) {

        val textValue = if (isOnline) "Connected" else "Disconnected"
        val textColor = if (isOnline) Color.Green else Color.Red
        val bgColor = if (isOnline) Color.Blue else Color.Gray

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .safeDrawingPadding()
                .background(bgColor)
                .padding(16.dp),
            contentAlignment = Alignment.Center

        ) {
            Text(text = textValue, color = textColor)
        }
    }

    //Previous Code
    data class Message(val author: String, val body: String)

    @Composable
    fun MessageCard(msg: Message) {
        Row {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "sample image"
            )
            Column {
                Text(text = msg.body)
                Text(text = msg.author)
            }
        }
    }

    @Preview
    @Composable
    fun PreviewMessageCard() {
        MessageCard(Message("author", "this is a sample body"))
    }
}