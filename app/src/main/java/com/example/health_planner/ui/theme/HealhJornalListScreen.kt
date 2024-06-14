package com.example.health_planner.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.health_planner.HealthJournalViewModel
import com.example.health_planner.models.HealthJournalEntry

@Composable
fun HealthJournalListScreen(
    viewModel: HealthJournalViewModel,
    userId: Int,
    onAddEntry: () -> Unit,
    onEditEntry: (HealthJournalEntry) -> Unit,
    onDeleteEntry: (HealthJournalEntry) -> Unit
) {
    val entries by viewModel.entries.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchEntries(userId)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Your Health Journal",
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            LazyColumn {
                items(entries) { entry ->
                    HealthJournalItem(
                        entry = entry,
                        onEditEntry = onEditEntry,
                        onDeleteEntry = onDeleteEntry
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onAddEntry,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(115, 128, 243),
                    contentColor = Color.White
                )
            ) {
                Text("Add Entry", fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun HealthJournalItem(
    entry: HealthJournalEntry,
    onEditEntry: (HealthJournalEntry) -> Unit,
    onDeleteEntry: (HealthJournalEntry) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Date: ${entry.entry_date}", fontSize = 18.sp)
            Text(text = "Symptoms: ${entry.symptoms}", fontSize = 16.sp)
            Text(text = "Condition: ${entry.condition_description}", fontSize = 16.sp)
            Text(text = "Notes: ${entry.other_notes}", fontSize = 16.sp)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(onClick = { onEditEntry(entry) }) {
                    Text("Edit", fontSize = 14.sp)
                }
                TextButton(onClick = { onDeleteEntry(entry) }) {
                    Text("Delete", fontSize = 14.sp)
                }
            }
        }
    }
}
