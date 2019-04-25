package antest.lotto_test;

import android.content.res.Resources;
import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class savedata {

    private Resources input_text;

    String str = Objects.requireNonNull(input_text).getText().toString();
    // 파일 생성
    File saveFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/camdata"); // 저장 경로
// 폴더 생성
if(!saveFile.exists())

    { // 폴더 없을 경우
        saveFile.mkdir(); // 폴더 생성
    }
try

    {
        long now = System.currentTimeMillis(); // 현재시간 받아오기
        Date date = new Date(now); // Date 객체 생성
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(date);

        BufferedWriter buf = new BufferedWriter(new FileWriter(saveFile + "/CarnumData.txt", true));
        buf.append(nowTime + " "); // 날짜 쓰기
        buf.append(str); // 파일 쓰기
        buf.newLine(); // 개행
        buf.close();
    } catch(
    FileNotFoundException e)

    {
        e.printStackTrace();
    } catch(
    IOException e)

    {
        e.printStackTrace();
    }


}
