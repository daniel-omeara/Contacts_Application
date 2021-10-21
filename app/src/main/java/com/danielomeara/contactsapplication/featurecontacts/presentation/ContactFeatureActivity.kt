package com.danielomeara.contactsapplication.featurecontacts.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.danielomeara.contactsapplication.featurecontacts.presentation.addeditcontact.AddEditContactScreen
import com.danielomeara.contactsapplication.featurecontacts.presentation.calling.CallingScreen
import com.danielomeara.contactsapplication.featurecontacts.presentation.contacts.ContactsScreen
import com.danielomeara.contactsapplication.featurecontacts.presentation.util.Screen
import com.danielomeara.contactsapplication.ui.theme.ContactsApplicationTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ContactFeatureActivity : ComponentActivity() {
    @ExperimentalComposeUiApi
    @ExperimentalPermissionsApi
    @ExperimentalFoundationApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            ContactsApplicationTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CallingScreen.route
                    ) {
                        composable(route = Screen.ContactsScreen.route) {
                            ContactsScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditContactScreen.route +
                                    "?contactId={contactId}&contactNumber={contactNumber}",
                            arguments = listOf(
                                navArgument(
                                    name = "contactId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "contactNumber"
                                ) {
                                    type = NavType.StringType
                                    nullable = true
                                    defaultValue = null
                                }
                            )
                        ) {
                            AddEditContactScreen(
                                navController = navController
                            )
                        }
                        composable(route = Screen.CallingScreen.route) {
                            CallingScreen(navController = navController)
                        }
                    }

                }
            }
        }
    }
}