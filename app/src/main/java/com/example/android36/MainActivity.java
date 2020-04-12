package com.example.android36;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

import javax.xml.transform.stream.StreamResult;


public class MainActivity extends AppCompatActivity {


    ImageView image1,image2, image3, image4, image5, image6, image7, image8, imageMain;
    TextView tv_status, level, tvHighScore ;
    Button b_next;
    TextToSpeech tts;
    MediaPlayer mediaPlayer;

    //instructions
    String[] instructions = {
            "find bee",
            "find elephant",
            "find cat",
            "find dog",
            "find bear",
            "find frog",
            "find bird",
            "find sheep",
            "find lion",
            "find panda",
            "find penguin",
            "find rabbit",
            "find dolphin",
            "find zebra",
            "find giraffe",
            "find horse",
            "find chimp",
            "find monkey",
            "find pig",
            "find snake",
    };

    //animal sounds
    Integer[] sounds = {
            R.raw.bee,
            R.raw.elephant,
            R.raw.cat,
            R.raw.dog,
            R.raw.bear,
            R.raw.frog,
            R.raw.bird,
            R.raw.koyun,
            R.raw.lion,
            R.raw.panda,
            R.raw.penguin,
            R.raw.rabbit,
            R.raw.dolphin,
            R.raw.zebra,
            R.raw.zurafa,
            R.raw.horse,
            R.raw.chimp,
            R.raw.monkey,
            R.raw.pig,
            R.raw.snake,
    };

    //list of main images yani soru imageleri
    Integer[] mainImages ={
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image8,
            R.drawable.image9,
            R.drawable.image10,
            R.drawable.image11,
            R.drawable.image12,
            R.drawable.image13,
            R.drawable.image14,
            R.drawable.image15,
            R.drawable.image16,
            R.drawable.image17,
            R.drawable.image18,
            R.drawable.image19,
            R.drawable.image20,
    };
    //list of images yani cevap imageleri
    Integer[] images ={
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image8,
            R.drawable.image9,
            R.drawable.image10,
            R.drawable.image11,
            R.drawable.image12,
            R.drawable.image13,
            R.drawable.image14,
            R.drawable.image15,
            R.drawable.image16,
            R.drawable.image17,
            R.drawable.image18,
            R.drawable.image19,
            R.drawable.image20,
    };


    //list of numbers for all of the images
    Integer[] images_number = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
    int turn = 1;
    int correctAnswer = 0;
    int score = 0;
    int highScore = 0;
    int levelUpdater = 0;
    //int soundIndex ;

    String strFilePath = "C:\\Users\\Dell\\StudioProjects\\android36\\app\\src\\main\\res\\raw\\scores.txt";

