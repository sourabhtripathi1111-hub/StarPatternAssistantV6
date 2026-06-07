package com.starpattern.assistant;
import android.app.*; import android.os.*; import android.content.*; import android.provider.Settings; import android.net.Uri; import android.view.*; import android.widget.*; import java.io.*;

public class MainActivity extends Activity {
    PatternEngine engine=new PatternEngine(); TextView status;
    String[] planets={"Purple x5","Orange/Yellow x5","Light Green x5","Teal/Green x5","Pink x10","Yellow x15","Purple x25","Pink/Purple x50"};
    public void onCreate(Bundle b){ super.onCreate(b); buildUi(); }
    void buildUi(){
        ScrollView sv=new ScrollView(this); LinearLayout root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setPadding(24,24,24,24); sv.addView(root);
        TextView title=new TextView(this); title.setText("Star Pattern Assistant V6\nManual + Direct Reading Base"); title.setTextSize(22); root.addView(title);
        status=new TextView(this); status.setText("Ready. Direct OCR/Icon matching module will be added after APK build test."); status.setTextSize(16); root.addView(status);
        Button overlay=new Button(this); overlay.setText("Enable Overlay Permission"); overlay.setOnClickListener(v-> startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:"+getPackageName())))); root.addView(overlay);
        Button direct=new Button(this); direct.setText("Direct App Reading Guide"); direct.setOnClickListener(v-> show("Direct Reading", "Next module: MediaProjection screenshot + crop area + OCR/icon matching. Manual correction will stay available.")); root.addView(direct);
        for(String p:planets){ Button bt=new Button(this); bt.setText("Add Result: "+p); bt.setOnClickListener(v->addResult(((Button)v).getText().toString().replace("Add Result: ",""))); root.addView(bt); }
        Button pred=new Button(this); pred.setText("Predict / Check Signal"); pred.setOnClickListener(v->{ Prediction p=engine.predict(); status.setText("Prediction: "+p.planet+"\nConfidence: "+p.confidence+"%\nSignal: "+p.signal+"\n"+engine.lastPattern()); }); root.addView(pred);
        Button export=new Button(this); export.setText("Show CSV Export Location"); export.setOnClickListener(v-> show("CSV Export", CsvStore.file(this).getAbsolutePath())); root.addView(export);
        Button ai=new Button(this); ai.setText("AI Pattern Help Prompt"); ai.setOnClickListener(v-> show("AI Recheck", AiHelper.prompt())); root.addView(ai);
        setContentView(sv);
    }
    void addResult(String p){ try{ engine.add(p); CsvStore.append(this,p); status.setText("Saved: "+p+"\nFamily: "+FamilyMapper.family(p)+"\n"+engine.lastPattern()); }catch(Exception e){ status.setText("Save error: "+e.getMessage()); } }
    void show(String t,String m){ new AlertDialog.Builder(this).setTitle(t).setMessage(m).setPositiveButton("OK",null).show(); }
}
