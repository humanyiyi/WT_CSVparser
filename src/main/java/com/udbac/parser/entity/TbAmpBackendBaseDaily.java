package com.udbac.parser.entity;

/**
 * Created by 43890 on 2016/9/19.
 */
public class TbAmpBackendBaseDaily {
    private String createDate;
    private String mic;
    private String visits;
    private String visitor;
    private String pv;
    private String click;
    private String bounceVisit;
    private String viewTime;

    public TbAmpBackendBaseDaily(String createDate, String mic, String visits,
                                 String visitor, String pv, String click, String bounceVisit, String viewTime) {
        this.createDate = createDate;
        this.mic = mic;
        this.visits = visits;
        this.visitor = visitor;
        this.pv = pv;
        this.click = click;
        this.bounceVisit = bounceVisit;
        this.viewTime = viewTime;
    }

    public TbAmpBackendBaseDaily() {
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getMic() {
        return mic;
    }

    public void setMic(String mic) {
        this.mic = mic;
    }

    public String getVisits() {
        return visits;
    }

    public void setVisits(String visits) {
        this.visits = visits;
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    public String getPv() {
        return pv;
    }

    public void setPv(String pv) {
        this.pv = pv;
    }

    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }

    public String getBounceVisit() {
        return bounceVisit;
    }

    public void setBounceVisit(String bounceVisit) {
        this.bounceVisit = bounceVisit;
    }

    public String getViewTime() {
        return viewTime;
    }

    public void setViewTime(String viewTime) {
        this.viewTime = viewTime;
    }

    @Override
//    public String toString() {
//        return "TbAmpBackendBaseDaily{" +
//                "createDate='" + createDate + '\'' +
//                ", mic='" + mic + '\'' +
//                ", visits='" + visits + '\'' +
//                ", visitor='" + visitor + '\'' +
//                ", pv='" + pv + '\'' +
//                ", click='" + click + '\'' +
//                ", bounceVisit='" + bounceVisit + '\'' +
//                ", viewTime='" + viewTime + '\'' +
//                '}';
//    }
    
    public String toString() {
        return
                createDate +
                ", '" + mic + '\'' +
                "," + visits + 
                "," + visitor + 
                ", " + pv + 
                "," + click  +
                ", " + bounceVisit + 
                ", " + viewTime ;
    }
}
