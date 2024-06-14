package com.example.health_planner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import com.example.health_planner.ui.theme.*
import com.example.health_planner.ui.theme.HealthplannerTheme

sealed class Screen {
    object Login : Screen()
    object Main : Screen()
    object AddReminder : Screen()
    object AddHealthJournalEntry : Screen()
    object Register : Screen()
    object Reminders : Screen()
    object HealthJournal : Screen()
}



class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()
    private val registrationViewModel: RegistrationViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()
    private val remindersViewModel: RemindersViewModel by viewModels()
    private val healthJournalViewModel: HealthJournalViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HealthplannerTheme {
                var currentScreen by remember { mutableStateOf<Screen>(Screen.Login) }

                BackHandler(onBack = {
                    when (currentScreen) {
                        is Screen.Main -> finish()
                        else -> currentScreen = Screen.Main
                    }
                })

                when (currentScreen) {
                    is Screen.Login -> LoginScreen(
                        viewModel = loginViewModel,
                        onLoginSuccess = { currentScreen = Screen.Main },
                        onRegister = { currentScreen = Screen.Register }
                    )
                    is Screen.Register -> RegistrationScreen(
                        viewModel = registrationViewModel,
                        onRegistrationSuccess = { currentScreen = Screen.Login }
                    )
                    is Screen.Main -> MainScreen(
                        onShowReminders = { currentScreen = Screen.Reminders },
                        onShowHealthJournal = { /* Implement show health journal */ },
                        onAddReminder = { currentScreen = Screen.AddReminder },
                        onAddHealthJournalEntry = { currentScreen = Screen.AddHealthJournalEntry }
                    )
                    is Screen.Reminders -> ReminderListScreen(
                        viewModel = remindersViewModel,
                        userId = 1, // Replace with the actual user ID
                        onAddReminder = { currentScreen = Screen.AddReminder },
                        onEditReminder = { reminder ->
                            // Navigate to the edit reminder screen (not implemented in this snippet)
                        },
                        onDeleteReminder = { reminder ->
                            remindersViewModel.deleteReminder(reminder.reminder_id, 1)
                        }
                    )
                    is Screen.AddReminder -> ReminderEntryScreen(
                        onReminderSaved = { reminder ->
                            remindersViewModel.addReminder(reminder)
                            currentScreen = Screen.Reminders
                        },
                        onCancel = { currentScreen = Screen.Reminders }
                    )
                    is Screen.HealthJournal -> HealthJournalListScreen(
                        viewModel = healthJournalViewModel,
                        userId = 1, // Replace with the actual user ID
                        onAddEntry = { currentScreen = Screen.AddHealthJournalEntry },
                        onEditEntry = { entry ->
                            // Navigate to the edit entry screen (not implemented in this snippet)
                        },
                        onDeleteEntry = { entry ->
                            // Implement delete entry logic
                        }
                    )
                    is Screen.AddHealthJournalEntry -> HealthJournalEntryScreen(
                        onJournalEntrySaved = { entry ->
                            // Save journal entry and navigate back to main screen
//                            mainViewModel.saveHealthJournalEntry(entry)
                            currentScreen = Screen.Main
                        },
                        onCancel = { currentScreen = Screen.Main }
                    )
                }
            }
        }
    }
}

