package lk.weerathunga.denimcenter.entity;

import lk.weerathunga.denimcenter.util.RegexPattern;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Customerreturn {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "date")
    @RegexPattern(reg = "^\\d{2}-\\d{2}-\\d{2}$", msg = "Invalid Date Format")
    private Date date;
    @Basic
    @Column(name = "reason")
    private String reason;
    @Basic
    @Column(name = "qty")
    private String qty;
    @Basic
    @Column(name = "returncost")
    private BigDecimal returncost;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "invoice_id", referencedColumnName = "id", nullable = false)
    private Invoice invoice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public BigDecimal getReturncost() {
        return returncost;
    }

    public void setReturncost(BigDecimal returncost) {
        this.returncost = returncost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customerreturn that = (Customerreturn) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(reason, that.reason) && Objects.equals(qty, that.qty) && Objects.equals(returncost, that.returncost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, reason, qty, returncost);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
