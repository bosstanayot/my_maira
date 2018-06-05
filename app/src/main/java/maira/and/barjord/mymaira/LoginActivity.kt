package maira.and.barjord.mymaira

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback
import com.facebook.login.widget.LoginButton
import com.facebook.login.LoginManager
import com.facebook.login.widget.DeviceLoginButton
import java.util.*


class LoginActivity : AppCompatActivity() {
    private var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired

        if(isLoggedIn){
            startActivity(Intent(this@LoginActivity, FeedActivity::class.java).addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            ))
        }

        val loginButton = findViewById<View>(R.id.login_button) as LoginButton
        loginButton.setReadPermissions("email");
            callbackManager = CallbackManager.Factory.create()
            //LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
            LoginManager.getInstance().registerCallback(callbackManager,
                    object : FacebookCallback<LoginResult> {
                        override fun onSuccess(loginResult: LoginResult) {
                            // val intent = Intent(this@LoginActivity, FeedActivity::class.java)
                            startActivity(Intent(this@LoginActivity, FeedActivity::class.java).addFlags(
                                    Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            ))
                        }

                        override fun onCancel() {
                            // App code
                        }

                        override fun onError(exception: FacebookException) {
                            // App code
                        }
                    })


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }
}
