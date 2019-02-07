package y.com.studentdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivityLogin extends AppCompatActivity {
    EditText Username, Password;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main_login );
        Username = findViewById ( R.id.editText4 );
        Password = findViewById ( R.id.editText5 );
        button = findViewById ( R.id.bLogin );
        button.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent ( getApplicationContext (), MainActivity4.class );
                startActivity ( intent );
            }
        });
    }
}
