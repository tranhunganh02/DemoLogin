package course.demoappfirebase

import android.app.Application
import com.google.firebase.FirebaseApp

class DemoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
    }
}