package com.example.yugao.homework_try1;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yugao on 15/9/10.
 */
public class BookPreProcessor {
    public static JSONArray BookStringToJson(String book){
        //JSONObject unit_1 = new JSONObject();//这个jsonObject包含所有Lesson
        int lessonStart = 0;
        int lessonEnd = 0;
        JSONArray allLessonsJsonArray = new JSONArray();
        int count = 0;
        while(lessonStart != book.length()) {
            lessonStart = book.indexOf("Lesson",lessonStart);
            lessonEnd = book.indexOf("Lesson", lessonStart + 1); //因为subString方法是inclusive,exclusive所以不再减1

            Log.v("lessonend",String.valueOf(lessonEnd));
            JSONObject lessonJsonObject = new JSONObject();
            if(lessonEnd == -1)
                lessonEnd = book.length();

            String lessonString = book.substring(lessonStart, lessonEnd);
            int titleStart = lessonString.indexOf("Lesson") + 14;
            int titleEnd = lessonString.indexOf("First listen");
            //String title = lessonString.substring(titleStart, titleEnd);
            int mainStart = titleEnd;
            int mainEnd = lessonString.indexOf("New words");
            //String main = lessonString.substring(mainStart, mainEnd);
            int newWordStart = mainEnd;
            int newWordEnd = lessonString.indexOf("参考译文");
            //String newWord= lessonString.substring(newWordStart, newWordEnd);
            //String translation = lessonString.substring(newWordEnd,lessonString.length());
            try {
                lessonJsonObject.put("title",lessonString.substring(titleStart, titleEnd));
                lessonJsonObject.put("main",lessonString.substring(mainStart, mainEnd));
                lessonJsonObject.put("newWord",lessonString.substring(newWordStart, newWordEnd));
                lessonJsonObject.put("translation",lessonString.substring(newWordEnd,lessonString.length()));

                allLessonsJsonArray.put(lessonJsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            lessonStart = lessonEnd;
            count ++;
            Log.v("BookPreProcessor", "while loop count " + count);

        }
        Log.v("BookPreProcessor","out of while loop");
//        try {
//            unit_1.put("allLessons",allLessonsJsonArray);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        return allLessonsJsonArray;
    }
}
