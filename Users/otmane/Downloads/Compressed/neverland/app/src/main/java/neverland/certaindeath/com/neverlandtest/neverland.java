package neverland.certaindeath.com.neverlandtest;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.Instant;

import neverland.certaindeath.com.neverlandtest.Model.Question;

public class neverland extends AppCompatActivity {

    Button b1,b2,b3,b4;
    TextView ti_question,timertxt;
    int total = 0;
    int correct = 0;
    int wrong = 0;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neverland);
        b1 = (Button)findViewById(R.id.opt1);
        b2 = (Button)findViewById(R.id.opt2);
        b3 = (Button)findViewById(R.id.opt3);
        b4 = (Button)findViewById(R.id.opt4);

        ti_question = (TextView)findViewById(R.id.questiontext);
        timertxt =  (TextView)findViewById(R.id.timertxt);

        updateQuestion();
        reverseTime(30,timertxt);
    }

    private void updateQuestion(){
        total++;
        if (total > 4){//number of
            // open the result activity
            Intent i = new Intent(neverland.this,ResultActivity.class);
            i.putExtra("total",String.valueOf(total)); // hado bach tsifet
            i.putExtra("correct",String.valueOf(correct));
            i.putExtra("incorrect",String.valueOf(wrong));
            startActivity(i);
        }
        else {
            reference = FirebaseDatabase.getInstance().getReference().child("Questions").child(String.valueOf(total));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final Question question = dataSnapshot.getValue(Question.class);

                    ti_question.setText(question.getQuestion());
                    b1.setText(question.getOption1());
                    b2.setText(question.getOption2());
                    b3.setText(question.getOption3());
                    b4.setText(question.getOption4());

                    //check if it's true or not

                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (b1.getText().toString().equals(question.getAnswer())){
                                b1.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b1.setBackgroundColor(Color.parseColor("#03A9f4"));
                                        updateQuestion();
                                    }
                                },1500);
                            }else {
                                // wrong answer
                                wrong++;
                                b1.setBackgroundColor(Color.RED);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#03A9f4"));
                                        updateQuestion();
                                    }},1500);
                            }
                        }
                    });

                    //b2
                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (b2.getText().toString().equals(question.getAnswer())){
                                b2.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b2.setBackgroundColor(Color.parseColor("#03A9f4"));
                                        updateQuestion();
                                    }
                                },1500);
                            }else {
                                // wrong answer
                                wrong++;
                                b2.setBackgroundColor(Color.RED);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b2.setBackgroundColor(Color.parseColor("#03A9f4"));
                                        updateQuestion();
                                    }},1500);
                            }
                        }
                    });

                    b3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (b3.getText().toString().equals(question.getAnswer())){
                                b3.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b3.setBackgroundColor(Color.parseColor("#03A9f4"));
                                        updateQuestion();
                                    }
                                },1500);
                            }else {
                                // wrong answer
                                wrong++;
                                b3.setBackgroundColor(Color.RED);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b3.setBackgroundColor(Color.parseColor("#03A9f4"));
                                        updateQuestion();
                                    }},1500);
                            }
                        }
                    });

                    b4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (b4.getText().toString().equals(question.getAnswer())){
                                b4.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b4.setBackgroundColor(Color.parseColor("#03A9f4"));
                                        updateQuestion();
                                    }
                                },1500);
                            }else {
                                // wrong answer
                                wrong++;
                                b4.setBackgroundColor(Color.RED);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b4.setBackgroundColor(Color.parseColor("#03A9f4"));
                                        updateQuestion();
                                    }},1500);
                            }


                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }



    public void reverseTime(int seconds , final TextView tv){

        new CountDownTimer(seconds * 1000+1000,1000){
            @Override
            public void onTick(long mili) {
                int second = (int) (mili/1000);
                int minutes = second / 60;
                tv.setText(String.format("%02d",minutes)+":"+String.format("%02d",second));
            }

            @Override
            public void onFinish() {
                tv.setText("complete");
                Intent myIn = new Intent(neverland.this,ResultActivity.class);
                myIn.putExtra("total",String.valueOf(total));
                myIn.putExtra("correct",String.valueOf(correct));
                myIn.putExtra("incorrect",String.valueOf(wrong));
                startActivity(myIn);
            }
        }.start();
    }


}