    @Override
    protected void onPause(){
        if(tts != null){
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }
    public void onClick(View v){
        ConvertTextToSpeech();
    }
    private void ConvertTextToSpeech(){
        if(instructions[images_number[turn]] == null || "".equals(instructions[images_number[turn]])){
            tts.speak(instructions[images_number[turn]], TextToSpeech.QUEUE_FLUSH, null);
        }else
            tts.speak(instructions[images_number[turn]], TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image1 =  findViewById(R.id.imageId1);
        image2 =  findViewById(R.id.imageId2);
        image3 =  findViewById(R.id.imageId3);
        image4 =  findViewById(R.id.imageId4);
        image5 = findViewById(R.id.imageId5);
        image6 = findViewById(R.id.imageId6);
        image7 = findViewById(R.id.imageId7);
        image8 = findViewById(R.id.imageId8);
        imageMain = findViewById(R.id.imageMain);

        tv_status =  findViewById(R.id.tv_status);
        b_next =  findViewById(R.id.b_next);
        level =  findViewById(R.id.level);
        tvHighScore  = findViewById(R.id.highScore);


        //Setup MediaPlayer
        //MediaPlayer mp = MediaPlayer.create(this, sounds[images_number[turn]]);

        //randomly shuffle the images
        Collections.shuffle(Arrays.asList(images_number));


        //setimages for LEVEL 1
        setImagesLv1();



        //clicks
        image1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //check if answer is correct or not
                if(correctAnswer == 1){
                    score++;
                    tv_status.setText("Doğru Bildin");
                    b_next.setVisibility(View.VISIBLE);
                    //disabling images
                    image1.setEnabled(false);
                    image2.setEnabled(false);
                    image3.setEnabled(false);
                    image4.setEnabled(false);
                }else{
                    tv_status.setText("Yanlış Bildin");
                    b_next.setVisibility(View.VISIBLE);

                    //disabling images
                    image1.setEnabled(false);
                    image2.setEnabled(false);
                    image3.setEnabled(false);
                    image4.setEnabled(false);
                    chekEnd();
                }
            }
        });
        image2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //check if answer is correct or not
                if(correctAnswer == 2){
                    score++;
                    tv_status.setText("Doğru Bildin");
                    b_next.setVisibility(View.VISIBLE);
                    //disabling images
                    image1.setEnabled(false);
                    image2.setEnabled(false);
                    image3.setEnabled(false);
                    image4.setEnabled(false);
                }else{
                    tv_status.setText("Yanlış Bildin");
                    b_next.setVisibility(View.VISIBLE);

                    //disabling images
                    image1.setEnabled(false);
                    image2.setEnabled(false);
                    image3.setEnabled(false);
                    image4.setEnabled(false);
                    chekEnd();
                }

            }
        });
        image3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //check if answer is correct or not
                if(correctAnswer == 3){
                    score++;
                    tv_status.setText("Doğru Bildin");
                    b_next.setVisibility(View.VISIBLE);
                    //disabling images
                    image1.setEnabled(false);
                    image2.setEnabled(false);
                    image3.setEnabled(false);
                    image4.setEnabled(false);
                }else{
                    tv_status.setText("Yanlış Bildin");
                    b_next.setVisibility(View.VISIBLE);

                    //disabling images
                    image1.setEnabled(false);
                    image2.setEnabled(false);
                    image3.setEnabled(false);
                    image4.setEnabled(false);
                    chekEnd();
                }
            }
        });
        image4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //check if answer is correct or not
                if(correctAnswer == 4){
                    score++;
                    tv_status.setText("Doğru Bildin");
                    b_next.setVisibility(View.VISIBLE);
                    //disabling images
                    image1.setEnabled(false);
                    image2.setEnabled(false);
                    image3.setEnabled(false);
                    image4.setEnabled(false);
                }else{
                    tv_status.setText("Yanlış Bildin");
                    b_next.setVisibility(View.VISIBLE);

                    //disabling images
                    image1.setEnabled(false);
                    image2.setEnabled(false);
                    image3.setEnabled(false);
                    image4.setEnabled(false);
                    chekEnd();
                }

            }
        });
        image5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //check if answer is correct or not
                if(correctAnswer == 5){
                    score++;
                    tv_status.setText("Doğru Bildin");
                    b_next.setVisibility(View.VISIBLE);
                    //disabling images
                    image1.setEnabled(false);
                    image2.setEnabled(false);
                    image3.setEnabled(false);
                    image4.setEnabled(false);
                    image5.setEnabled(false);
                }else{
                    tv_status.setText("Yanlış Bildin");
                    b_next.setVisibility(View.VISIBLE);

                    //disabling images
                    image1.setEnabled(false);
                    image2.setEnabled(false);
                    image3.setEnabled(false);
                    image4.setEnabled(false);
                    image5.setEnabled(false);
                    chekEnd();
                }

            }
        });
        image6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //check if answer is correct or not
                if(correctAnswer == 6){
                    score++;
                    tv_status.setText("Doğru Bildin");
                    b_next.setVisibility(View.VISIBLE);
                    //disabling images
                    image1.setEnabled(false);
                    image2.setEnabled(false);
                    image3.setEnabled(false);
                    image4.setEnabled(false);
                    image5.setEnabled(false);
                    image6.setEnabled(false);
                }else{
                    tv_status.setText("Yanlış Bildin");
                    b_next.setVisibility(View.VISIBLE);

                    //disabling images
                    image1.setEnabled(false);
                    image2.setEnabled(false);
                    image3.setEnabled(false);
                    image4.setEnabled(false);
                    image5.setEnabled(false);
                    image6.setEnabled(false);
                    chekEnd();
                }

            }
        });
        image7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //check if answer is correct or not
                if(correctAnswer == 7){
                    score++;
                    tv_status.setText("Doğru Bildin");
                    b_next.setVisibility(View.VISIBLE);
                    //disabling images
                    image1.setEnabled(false);
                    image2.setEnabled(false);
                    image3.setEnabled(false);
                    image4.setEnabled(false);
                    image5.setEnabled(false);
                    image6.setEnabled(false);
                    image7.setEnabled(false);
                }else{
                    tv_status.setText("Yanlış Bildin");
                    b_next.setVisibility(View.VISIBLE);

                    //disabling images
                    image1.setEnabled(false);
                    image2.setEnabled(false);
                    image3.setEnabled(false);
                    image4.setEnabled(false);
                    image5.setEnabled(false);
                    image6.setEnabled(false);
                    image7.setEnabled(false);
                    chekEnd();
                }

            }
        });
        image8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //check if answer is correct or not
                if(correctAnswer == 8){
                    score++;
                    tv_status.setText("Doğru Bildin");
                    b_next.setVisibility(View.VISIBLE);
                    //disabling images
                    image1.setEnabled(false);
                    image2.setEnabled(false);
                    image3.setEnabled(false);
                    image4.setEnabled(false);
                    image5.setEnabled(false);
                    image6.setEnabled(false);
                    image7.setEnabled(false);
                    image8.setEnabled(false);
                }else{
                    tv_status.setText("Yanlış Bildin");
                    b_next.setVisibility(View.VISIBLE);

                    //disabling images
                    image1.setEnabled(false);
                    image2.setEnabled(false);
                    image3.setEnabled(false);
                    image4.setEnabled(false);
                    image5.setEnabled(false);
                    image6.setEnabled(false);
                    image7.setEnabled(false);
                    image8.setEnabled(false);
                    chekEnd();
                }

            }
        });

        b_next.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //increase the turn and finish the game if 15 turns are passed
                turn = turn  + 1;

                levelUpdater++;
                if(levelUpdater<3){
                    setImagesLv1();
                }if(levelUpdater >= 3 & levelUpdater < 6){
                    level.setText("Level 2");
                    setImagesLv2();
                }if (levelUpdater >= 6 & levelUpdater < 9){
                    level.setText("Level 3");
                    setImagesLv3();
                }if(levelUpdater >= 9 & levelUpdater < 12){
                    level.setText("Level 4");
                    setImagesLv4();
                }if(levelUpdater >= 12 & levelUpdater < 15){
                    level.setText("Level 5");
                    setImagesLv5();
                }
                if(levelUpdater==15){
                    chekEnd();
                }
                b_next.setVisibility(View.INVISIBLE);

            }
        });
    }//END of onCreate




    private void setImagesLv1() {
        //determining correct answer 1-2
        Random r = new Random();
        correctAnswer = r.nextInt(2) + 1;

        //generate randomly wrong answers
        int wrongAnswer1;

        do {
            wrongAnswer1 = r.nextInt(18);
        } while (wrongAnswer1 == images_number[turn]);

        switch (correctAnswer) {
            case 1:
                image1.setImageResource(images[images_number[turn]]);
                image2.setImageResource(images[wrongAnswer1]);
                break;
            case 2:
                image1.setImageResource(images[wrongAnswer1]);
                image2.setImageResource(images[images_number[turn]]);
                break;
        }

        //setting images for the question
        imageMain.setImageResource(mainImages[images_number[turn]]);



        tts = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("error", "This lang is not supported");
                    } else {
                        ConvertTextToSpeech();
                    }
                } else
                    Log.e("error", "Initilaziton filed!");
            }
        });
        //soundIndex = images_number[turn];
        mediaPlayer = MediaPlayer.create(MainActivity.this, sounds[images_number[turn]]);
        mediaPlayer.start();

        //null stuff
        tv_status.setText("");

        image1.setEnabled(true);
        image2.setEnabled(true);
    }

    private void setImagesLv2(){
        //determine which is the correct answer 1-3
        Random r = new Random();
        correctAnswer = r.nextInt(3) + 1;

        //generate random wrong answers
        int wrongAnswer1, wrongAnswer2;

        do{
            wrongAnswer1=r.nextInt(18);
        }while (wrongAnswer1 == images_number[turn]);

        do{
            wrongAnswer2 = r.nextInt(18);
        }while (wrongAnswer2 == images_number[turn] || wrongAnswer2==wrongAnswer1);

        //setting images for all answers
        switch(correctAnswer){
            case 1:
                image1.setImageResource(images[images_number[turn]]);
                image2.setImageResource(images[wrongAnswer1]);
                image3.setImageResource(images[wrongAnswer2]);
                break;
            case 2:
                image1.setImageResource(images[wrongAnswer1]);
                image2.setImageResource(images[images_number[turn]]);
                image3.setImageResource(images[wrongAnswer2]);
                break;
            case 3:
                image1.setImageResource(images[wrongAnswer1]);
                image2.setImageResource(images[wrongAnswer2]);
                image3.setImageResource(images[images_number[turn]]);
                break;
        }

        //setting images for the question
        imageMain.setImageResource(mainImages[images_number[turn]]);

        tts = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("error", "This lang is not supported");
                    } else {
                        ConvertTextToSpeech();
                    }
                } else
                    Log.e("error", "Initilaziton filed!");
            }
        });

        //soundIndex = images_number[turn];
        mediaPlayer = MediaPlayer.create(MainActivity.this, sounds[images_number[turn]]);
        mediaPlayer.start();

        //null stuff
        tv_status.setText("");

        image1.setEnabled(true);
        image2.setEnabled(true);
        image3.setEnabled(true);
    }

    private void setImagesLv3(){
        //determine which is the correct answer 1-4
        Random r = new Random();
        correctAnswer = r.nextInt(4) + 1;

        //generate random wrong answers
        int wrongAnswer1, wrongAnswer2, wrongAnswer3;

        do{
            wrongAnswer1=r.nextInt(18);
        }while (wrongAnswer1 == images_number[turn]);

        do{
            wrongAnswer2 = r.nextInt(18);
        }while (wrongAnswer2 == images_number[turn] || wrongAnswer2==wrongAnswer1);

        do{
            wrongAnswer3 = r.nextInt(18);
        }while (wrongAnswer3 == images_number[turn] || wrongAnswer3==wrongAnswer2 || wrongAnswer3==wrongAnswer1);

        //setting images for all answers
        switch(correctAnswer){
            case 1:
                image1.setImageResource(images[images_number[turn]]);
                image2.setImageResource(images[wrongAnswer1]);
                image3.setImageResource(images[wrongAnswer2]);
                image4.setImageResource(images[wrongAnswer3]);
                break;
            case 2:
                image1.setImageResource(images[wrongAnswer1]);
                image2.setImageResource(images[images_number[turn]]);
                image3.setImageResource(images[wrongAnswer2]);
                image4.setImageResource(images[wrongAnswer3]);
                break;
            case 3:
                image1.setImageResource(images[wrongAnswer1]);
                image2.setImageResource(images[wrongAnswer2]);
                image3.setImageResource(images[images_number[turn]]);
                image4.setImageResource(images[wrongAnswer3]);
                break;
            case 4:
                image1.setImageResource(images[wrongAnswer1]);
                image2.setImageResource(images[wrongAnswer2]);
                image3.setImageResource(images[wrongAnswer3]);
                image4.setImageResource(images[images_number[turn]]);
                break;
        }

        //setting images for the question
        imageMain.setImageResource(mainImages[images_number[turn]]);

        tts = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("error", "This lang is not supported");
                    } else {
                        ConvertTextToSpeech();
                    }
                } else
                    Log.e("error", "Initilaziton filed!");
            }
        });

       // soundIndex = images_number[turn];
        mediaPlayer = MediaPlayer.create(MainActivity.this, sounds[images_number[turn]]);
        mediaPlayer.start();

        //null stuff
        tv_status.setText("");

        image1.setEnabled(true);
        image2.setEnabled(true);
        image3.setEnabled(true);
        image4.setEnabled(true);
    }
    private void setImagesLv4(){
        //determine which is the correct answer 1-6
        Random r = new Random();
        correctAnswer = r.nextInt(6) + 1;

        //generate random wrong answers
        int wrongAnswer1, wrongAnswer2, wrongAnswer3, wrongAnswer4, wrongAnswer5;

        do{
            wrongAnswer1=r.nextInt(18);
        }while (wrongAnswer1 == images_number[turn]);

        do{
            wrongAnswer2 = r.nextInt(18);
        }while (wrongAnswer2 == images_number[turn] || wrongAnswer2==wrongAnswer1);

        do{
            wrongAnswer3 = r.nextInt(18);
        }while (wrongAnswer3 == images_number[turn] || wrongAnswer3==wrongAnswer2 || wrongAnswer3==wrongAnswer1);
        do{
            wrongAnswer4 = r.nextInt(18);
        }while (wrongAnswer4 == images_number[turn] || wrongAnswer4==wrongAnswer3 || wrongAnswer4==wrongAnswer2 || wrongAnswer4==wrongAnswer1);
        do{
            wrongAnswer5 = r.nextInt(18);
        }while (wrongAnswer5 == images_number[turn] || wrongAnswer5==wrongAnswer4 || wrongAnswer5==wrongAnswer3 || wrongAnswer5==wrongAnswer2 || wrongAnswer5==wrongAnswer1);

        //setting images for all answers
        switch(correctAnswer){
            case 1:
                image1.setImageResource(images[images_number[turn]]);
                image2.setImageResource(images[wrongAnswer1]);
                image3.setImageResource(images[wrongAnswer2]);
                image4.setImageResource(images[wrongAnswer3]);
                image5.setImageResource(images[wrongAnswer4]);
                image6.setImageResource(images[wrongAnswer5]);
                break;
            case 2:
                image1.setImageResource(images[wrongAnswer1]);
                image2.setImageResource(images[images_number[turn]]);
                image3.setImageResource(images[wrongAnswer2]);
                image4.setImageResource(images[wrongAnswer3]);
                image5.setImageResource(images[wrongAnswer4]);
                image6.setImageResource(images[wrongAnswer5]);
                break;
            case 3:
                image1.setImageResource(images[wrongAnswer1]);
                image2.setImageResource(images[wrongAnswer2]);
                image3.setImageResource(images[images_number[turn]]);
                image4.setImageResource(images[wrongAnswer3]);
                image5.setImageResource(images[wrongAnswer4]);
                image6.setImageResource(images[wrongAnswer5]);
                break;
            case 4:
                image1.setImageResource(images[wrongAnswer1]);
                image2.setImageResource(images[wrongAnswer2]);
                image3.setImageResource(images[wrongAnswer3]);
                image4.setImageResource(images[images_number[turn]]);
                image5.setImageResource(images[wrongAnswer4]);
                image6.setImageResource(images[wrongAnswer5]);
                break;
            case 5:
                image1.setImageResource(images[wrongAnswer1]);
                image2.setImageResource(images[wrongAnswer2]);
                image3.setImageResource(images[wrongAnswer3]);
                image4.setImageResource(images[wrongAnswer4]);
                image5.setImageResource(images[images_number[turn]]);
                image6.setImageResource(images[wrongAnswer5]);
                break;
            case 6:
                image1.setImageResource(images[wrongAnswer1]);
                image2.setImageResource(images[wrongAnswer2]);
                image3.setImageResource(images[wrongAnswer3]);
                image4.setImageResource(images[wrongAnswer4]);
                image5.setImageResource(images[wrongAnswer5]);
                image6.setImageResource(images[images_number[turn]]);
                break;
        }

        //setting images for the question
        imageMain.setImageResource(mainImages[images_number[turn]]);

        tts = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("error", "This lang is not supported");
                    } else {
                        ConvertTextToSpeech();
                    }
                } else
                    Log.e("error", "Initilaziton filed!");
            }
        });

        //soundIndex = images_number[turn];
        mediaPlayer = MediaPlayer.create(MainActivity.this, sounds[images_number[turn]]);
        mediaPlayer.start();

        //null stuff
        tv_status.setText("");

        image1.setEnabled(true);
        image2.setEnabled(true);
        image3.setEnabled(true);
        image4.setEnabled(true);
        image5.setEnabled(true);
        image6.setEnabled(true);
    }

    private void setImagesLv5(){
        //determine which is the correct answer 1-8
        Random r = new Random();
        correctAnswer = r.nextInt(8) + 1;

        //generate random wrong answers
        int wrongAnswer1, wrongAnswer2, wrongAnswer3, wrongAnswer4, wrongAnswer5, wrongAnswer6, wrongAnswer7;

        do{
            wrongAnswer1=r.nextInt(18);
        }while (wrongAnswer1 == images_number[turn]);

        do{
            wrongAnswer2 = r.nextInt(18);
        }while (wrongAnswer2 == images_number[turn] || wrongAnswer2==wrongAnswer1);

        do{
            wrongAnswer3 = r.nextInt(18);
        }while (wrongAnswer3 == images_number[turn] || wrongAnswer3==wrongAnswer2 || wrongAnswer3==wrongAnswer1);
        do{
            wrongAnswer4 = r.nextInt(18);
        }while (wrongAnswer4 == images_number[turn] || wrongAnswer4==wrongAnswer3 || wrongAnswer4==wrongAnswer2 || wrongAnswer4==wrongAnswer1);
        do{
            wrongAnswer5 = r.nextInt(18);
        }while (wrongAnswer5 == images_number[turn] || wrongAnswer5==wrongAnswer4 || wrongAnswer5==wrongAnswer3 || wrongAnswer5==wrongAnswer2 || wrongAnswer5==wrongAnswer1);
        do{
            wrongAnswer6 = r.nextInt(18);
        }while (wrongAnswer6 == images_number[turn] || wrongAnswer6==wrongAnswer5 || wrongAnswer6==wrongAnswer4 || wrongAnswer6==wrongAnswer3 || wrongAnswer6==wrongAnswer2 || wrongAnswer6==wrongAnswer1);
        do{
            wrongAnswer7 = r.nextInt(18);
        }while (wrongAnswer7 == images_number[turn] || wrongAnswer7==wrongAnswer6 || wrongAnswer7==wrongAnswer5 || wrongAnswer7==wrongAnswer4 || wrongAnswer7==wrongAnswer3 || wrongAnswer7==wrongAnswer2 || wrongAnswer7==wrongAnswer1);

        //setting images for all answers
        switch(correctAnswer){
            case 1:
                image1.setImageResource(images[images_number[turn]]);
                image2.setImageResource(images[wrongAnswer1]);
                image3.setImageResource(images[wrongAnswer2]);
                image4.setImageResource(images[wrongAnswer3]);
                image5.setImageResource(images[wrongAnswer4]);
                image6.setImageResource(images[wrongAnswer5]);
                image7.setImageResource(images[wrongAnswer6]);
                image8.setImageResource(images[wrongAnswer7]);
                break;
            case 2:
                image1.setImageResource(images[wrongAnswer1]);
                image2.setImageResource(images[images_number[turn]]);
                image3.setImageResource(images[wrongAnswer2]);
                image4.setImageResource(images[wrongAnswer3]);
                image5.setImageResource(images[wrongAnswer4]);
                image6.setImageResource(images[wrongAnswer5]);
                image7.setImageResource(images[wrongAnswer6]);
                image8.setImageResource(images[wrongAnswer7]);
                break;
            case 3:
                image1.setImageResource(images[wrongAnswer1]);
                image2.setImageResource(images[wrongAnswer2]);
                image3.setImageResource(images[images_number[turn]]);
                image4.setImageResource(images[wrongAnswer3]);
                image5.setImageResource(images[wrongAnswer4]);
                image6.setImageResource(images[wrongAnswer5]);
                image7.setImageResource(images[wrongAnswer6]);
                image8.setImageResource(images[wrongAnswer7]);
                break;
            case 4:
                image1.setImageResource(images[wrongAnswer1]);
                image2.setImageResource(images[wrongAnswer2]);
                image3.setImageResource(images[wrongAnswer3]);
                image4.setImageResource(images[images_number[turn]]);
                image5.setImageResource(images[wrongAnswer4]);
                image6.setImageResource(images[wrongAnswer5]);
                image7.setImageResource(images[wrongAnswer6]);
                image8.setImageResource(images[wrongAnswer7]);
                break;
            case 5:
                image1.setImageResource(images[wrongAnswer1]);
                image2.setImageResource(images[wrongAnswer2]);
                image3.setImageResource(images[wrongAnswer3]);
                image4.setImageResource(images[wrongAnswer4]);
                image5.setImageResource(images[images_number[turn]]);
                image6.setImageResource(images[wrongAnswer5]);
                image7.setImageResource(images[wrongAnswer6]);
                image8.setImageResource(images[wrongAnswer7]);
                break;
            case 6:
                image1.setImageResource(images[wrongAnswer1]);
                image2.setImageResource(images[wrongAnswer2]);
                image3.setImageResource(images[wrongAnswer3]);
                image4.setImageResource(images[wrongAnswer4]);
                image5.setImageResource(images[wrongAnswer5]);
                image6.setImageResource(images[images_number[turn]]);
                image7.setImageResource(images[wrongAnswer6]);
                image8.setImageResource(images[wrongAnswer7]);
                break;
            case 7:
                image1.setImageResource(images[wrongAnswer1]);
                image2.setImageResource(images[wrongAnswer2]);
                image3.setImageResource(images[wrongAnswer3]);
                image4.setImageResource(images[wrongAnswer4]);
                image5.setImageResource(images[wrongAnswer5]);
                image6.setImageResource(images[wrongAnswer6]);
                image7.setImageResource(images[images_number[turn]]);
                image8.setImageResource(images[wrongAnswer7]);
                break;
            case 8:
                image1.setImageResource(images[wrongAnswer1]);
                image2.setImageResource(images[wrongAnswer2]);
                image3.setImageResource(images[wrongAnswer3]);
                image4.setImageResource(images[wrongAnswer4]);
                image5.setImageResource(images[wrongAnswer5]);
                image6.setImageResource(images[wrongAnswer6]);
                image7.setImageResource(images[wrongAnswer7]);
                image8.setImageResource(images[images_number[turn]]);
                break;
        }

        //setting images for the question
        imageMain.setImageResource(mainImages[images_number[turn]]);

        tts = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("error", "This lang is not supported");
                    } else {
                        ConvertTextToSpeech();
                    }
                } else
                    Log.e("error", "Initilaziton filed!");
            }
        });

        //soundIndex = images_number[turn];
        mediaPlayer = MediaPlayer.create(MainActivity.this, sounds[images_number[turn]]);
        mediaPlayer.start();

        //null stuff
        tv_status.setText("");

        image1.setEnabled(true);
        image2.setEnabled(true);
        image3.setEnabled(true);
        image4.setEnabled(true);
        image5.setEnabled(true);
        image6.setEnabled(true);
        image7.setEnabled(true);
        image8.setEnabled(true);
    }


    public void chekEnd() {
        try
        {

            FileOutputStream fos = new FileOutputStream(strFilePath);
            DataOutputStream dos = new DataOutputStream(fos);

            /* This method writes specified int to output stream as 4 bytes value.
             */
            dos.writeInt(score);
            dos.close();
        }
        catch (IOException e){ System.out.println("IOException : " + e); }

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setMessage("Oyun Bitti, Skor: " + score);

        alertDialogBuilder.setPositiveButton("Quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog alertDialog =alertDialogBuilder.create();
        alertDialog.show();
    }

}
