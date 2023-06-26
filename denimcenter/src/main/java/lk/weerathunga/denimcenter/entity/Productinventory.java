package lk.weerathunga.denimcenter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lk.weerathunga.denimcenter.util.RegexPattern;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
public class Productinventory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "number")
    @Pattern(regexp = "^\\d{4}$", message = "Invalid Number")
    private String number;
    @Basic
    @Column(name = "qty")
    private Integer qty;
    @Basic
    @Column(name = "updateddate")
    @RegexPattern(reg = "^\\d{2}-\\d{2}-\\d{2}$", msg = "Invalid Date Format")
    private Date updateddate;
    @Basic
    @Column(name = "reorderlevel")
    private Integer reorderlevel;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;
    @JsonIgnore
    @OneToMany(mappedBy = "productinventory")
    private Collection<Productmovement> productmovements;

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

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Date getUpdateddate() {
        return updateddate;
    }

    public void setUpdateddate(Date updateddate) {
        this.updateddate = updateddate;
    }

    public Integer getReorderlevel() {
        return reorderlevel;
    }

    public void setReorderlevel(Integer reorderlevel) {
        this.reorderlevel = reorderlevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Productinventory that = (Productinventory) o;
        return Objects.equals(id, that.id) && Objects.equals(number, that.number) && Objects.equals(qty, that.qty) && Objects.equals(updateddate, that.updateddate) && Objects.equals(reorderlevel, that.reorderlevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, qty, updateddate, reorderlevel);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Collection<Productmovement> getProductmovements() {
        return productmovements;
    }

    public void setProductmovements(Collection<Productmovement> productmovements) {
        this.productmovements = productmovements;
    }
}
