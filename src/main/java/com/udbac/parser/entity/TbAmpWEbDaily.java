package com.udbac.parser.entity;

/**
 * Created by root on 2016/9/28.
 */
public class TbAmpWEbDaily {
    private String mcid;
    private String event;
    private String visits;
    private String visitRate;
    private String click;
    private String pageview;

    public String getMcid() {
        return mcid;
    }
    public void setMcid(String mcid) {
        this.mcid = mcid;
    }
    public String getEvent(){
        return event;
    }
    public void setEvent(String event){
        this.event = event;
    }

    public String getVisits(){
        return visits;
    }
    public void setVisits(String visits){
        this.visits = visits;
    }

    public String getVisitRate(){
        return visitRate;
    }
    public void setVisitRate(String visitRate){
        this.visitRate = visitRate;
    }

    public String getClick(){
        return click;
    }
    public void setClick(String click){
        this.click= click;
    }
    public String getPageview(){
        return pageview;
    }
    public void setPageview(String pageview){
        this.pageview = pageview;
    }

    public String toString(){
        return "'"+mcid + '\''+
                ", '" + event + '\'' +
                ", " + visits +
                ", " + visitRate +
                "," + click +
                "," + pageview ;
    }
}
