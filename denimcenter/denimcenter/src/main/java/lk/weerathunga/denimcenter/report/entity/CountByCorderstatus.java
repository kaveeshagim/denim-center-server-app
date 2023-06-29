package lk.weerathunga.denimcenter.report.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CountByCorderstatus {

    private Integer id;
    private String corderstatus;
    private Long count;
    private double percentage;

    public CountByCorderstatus() {  }

    public CountByCorderstatus(String corderstatus, Long count) {
        this.corderstatus = corderstatus;
        this.count = count;
    }

    public String getCorderstatus() {
        return corderstatus;
    }
    public void setCorderstatus(String corderstatus) {
        this.corderstatus = corderstatus;
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
