package com.starpattern.assistant;
import android.content.*; import java.io.*; import java.text.*; import java.util.*;
public class CsvStore {
    public static File file(Context c){ return new File(c.getExternalFilesDir(null), "star_pattern_data.csv"); }
    public static void append(Context c, String planet) throws IOException{
        File f=file(c); boolean exists=f.exists();
        FileWriter w=new FileWriter(f,true);
        if(!exists) w.write("time,planet,family\n");
        String time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.US).format(new Date());
        w.write(time+","+planet+","+FamilyMapper.family(planet)+"\n"); w.close();
    }
}
