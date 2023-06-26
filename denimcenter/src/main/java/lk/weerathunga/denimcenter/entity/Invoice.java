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
public class Invoice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "number")
    @Pattern(regexp = "^\\d{4}$", message = "Invalid Number")
    private String number;
    @Basic
    @Column(name = "totalamount")
    private BigDecimal totalamount;
    @Basic
    @Column(name = "paidamount")
    private BigDecimal paidamount;
    @Basic
    @Column(name = "datecreated")
    @RegexPattern(reg = "^\\d{2}-\\d{2}-\\d{2}$", msg = "Invalid Date Format")
    private Date datecreated;
    @Basic
    @Column(name = "remarks")
    private String remarks;
    @JsonIgnore
    @OneToMany(mappedBy = "invoice")
    private Collection<Customerpayment> customerpayments;
    @JsonIgnore
    @OneToMany(mappedBy = "invoice")
    private Collection<Customerreturn> customerreturns;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "invoicestatus_id", referencedColumnName = "id", nullable = false)
    private Invoicestatus invoicestatus;
    @ManyToOne
    @JoinColumn(name = "corder_id", referencedColumnName = "id", nullable = false)
    private Corder corder;

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

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
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
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id) && Objects.equals(number, invoice.number) && Objects.equals(totalamount, invoice.totalamount) && Objects.equals(paidamount, invoice.paidamount) && Objects.equals(datecreated, invoice.datecreated) && Objects.equals(remarks, invoice.remarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, totalamount, paidamount, datecreated, remarks);
    }

    public Collection<Customerpayment> getCustomerpayments() {
        return customerpayments;
    }

    public void setCustomerpayments(Collection<Customerpayment> customerpayments) {
        this.customerpayments = customerpayments;
    }

    public Collection<Customerreturn> getCustomerreturns() {
        return customerreturns;
    }

    public void setCustomerreturns(Collection<Customerreturn> customerreturns) {
        this.customerreturns = customerreturns;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Invoicestatus getInvoicestatus() {
        return invoicestatus;
    }

    public void setInvoicestatus(Invoicestatus invoicestatus) {
        this.invoicestatus = invoicestatus;
    }

    public Corder getCorder() {
        return corder;
    }

    public void setCorder(Corder corder) {
        this.corder = corder;
    }
}
