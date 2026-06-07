package com.starpattern.assistant;
public class ResultRecord {
    public final long time; public final String planet; public final String family;
    public ResultRecord(String planet, String family){ this.time=System.currentTimeMillis(); this.planet=planet; this.family=family; }
}
