package lk.weerathunga.denimcenter.entity;

import lk.weerathunga.denimcenter.util.RegexPattern;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Supplierreturn {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "date")
    @RegexPattern(reg = "^\\d{2}-\\d{2}-\\d{2}$", msg = "Invalid Date Format")
    private Date date;
    @Basic
    @Column(name = "qty")
    private String qty;
    @Basic
    @Column(name = "reason")
    private String reason;
    @Basic
    @Column(name = "returncost")
    private BigDecimal returncost;
    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id", nullable = false)
    private Supplier supplier;
    @ManyToOne
    @JoinColumn(name = "grn_id", referencedColumnName = "id", nullable = false)
    private Grn grn;

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

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
        Supplierreturn that = (Supplierreturn) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(qty, that.qty) && Objects.equals(reason, that.reason) && Objects.equals(returncost, that.returncost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, qty, reason, returncost);
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Grn getGrn() {
        return grn;
    }

    public void setGrn(Grn grn) {
        this.grn = grn;
    }
}
