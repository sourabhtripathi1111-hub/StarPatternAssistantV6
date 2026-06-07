package com.starpattern.assistant;
public class FamilyMapper {
    public static String family(String p){
        if(p==null) return "Unknown"; String x=p.toLowerCase();
        if(x.contains("x25")||x.contains("x15")||x.contains("yellow")||x.contains("orange")) return "Yellow/Big";
        if(x.contains("purple")) return "Purple/Small";
        if(x.contains("green")||x.contains("teal")) return "Green/Small";
        if(x.contains("x10")||x.contains("pink")) return "Pink/Big";
        if(x.contains("x50")) return "Jackpot/x50";
        return "Unknown";
    }
}
