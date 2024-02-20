package com.example.jetnote

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp//acessa o application no manifest
class NoteApplication : Application() {}