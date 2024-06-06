package com.example.health_planner.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun MainScreen(
    onShowReminders: () -> Unit,
    onShowHealthJournal: () -> Unit,
    onAddReminder: () -> Unit,
    onAddHealthJournalEntry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 16.dp, 16.dp, 50.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(
            onClick = onShowReminders,
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(top = 5.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(115, 128, 243), // Set the background color
                contentColor = Color.White // Set the text color
            )
        ) {
            Text("Напоминания", fontSize = 16.sp)
        }
        Button(
            onClick = onShowHealthJournal,
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(top = 5.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(115, 128, 243), // Set the background color
                contentColor = Color.White // Set the text color
            )
        ) {
            Text("Журнал здоровья", fontSize = 16.sp)
        }
        Button(
            onClick = onAddReminder,
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(top = 5.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(115, 128, 243), // Set the background color
                contentColor = Color.White // Set the text color
            )
        ) {
            Text("Добавить напоминание", fontSize = 16.sp)
        }
        Button(
            onClick = onAddHealthJournalEntry,
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(top = 5.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(115, 128, 243), // Set the background color
                contentColor = Color.White // Set the text color
            )
        ) {
            Text("Добавить запись журнала", fontSize = 16.sp)
        }
    }
}
