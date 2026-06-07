package com.starpattern.assistant;
import java.util.*;
public class PatternEngine {
    private final ArrayList<ResultRecord> history = new ArrayList<>();
    public void add(String planet){ history.add(new ResultRecord(planet, FamilyMapper.family(planet))); if(history.size()>5000) history.remove(0); }
    public Prediction predict(){
        if(history.size()<5) return new Prediction("Need more data",25,"SKIP");
        HashMap<String,Integer> counts=new HashMap<>();
        int start=Math.max(0, history.size()-30);
        for(int i=start;i<history.size();i++) counts.put(history.get(i).planet, counts.getOrDefault(history.get(i).planet,0)+1);
        String best="Manual Check"; int max=0; for(String k:counts.keySet()) if(counts.get(k)>max){max=counts.get(k);best=k;}
        int conf=Math.min(82, 35 + max*5);
        String signal= conf>=65?"SAFE/MEDIUM":(conf>=45?"RISK":"SKIP");
        return new Prediction(best, conf, signal);
    }
    public String lastPattern(){
        int n=history.size(); if(n<6) return "Pattern: collecting data";
        StringBuilder b=new StringBuilder("Last: "); for(int i=Math.max(0,n-8);i<n;i++) b.append(history.get(i).planet).append(" → ");
        return b.substring(0, Math.max(0,b.length()-3));
    }
}
