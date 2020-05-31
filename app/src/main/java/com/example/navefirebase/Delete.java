package com.example.navefirebase;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static com.example.navefirebase.FBREF.refStudentGrade;
import static com.example.navefirebase.FBREF.refStudents;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;



public class Delete extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ArrayList<String> stuList = new ArrayList<String>();
    ArrayList<StudentInfo> stuValues = new ArrayList<StudentInfo>();
    ArrayList<String> stuGradeList = new ArrayList<String>();
    ArrayList<Grades> stuGradeValues = new ArrayList<Grades>();
    ListView lv,lvGrades;
    String str1,str2,str3,str4;
    ArrayAdapter<String> adp;
    ArrayAdapter<String> adp2;
    AlertDialog.Builder adb;
    ValueEventListener stuListener,StuGradeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_delete);
        lv = findViewById(R.id.lv);
        lvGrades = findViewById(R.id.lvGrades);
        lvGrades.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvGrades.setOnItemClickListener(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setOnItemClickListener(this);


        ValueEventListener stuListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {
                stuList.clear();
                stuValues.clear();
                for(DataSnapshot data : dS.getChildren()) {
                    str1 = (String)data.getKey();
                    StudentInfo stuTmp = data.getValue(StudentInfo.class);
                    stuValues.add(stuTmp);
                    str2 = stuTmp.getStudent_N()+","+stuTmp.getStudent_P()+","+stuTmp.getAddress()+","+ stuTmp.getHome_Phone()+",";
                    str2+= stuTmp.getMom_N()+","+ stuTmp.getMom_P()+ "," +stuTmp.getDad_N() + ","+ stuTmp.getDad_P();
                    stuList.add(str1+" "+str2);
                }
                adp = new ArrayAdapter<String>(Delete.this,R.layout.support_simple_spinner_dropdown_item, stuList);
                lv.setAdapter(adp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        refStudents.addValueEventListener(stuListener);

        ValueEventListener StuGradeListener = new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dS) {
                stuGradeList.clear();
                stuGradeValues.clear();
                for (DataSnapshot data : dS.getChildren()){
                    str3 = (String) data.getKey();
                    Grades stuGradeTmp = data.getValue(Grades.class);
                    stuGradeValues.add(stuGradeTmp);
                    str4 = stuGradeTmp.getSubject()+","+stuGradeTmp.getQuarter()+","+stuGradeTmp.getGrade();
                    stuGradeList.add(str3+" "+str4);
                }
                adp2 = new ArrayAdapter<String>(Delete.this,R.layout.support_simple_spinner_dropdown_item,stuGradeList);
                lvGrades.setAdapter(adp2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        refStudentGrade.addValueEventListener(StuGradeListener);

    }

    /**
     *  delete thee information with long click
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        adb = new AlertDialog.Builder(this);
        adb.setTitle("Warning!");
        adb.setMessage("Are you sure you want to delete the information?");
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int pos, long id) {
                adb.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String strstuID=stuValues.get(pos).getStuID()+"";
                        refStudents.child(strstuID).removeValue();
                        stuList.remove(pos);
                        adp.notifyDataSetChanged();
                        final String strstuID_2=stuGradeValues.get(pos).getStuID();
                        refStudentGrade.child(strstuID_2).removeValue();
                        stuGradeList.remove(pos);
                        adp2.notifyDataSetChanged();
                    }
                });
                adb.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Delete.this, str1, Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                AlertDialog ad = adb.create();
                ad.show();
                return true;
            }
        });
        lvGrades.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int pos, long id) {
                adb.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strstuID=stuValues.get(pos).getStuID();
                        refStudents.child(strstuID).removeValue();
                        stuList.remove(pos);
                        adp.notifyDataSetChanged();
                        strstuID=stuGradeValues.get(pos).getStuID();
                        refStudentGrade.child(strstuID).removeValue();
                        stuGradeList.remove(pos);
                        adp2.notifyDataSetChanged();
                    }
                });
                adb.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog ad = adb.create();
                ad.show();
                return true;
            }
        });


    }
    public void EndAct () {
        if (stuListener!=null) {
            refStudents.removeEventListener(stuListener);
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
            case (R.id.menuDataIn): {
                Intent s = new Intent(this, MainActivity.class);
                EndAct();
                startActivity(s);
                break;
            }
            case (R.id.menuSort): {
                Intent s = new Intent(this, Sort.class);
                EndAct();
                startActivity(s);
                break;
            }
            case (R.id.Credits): {
                Intent s = new Intent(this, Credits.class);
                EndAct();
                startActivity(s);
                break;
            }
            default:
                break;
        }
        return true;
    }
}

