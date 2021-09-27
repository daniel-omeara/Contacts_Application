package com.danielomeara.contactsapplication.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightBlue200 = Color(0xff81d4fa)
val LightBlue500 = Color(0xFF03a9f4)
val LightBlue700 = Color(0xFF0288d1)
val Green200 = Color(0xFFa5d6a7)

val PhoneGreen400ALight = Color(0xFF00b248)
val PhoneGreen400ADark = Color(0xFF00e676)

val Colors.phoneIcon: Color
    @Composable get() = if (isLight) PhoneGreen400ALight else PhoneGreen400ADark

val Colors.hintText: Color
    @Composable get() = if (isLight) Color.DarkGray else Color.LightGray

val Colors.bottomNavIcons: Color
    @Composable get() = LightBlue500