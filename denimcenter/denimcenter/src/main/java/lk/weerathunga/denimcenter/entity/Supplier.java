package lk.weerathunga.denimcenter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lk.weerathunga.denimcenter.util.RegexPattern;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Supplier {
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
    @Column(name = "mobile")
    @Pattern(regexp = "^0\\d{9}$", message = "Invalid Mobile Number")
    private String mobile;
    @Basic
    @Column(name = "email")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid Email")
    private String email;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "dateadded")
    @RegexPattern(reg = "^\\d{2}-\\d{2}-\\d{2}$", msg = "Invalid Date Format")
    private Date dateadded;
    @JsonIgnore
    @OneToMany(mappedBy = "supplier")
    private Collection<Grn> grns;
    @JsonIgnore
    @OneToMany(mappedBy = "supplier")
    private Collection<Material> materials;
    @JsonIgnore
    @OneToMany(mappedBy = "supplier")
    private Collection<Porder> porders;
    @JsonIgnore
    @OneToMany(mappedBy = "supplier")
    private Collection<Supplierpayment> supplierpayments;
    @JsonIgnore
    @OneToMany(mappedBy = "supplier")
    private Collection<Supplierreturn> supplierreturns;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        Supplier supplier = (Supplier) o;
        return Objects.equals(id, supplier.id) && Objects.equals(companyname, supplier.companyname) && Objects.equals(name, supplier.name) && Objects.equals(address, supplier.address) && Objects.equals(nic, supplier.nic) && Objects.equals(mobile, supplier.mobile) && Objects.equals(email, supplier.email) && Objects.equals(city, supplier.city) && Objects.equals(dateadded, supplier.dateadded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyname, name, address, nic, mobile, email, city, dateadded);
    }

    public Collection<Grn> getGrns() {
        return grns;
    }

    public void setGrns(Collection<Grn> grns) {
        this.grns = grns;
    }

    public Collection<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(Collection<Material> materials) {
        this.materials = materials;
    }

    public Collection<Porder> getPorders() {
        return porders;
    }

    public void setPorders(Collection<Porder> porders) {
        this.porders = porders;
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
