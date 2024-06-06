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
}

class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()
    private val registrationViewModel: RegistrationViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()

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
                        onShowReminders = { /* Implement show reminders */ },
                        onShowHealthJournal = { /* Implement show health journal */ },
                        onAddReminder = { currentScreen = Screen.AddReminder },
                        onAddHealthJournalEntry = { currentScreen = Screen.AddHealthJournalEntry }
                    )
                    is Screen.AddReminder -> ReminderEntryScreen(
                        onReminderSaved = { reminder ->
                            // Save reminder and navigate back to main screen
//                            mainViewModel.saveReminder(reminder)
                            currentScreen = Screen.Main
                        },
                        onCancel = { currentScreen = Screen.Main }
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


