package lk.weerathunga.denimcenter.report.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CountByPorderstatus {

    private Integer id;
    private String porderstatus;
    private Long count;
    private double percentage;

    public CountByPorderstatus() {  }

    public CountByPorderstatus(String porderstatus, Long count) {
        this.porderstatus = porderstatus;
        this.count = count;
    }

    public String getPorderstatus() {
        return porderstatus;
    }
    public void setPorderstatus(String porderstatus) {
        this.porderstatus = porderstatus;
    }
    public Long getCount() {
        return count;
    }
    public void setCount(Long count) {
        this.count = count;
    }
    public double getPercentage() {
        return percentage;
    }
    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    public Integer getId() {
        return id;
    }
}
