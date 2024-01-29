package com.otcompany.models;

public class CallDto {

    private long startTime;
    private long endTime;
    private long duration;
    
    public long getStartTime() {
        return startTime;
    }
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    public long getEndTime() {
        return endTime;
    }
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
    public long getDuration() {
        return duration;
    }
    @Override
    public String toString() {
        return "CallsDTO [startTime=" + startTime + ", endTime=" + endTime + ", duration=" + duration + "]";
    }
    public void setDuration(long duration) {
        this.duration = duration;
    }

   
}
