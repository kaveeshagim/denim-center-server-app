package lk.weerathunga.denimcenter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lk.weerathunga.denimcenter.util.RegexPattern;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "price")
    private BigDecimal price;
    @Basic
    @Column(name = "code")
    @Pattern(regexp = "^\\d{4}$", message = "Invalid Number")
    private String code;
    @Basic
    @Column(name = "proddate")
    @RegexPattern(reg = "^\\d{2}-\\d{2}-\\d{2}$", msg = "Invalid Date Format")
    private Date proddate;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "image")
    private byte[] image;
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private Collection<Corder> corders;
    @ManyToOne
    @JoinColumn(name = "agecategory_id", referencedColumnName = "id", nullable = false)
    private Agecategory agecategory;
    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    private Type type;
    @ManyToOne
    @JoinColumn(name = "gender_id", referencedColumnName = "id", nullable = false)
    private Gender gender;
    @ManyToOne
    @JoinColumn(name = "color_id", referencedColumnName = "id", nullable = false)
    private Color color;
    @ManyToOne
    @JoinColumn(name = "size_id", referencedColumnName = "id", nullable = false)
    private Size size;
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private Collection<Productinventory> productinventories;
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private Collection<Productionorder> productionorders;

    // Getters and setters for the Product class
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getProddate() {
        return proddate;
    }

    public void setProddate(Date proddate) {
        this.proddate = proddate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(code, product.code) && Objects.equals(proddate, product.proddate) && Objects.equals(description, product.description) && Arrays.equals(image, product.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, price, code, proddate, description);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    public Collection<Corder> getCorders() {
        return corders;
    }

    public void setCorders(Collection<Corder> corders) {
        this.corders = corders;
    }

    public Agecategory getAgecategory() {
        return agecategory;
    }

    public void setAgecategory(Agecategory agecategory) {
        this.agecategory = agecategory;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Collection<Productinventory> getProductinventories() {
        return productinventories;
    }

    public void setProductinventories(Collection<Productinventory> productinventories) {
        this.productinventories = productinventories;
    }

    public Collection<Productionorder> getProductionorders() {
        return productionorders;
    }

    public void setProductionorders(Collection<Productionorder> productionorders) {
        this.productionorders = productionorders;
    }
}
