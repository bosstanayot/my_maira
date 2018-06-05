package maira.and.barjord.mymaira

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.HttpMethod
import com.facebook.login.LoginManager
import com.facebook.login.widget.DeviceLoginButton
import com.facebook.login.widget.LoginButton
import com.facebook.GraphResponse
import org.json.JSONObject




class FeedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)


        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired
        val logout_btn = findViewById<LoginButton>(R.id.logout_button)

        logout_btn.setOnClickListener {
            if(isLoggedIn){
                AccessToken.setCurrentAccessToken(null)
                LoginManager.getInstance().logOut()
                startActivity(Intent(this, LoginActivity::class.java).addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                ))

            }
        }
        val request = GraphRequest.newGraphPathRequest(
                accessToken, "/bnk48official.maira",
                GraphRequest.Callback {
                    
                }


        )
        val parameters = Bundle()
        parameters.putString("fields", "id,name,posts{message,full_picture,permalink_url}")
        request.parameters = parameters
        request.executeAsync()
    }
}
