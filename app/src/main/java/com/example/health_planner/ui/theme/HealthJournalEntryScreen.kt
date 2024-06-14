package com.example.health_planner.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                label = { Text("Дата записи") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = symptoms,
                onValueChange = { symptoms = it },
                label = { Text("Симптомы") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = conditionDescription,
                onValueChange = { conditionDescription = it },
                label = { Text("Описания заболевания") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = otherNotes,
                onValueChange = { otherNotes = it },
                label = { Text("Комментарии") },
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { onCancel() },
                    modifier = Modifier
                        .height(60.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(115, 128, 243), // Set the background color
                        contentColor = Color.White // Set the text color
                    )) {
                    Text("Отмена")
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
                    },
                    modifier = Modifier
                        .height(60.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(115, 128, 243), // Set the background color
                        contentColor = Color.White // Set the text color
                    )
                ) {
                    Text("Сохранить")
                }
            }
        }
    }
}
