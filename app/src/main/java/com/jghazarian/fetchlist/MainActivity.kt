package com.jghazarian.fetchlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jghazarian.fetchlist.data.model.Item
import com.jghazarian.fetchlist.ui.theme.FetchListTheme
import com.jghazarian.fetchlist.ui.theme.Typography

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val itemsViewModel = ItemsViewModel()

        enableEdgeToEdge()
        setContent {
            val uiState = itemsViewModel.uiStateFlow.collectAsState()
            var tempItems by remember { mutableStateOf(listOf<List<Item>>()) }

            tempItems = uiState.value.items
                .filter { !it.name.isNullOrBlank() }
                .sortedBy { it.name }
                .groupBy { it.listId }
                .toSortedMap()
                .map { it.value }

            FetchListTheme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(title = {
                            Text("Fetch Item List", style = Typography.titleLarge)
                        })
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(
                                start = innerPadding.calculateStartPadding(_root_ide_package_.androidx.compose.ui.platform.LocalLayoutDirection.current),
                                top = innerPadding.calculateTopPadding(),
                                end = innerPadding.calculateEndPadding(_root_ide_package_.androidx.compose.ui.platform.LocalLayoutDirection.current)
                            )
                    ) {
                        ItemList(tempItems)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemList(itemGroups: List<List<Item>>) {
    LazyColumn {
        itemGroups.forEach { itemGroup ->
            stickyHeader {
                Text(
                    text = "Section: ${itemGroup.first().listId}",
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.DarkGray)
                        .padding(4.dp)
                )
            }

            items(items = itemGroup, key = { it.id }) { item -> ItemView(item) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val itemLists =
        listOf(listOf(Item(1, 11, "test1"), Item(2, 11, "test2")), listOf(Item(33, 33, "test3")))
    FetchListTheme {
        ItemList(itemLists)
    }
}