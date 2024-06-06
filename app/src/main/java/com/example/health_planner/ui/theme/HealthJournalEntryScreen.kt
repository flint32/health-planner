package com.example.health_planner.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.health_planner.models.HealthJournalEntry

@Composable
fun HealthJournalEntryScreen(
    onJournalEntrySaved: (HealthJournalEntry) -> Unit,
    onCancel: () -> Unit
) {
    var entryDate by remember { mutableStateOf("") }
    var symptoms by remember { mutableStateOf("") }
    var conditionDescription by remember { mutableStateOf("") }
    var otherNotes by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = entryDate,
                onValueChange = { entryDate = it },
                label = { Text("Entry Date") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = symptoms,
                onValueChange = { symptoms = it },
                label = { Text("Symptoms") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = conditionDescription,
                onValueChange = { conditionDescription = it },
                label = { Text("Condition Description") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = otherNotes,
                onValueChange = { otherNotes = it },
                label = { Text("Other Notes") },
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { onCancel() }) {
                    Text("Cancel")
                }
                Button(
                    onClick = {
                        val entry = HealthJournalEntry(
                            entry_id = 0, // Use appropriate ID
                            user_id = 0, // Replace with actual user ID
                            entry_date = entryDate,
                            symptoms = symptoms,
                            condition_description = conditionDescription,
                            other_notes = otherNotes
                        )
                        onJournalEntrySaved(entry)
                    }
                ) {
                    Text("Save Entry")
                }
            }
        }
    }
}
