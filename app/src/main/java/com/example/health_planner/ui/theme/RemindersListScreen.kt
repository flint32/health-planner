package com.example.health_planner.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.health_planner.RemindersViewModel
import com.example.health_planner.models.Reminder

@Composable
fun ReminderListScreen(
    userId: Int,
    viewModel: RemindersViewModel,
    onAddReminder: () -> Unit,
    onEditReminder: (Reminder) -> Unit,
    onDeleteReminder: (Reminder) -> Unit
) {
    val reminders by viewModel.reminders.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchReminders()
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Ваши напоминания",
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            LazyColumn {
                items(reminders) { reminder ->
                    ReminderItem(
                        reminder = reminder,
                        onEditReminder = onEditReminder,
                        onDeleteReminder = onDeleteReminder
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onAddReminder,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(115, 128, 243), // Set the background color
                    contentColor = Color.White // Set the text color
                )
            ) {
                Text("Добавить напоминание", fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun ReminderItem(
    reminder: Reminder,
    onEditReminder: (Reminder) -> Unit,
    onDeleteReminder: (Reminder) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(115, 128, 243), // Set the background color
            contentColor = Color.White // Set the text color
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Название препарата: ${reminder.medication_name}", fontSize = 18.sp)
            Text(text = "Дозировка: ${reminder.dosage}", fontSize = 16.sp)
            Text(text = "Время приема: ${reminder.time_to_take}", fontSize = 16.sp)
            Text(text = "Частота приема: ${reminder.frequency}", fontSize = 16.sp)
            Text(text = "Дата начала приема: ${reminder.start_date}", fontSize = 16.sp)
            Text(text = "Дата окончания приема: ${reminder.end_date}", fontSize = 16.sp)
            Text(text = "Комментарии: ${reminder.notes}", fontSize = 16.sp)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(onClick = { onDeleteReminder(reminder) }) {
                    Text("Удалить", fontSize = 14.sp)
                }
            }
        }
    }
}
