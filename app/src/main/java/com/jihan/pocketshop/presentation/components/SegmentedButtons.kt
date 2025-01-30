package com.jihan.pocketshop.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SegmentedButtons(
    modifier: Modifier = Modifier,
    buttonArray: List<String>,
    currentItem: Int,
    title:String="",
    shape: CornerBasedShape = SegmentedButtonDefaults.baseShape.copy(
        topStart = CornerSize(0.dp),
        topEnd = CornerSize(0.dp),
        bottomStart = CornerSize(0.dp),
        bottomEnd = CornerSize(0.dp)
    ),
    onSegmentSelected: (Int) -> Unit
) {

    Text(title, style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(vertical = 4.dp))
    SingleChoiceSegmentedButtonRow(modifier = modifier) {
        buttonArray.forEachIndexed { index, text ->

            SegmentedButton(
                selected = index == currentItem,
                onClick = { onSegmentSelected(index) },
                shape = shape
            ) {
                Text(text)
            }
        }

    }
}