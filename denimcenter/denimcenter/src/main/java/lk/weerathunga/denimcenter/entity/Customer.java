package lk.weerathunga.denimcenter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lk.weerathunga.denimcenter.util.RegexPattern;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Customer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "companyname")
    private String companyname;
    @Basic
    @Column(name = "name")
    @Pattern(regexp = "^[A-Z][a-zA-Z]*( [A-Z][a-zA-Z]*)*$", message = "Invalid Name")
    private String name;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "nic")
    @Pattern(regexp = "^(([\\d]{9}[vVxX])|([\\d]{12}))$", message = "Invalid NIC")
    private String nic;
    @Basic
    @Column(name = "email")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid Email")
    private String email;
    @Basic
    @Column(name = "mobile")
    @Pattern(regexp = "^0\\d{9}$", message = "Invalid Mobile Number")
    private String mobile;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "dateadded")
    @RegexPattern(reg = "^\\d{2}-\\d{2}-\\d{2}$", msg = "Invalid Date Format")
    private Date dateadded;
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private Collection<Corder> corders;
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private Collection<Customerpayment> customerpayments;
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private Collection<Customerreturn> customerreturns;
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private Collection<Invoice> invoices;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDateadded() {
        return dateadded;
    }

    public void setDateadded(Date dateadded) {
        this.dateadded = dateadded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(companyname, customer.companyname) && Objects.equals(name, customer.name) && Objects.equals(address, customer.address) && Objects.equals(nic, customer.nic) && Objects.equals(email, customer.email) && Objects.equals(mobile, customer.mobile) && Objects.equals(city, customer.city) && Objects.equals(dateadded, customer.dateadded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyname, name, address, nic, email, mobile, city, dateadded);
    }

    public Collection<Corder> getCorders() {
        return corders;
    }

    public void setCorders(Collection<Corder> corders) {
        this.corders = corders;
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

    public Collection<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Collection<Invoice> invoices) {
        this.invoices = invoices;
    }
}
