package y.com.studentdatabase;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity implements View.OnClickListener {
    EditText Roll, Mrk, name;
    Button b1, b2, b3, b4, b5, b6;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView (R.layout.activity_main4);
        initiateobj ();
        db = openOrCreateDatabase ( "StudentDB", MODE_PRIVATE, null );
        db.execSQL ( "Create table If not exists student (rollno VARCHAR,name VARCHAR, marks VARCHAR);" );
    }

    private void initiateobj() {
        Roll = findViewById ( R.id.editText );
        Mrk = findViewById ( R.id.editText2 );
        name = findViewById ( R.id.editText3 );
        b1 = findViewById ( R.id.bAdd );
        b2 = findViewById ( R.id.bDelete );
        b3 = findViewById ( R.id.bModify );
        b4 = findViewById ( R.id.bVA );
        b5 = findViewById ( R.id.bView );
        b6 = findViewById ( R.id.bShow );
        initclick ();
    }

    private void initclick() {
        b1.setOnClickListener (this );
        b2.setOnClickListener (this );
        b3.setOnClickListener (this );
        b4.setOnClickListener (this );
        b5.setOnClickListener (this );
        b6.setOnClickListener (this );
    }

    public void onClick(View view) {
        if (view == b1) {
            if (Roll.getText ().toString ().trim ().length () == 0 ||
                    name.getText ().toString ().trim ().length () == 0 ||
                    Mrk.getText ().toString ().trim ().length () == 0) {
                Toast.makeText ( this, "Error", Toast.LENGTH_SHORT ).show ();
                showMessage ( "Error", "Please enter all values" );
                return;
            }
            db.execSQL ( "INSERT INTO student VALUES('" + Roll.getText () + "','" + name.getText () +
                    "','" + Mrk.getText () + "');" );
            showMessage ( "Success", "Record added" );
            clearText ();
        }

        if (view == b2) {
            if (Roll.getText ().toString ().trim ().length () == 0) {
                showMessage ( "Error", "Please enter Rollno" );
                return;
            }
            Cursor c = db.rawQuery ( "SELECT * FROM student WHERE rollno='" + Roll.getText () + "'", null );
            if (c.moveToFirst ()) {
                db.execSQL ( "DELETE FROM student WHERE rollno='" + Roll.getText () + "'" );
                showMessage ( "Success", "Record Deleted" );
            } else {
                showMessage ( "Error", "Invalid Rollno" );
            }
            clearText ();
        }
        if (view == b3) {
            if (Roll.getText ().toString ().trim ().length () == 0) {
                showMessage ( "Error", "Please enter Rollno" );
                return;
            }
            Cursor c = db.rawQuery ( "SELECT * FROM student WHERE rollno='" + Roll.getText () + "'", null );
            if (c.moveToFirst ()) {
                db.execSQL ( "UPDATE student SET name='" + name.getText () + "',marks='" + Mrk.getText () +
                        "' WHERE rollno='" + Roll.getText () + "'" );
                showMessage ( "Success", "Record Modified" );
            } else {
                showMessage ( "Error", "Invalid Rollno" );
            }
            clearText ();
        }
        if (view == b5) {
            if (Roll.getText ().toString ().trim ().length () == 0) {
                showMessage ( "Error", "Please enter Rollno" );
                return;
            }
            Cursor c = db.rawQuery ( "SELECT * FROM student WHERE rollno='" + Roll.getText () + "'", null );
            if (c.moveToFirst ()) {
                name.setText ( c.getString ( 1 ) );
                Mrk.setText ( c.getString ( 2 ) );
            } else {
                showMessage ( "Error", "Invalid Rollno" );
                clearText ();
            }
        }
        if (view == b4) {
            Cursor c = db.rawQuery ( "SELECT * FROM student", null );
            if (c.getCount () == 0) {
                showMessage ( "Error", "No records found" );
                return;
            }
            StringBuffer buffer = new StringBuffer ();
            while (c.moveToNext ()) {
                buffer.append ( "Rollno: " + c.getString ( 0 ) + "\n" );
                buffer.append ( "Marks: " + c.getString ( 1 ) + "\n" );
                buffer.append ( "Name: " + c.getString ( 2 ) + "\n\n" );
            }
            showMessage ( "Student Details", buffer.toString () );
        }
        if (view == b6) {
            showMessage ( "Student Record Application", "Developed By Vaibhav Zapke" );
        }
    }

    private void showMessage(String success, String record_added) {

        //Toast.makeText ( this, "Status ::"+success+"\n Result"+record_added, Toast.LENGTH_SHORT ).show ();
        AlertDialog.Builder builder = new AlertDialog.Builder ( this );
        builder.setTitle ( success );
        builder.setMessage ( record_added );
        builder.show ();
        builder.setIcon ( R.drawable.ic_launcher_background );
    }

    private void clearText() {
        Roll.setText ( "" );
        name.setText ( "" );
        Mrk.setText ( "" );
    }


}

