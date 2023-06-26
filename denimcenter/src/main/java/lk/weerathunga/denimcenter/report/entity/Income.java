package lk.weerathunga.denimcenter.report.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Income {

    private Integer id;
    private Date date;
    private BigDecimal amount;

    public Income() {  }

    public Income(Date date, BigDecimal amount) {
        this.date = date;
        this.amount = amount;

    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    public Integer getId() {
        return id;
    }
}
