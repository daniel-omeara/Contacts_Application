package com.danielomeara.contactsapplication.feature_contacts.presentation.calling.components

import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.google.accompanist.permissions.rememberPermissionState

@ExperimentalPermissionsApi
@Composable
fun FeatureThatRequiresCallingPermission(
    navigateToSettingsScreen: () -> Unit,
    content: @Composable () -> Unit
) {
    // Track if the user doesn't want to see the rationale any more.
    var doNotShowRationale by rememberSaveable { mutableStateOf(false) }

    val phonePermissionState = rememberPermissionState(Manifest.permission.CALL_PHONE)

    PermissionRequired(
        permissionState = phonePermissionState,
        permissionNotGrantedContent = {
            if (doNotShowRationale) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Feature not available",
                        )
                        Button(onClick = navigateToSettingsScreen) {
                            Text("Open Settings")
                        }
                    }
                }
            } else {
                Rationale(
                    onDoNotShowRationale = { doNotShowRationale = true },
                    onRequestPermission = { phonePermissionState.launchPermissionRequest() }
                )
            }
        },
        permissionNotAvailableContent = {
            PermissionDenied(navigateToSettingsScreen)
        }
    ) {
        content()
    }
}

@Composable
private fun Rationale(
    onDoNotShowRationale: () -> Unit,
    onRequestPermission: () -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calling is a pretty cool part of this app. In order to use this feature you must grant the permission.",
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = onRequestPermission,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Request permission")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onDoNotShowRationale,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Don't show rationale again")
        }
    }
}

@Composable
private fun PermissionDenied(
    navigateToSettingsScreen: () -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calling permission denied. Please, grant us access on the Settings screen.",

        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = navigateToSettingsScreen,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Open Settings")
        }
    }
}