package lk.weerathunga.denimcenter.report.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CountByProductionstatus {

    private Integer id;
    private String productionstatus;
    private Long count;
    private double percentage;

    public CountByProductionstatus() {  }

    public CountByProductionstatus(String productionstatus, Long count) {
        this.productionstatus = productionstatus;
        this.count = count;
    }

    public String getProductionstatus() {
        return productionstatus;
    }
    public void setProductionstatus(String productionstatus) {
        this.productionstatus = productionstatus;
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
