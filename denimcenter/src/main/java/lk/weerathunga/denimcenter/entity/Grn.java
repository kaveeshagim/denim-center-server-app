package lk.weerathunga.denimcenter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lk.weerathunga.denimcenter.util.RegexPattern;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Grn {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "number")
    @Pattern(regexp = "^\\d{4}$", message = "Invalid Number")
    private String number;
    @Basic
    @Column(name = "datecreated")
    @RegexPattern(reg = "^\\d{2}-\\d{2}-\\d{2}$", msg = "Invalid Date Format")
    private Date datecreated;
    @Basic
    @Column(name = "totalamount")
    private BigDecimal totalamount;
    @Basic
    @Column(name = "paidamount")
    private BigDecimal paidamount;
    @Basic
    @Column(name = "remarks")
    private String remarks;
    @ManyToOne
    @JoinColumn(name = "porder_id", referencedColumnName = "id", nullable = false)
    private Porder porder;
    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id", nullable = false)
    private Supplier supplier;
    @ManyToOne
    @JoinColumn(name = "grnstatus_id", referencedColumnName = "id", nullable = false)
    private Grnstatus grnstatus;
    @JsonIgnore
    @OneToMany(mappedBy = "grn")
    private Collection<Supplierpayment> supplierpayments;
    @JsonIgnore
    @OneToMany(mappedBy = "grn")
    private Collection<Supplierreturn> supplierreturns;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public BigDecimal getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }

    public BigDecimal getPaidamount() {
        return paidamount;
    }

    public void setPaidamount(BigDecimal paidamount) {
        this.paidamount = paidamount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grn grn = (Grn) o;
        return Objects.equals(id, grn.id) && Objects.equals(number, grn.number) && Objects.equals(datecreated, grn.datecreated) && Objects.equals(totalamount, grn.totalamount) && Objects.equals(paidamount, grn.paidamount) && Objects.equals(remarks, grn.remarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, datecreated, totalamount, paidamount, remarks);
    }

    public Porder getPorder() {
        return porder;
    }

    public void setPorder(Porder porder) {
        this.porder = porder;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Grnstatus getGrnstatus() {
        return grnstatus;
    }

    public void setGrnstatus(Grnstatus grnstatus) {
        this.grnstatus = grnstatus;
    }

    public Collection<Supplierpayment> getSupplierpayments() {
        return supplierpayments;
    }

    public void setSupplierpayments(Collection<Supplierpayment> supplierpayments) {
        this.supplierpayments = supplierpayments;
    }

    public Collection<Supplierreturn> getSupplierreturns() {
        return supplierreturns;
    }

    public void setSupplierreturns(Collection<Supplierreturn> supplierreturns) {
        this.supplierreturns = supplierreturns;
    }
}
