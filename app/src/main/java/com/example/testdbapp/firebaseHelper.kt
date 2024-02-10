import android.app.Application
import com.google.firebase.FirebaseApp

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // "This" is the application context, it is passing in this app's info to firebase
        // the app's credentials are validated in our firebase server
        FirebaseApp.initializeApp(this)
    }
}
