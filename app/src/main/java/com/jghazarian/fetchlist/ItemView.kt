package com.jghazarian.fetchlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jghazarian.fetchlist.data.model.Item
import com.jghazarian.fetchlist.ui.theme.Typography

@Composable
fun ItemView(
    item: Item,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ItemLine("ID:", item.id.toString())
            ItemLine("List Id:", item.listId.toString())
            ItemLine("Name:", item.name.toString())
        }
    }
}

@Composable
fun ItemLine(
    title: String,
    content: String
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = title, style = Typography.titleMedium, modifier = Modifier.padding(end = 8.dp))
        Text(text = content, style = Typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    val item = Item(1, 1234567890, "test item with long name")
    ItemView(item)
}
