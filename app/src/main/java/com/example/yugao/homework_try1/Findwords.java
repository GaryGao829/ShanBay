package com.example.yugao.homework_try1;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by yugao on 15/8/28.
 */
public class Findwords {
    ArrayList<String> word0 = new ArrayList<>();
    ArrayList<String> word1 = new ArrayList<>();
    ArrayList<String> word2 = new ArrayList<>();
    ArrayList<String> word3 = new ArrayList<>();
    ArrayList<String> word4 = new ArrayList<>();
    ArrayList<String> word5 = new ArrayList<>();
    ArrayList allword[] = {word0,word1,word2,word3,word4,word5};


    public void preProcessWordlist(InputStream wordInputStream){
        InputStreamReader inputStreamReader = null;
        try{
            inputStreamReader = new InputStreamReader(wordInputStream,"UTF-8");
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String line;
        String[] linesplit;
        try{
            while((line = reader.readLine())!=""){
                Log.v("wordlist every line ",line);
                linesplit = line.split("\t");
                Log.v("linesplie[0] is ",linesplit[0]);
                Log.v("linesplie[1] is ",linesplit[1]);

                switch (linesplit[1] ){
                    case "0":{
                        allword[0].add(linesplit[0]);
                        //Log.v("wordlist","nan du shi 0"+ linesplit[0]);
                    }
                    case "1":allword[1].add(linesplit[0]);
                    case "2":allword[2].add(linesplit[0]);
                    case "3":allword[3].add(linesplit[0]);
                    case "4":allword[4].add(linesplit[0]);
                    case "5":allword[5].add(linesplit[0]);
                    default:{
                        Log.v("wordlist","wordlist read error"+line);

                    }
                }
            }
        }catch(Exception e){
        }
    }
//    public static String getString(InputStream inputStream){
//        InputStreamReader inputStreamReader = null;
//        try{
//            inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
//        }catch (UnsupportedEncodingException e){
//            e.printStackTrace();
//        }
//        BufferedReader reader = new BufferedReader(inputStreamReader);
//        StringBuffer sb = new StringBuffer("");
//        String line;
//        try {
//            while((line = reader.readLine())!=null){
//                sb.append(line);
//                sb.append("\n");
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        return sb.toString();
//    }


}
