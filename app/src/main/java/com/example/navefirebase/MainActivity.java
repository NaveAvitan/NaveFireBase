package com.example.navefirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.navefirebase.FBREF.refStudentGrade;
import static com.example.navefirebase.FBREF.refStudents;




public class MainActivity extends AppCompatActivity {
    EditText S_Name,Address,S_Phone,HomePhone,M_Name,M_Phone,D_Name,D_Phone,Subject,Quarter,Grade;
    StudentInfo SPI;
    Grades SG;
    String  stuID=" ";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        S_Name = findViewById(R.id.S_Name);
        Address = findViewById(R.id.Address);
        S_Phone = findViewById(R.id.S_Phone);
        HomePhone = findViewById(R.id.HomePhone);
        M_Name = findViewById(R.id.M_Name);
        M_Phone = findViewById(R.id.M_Phone);
        D_Name = findViewById(R.id.D_Name);
        D_Phone = findViewById(R.id.D_Phone);
        Subject = findViewById(R.id.Subject);
        Quarter = findViewById(R.id.Quarter);
        Grade = findViewById(R.id.Grade);
    }

    /**
     * put on the firebase the data from the user
     * @param view
     */

    public void Data_In(View view) {
        String Student_N = S_Name.getText().toString();
        String address = Address.getText().toString();
        String Mom_N = M_Name.getText().toString();
        String Dad_N = D_Name.getText().toString();

        String Student_P = S_Phone.getText().toString();
        String HomeP = HomePhone.getText().toString();
        String Mom_Phone = M_Phone.getText().toString();
        String Dad_Phone = D_Phone.getText().toString();

        long S_P = Long.parseLong(Student_P);
        long HP = Long.parseLong(HomeP);
        long  M_P = Long.parseLong(Mom_Phone);
        long D_P = Long.parseLong(Dad_Phone);

        stuID = Student_N.charAt(0)+"_"+Student_P.charAt(3)+Student_P.charAt(4);
        SPI = new StudentInfo(Student_N,address,S_P,HP,Mom_N,M_P,Dad_N,D_P,stuID);
        refStudents.child(stuID).setValue(SPI);
    }

    /**
     * put on the firebase the data from the user
     * @param view
     */
    public void Grades_In(View view) {
        if(stuID== " "){
            Toast.makeText(this, "Enter the student private info first.", Toast.LENGTH_SHORT).show();
        }
        else {
            String subject = Subject.getText().toString();
            String quarter = Quarter.getText().toString();
            String grade = Grade.getText().toString();

            long Q = Long.parseLong(quarter);
            long G = Long.parseLong(grade);

            SG = new Grades(subject, Q, G, stuID);
            refStudentGrade.child(stuID).setValue(SG);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }


    /**
     * switch activity
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case (R.id.menuDelete): {
                Intent s = new Intent(this, Delete.class);
                startActivity(s);
                break;
            }
            case (R.id.menuSort): {
                Intent s = new Intent(this, Sort.class);
                startActivity(s);
                break;
            }
            case (R.id.Credits): {
                Intent s = new Intent(this, Credits.class);
                startActivity(s);
                break;
            }
            default:
                break;
        }
        return true;
    }
}

