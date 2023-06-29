package lk.weerathunga.denimcenter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lk.weerathunga.denimcenter.util.RegexPattern;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Productionorder {
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
    private String qty;
    @Basic
    @Column(name = "orderdate")
    @RegexPattern(reg = "^\\d{2}-\\d{2}-\\d{2}$", msg = "Invalid Date Format")
    private Date orderdate;
    @Basic
    @Column(name = "requireddate")
    @RegexPattern(reg = "^\\d{2}-\\d{2}-\\d{2}$", msg = "Invalid Date Format")
    private Date requireddate;
    @JsonIgnore
    @OneToMany(mappedBy = "productionorder")
    private Collection<Billofmaterial> billofmaterials;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;
    @ManyToOne
    @JoinColumn(name = "productionstatus_id", referencedColumnName = "id", nullable = false)
    private Productionstatus productionstatus;

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

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public Date getRequireddate() {
        return requireddate;
    }

    public void setRequireddate(Date requireddate) {
        this.requireddate = requireddate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Productionorder that = (Productionorder) o;
        return Objects.equals(id, that.id) && Objects.equals(number, that.number) && Objects.equals(qty, that.qty) && Objects.equals(orderdate, that.orderdate) && Objects.equals(requireddate, that.requireddate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, qty, orderdate, requireddate);
    }

    public Collection<Billofmaterial> getBillofmaterials() {
        return billofmaterials;
    }

    public void setBillofmaterials(Collection<Billofmaterial> billofmaterials) {
        this.billofmaterials = billofmaterials;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Productionstatus getProductionstatus() {
        return productionstatus;
    }

    public void setProductionstatus(Productionstatus productionstatus) {
        this.productionstatus = productionstatus;
    }
}
