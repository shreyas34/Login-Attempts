package loginapp.com.loginattempts;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginbutton;
    private Button clearbutton;
    private TextView textEditText;
    private SharedPreferences preferences;
    private final static String PREF_CREDENTIALS="credentials";
    private final static String PREF_KEY_LOGIN_ATTEMPT="Attempts";
    private int attempt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText=findViewById(R.id.usernameEditText);
        passwordEditText=findViewById(R.id.passwordEditText);
        loginbutton=findViewById(R.id.loginbutton);
        clearbutton=findViewById(R.id.clearbutton);
        textEditText=findViewById(R.id.textviewEditText);
        preferences=getSharedPreferences(PREF_CREDENTIALS, Context.MODE_PRIVATE);

    if(getAttemptCount())
    {
      attempt=preferences.getInt(PREF_KEY_LOGIN_ATTEMPT,0);
    }
    loginbutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            login();
        }
    });

clearbutton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        preferences.edit().clear().apply();
        loginbutton.setEnabled(true);
    }
});

    }
    private void login()
    {
        String username=usernameEditText.getText().toString();
        String password=passwordEditText.getText().toString();
        if(username.equals("shivani")&&password.equals("shivani"))
        {
            Toast.makeText(MainActivity.this,"login success",Toast.LENGTH_SHORT).show();
        }
        else {
            if(attempt==3) {
                Toast.makeText(MainActivity.this, "all attempts are completed", Toast.LENGTH_SHORT).show();
                loginbutton.setEnabled(false);
            }
            else {
                attempt+=1;
                preferences.edit().putInt(PREF_KEY_LOGIN_ATTEMPT,attempt).apply();
                Toast.makeText(MainActivity.this,"login failed..ur attepts is"+attempt,Toast.LENGTH_SHORT).show();
            }
        }
    }




    private boolean getAttemptCount()
    {
        return preferences.contains(PREF_KEY_LOGIN_ATTEMPT);
    }
}

