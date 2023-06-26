package lk.weerathunga.denimcenter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Paymentmethod {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "paymentmethod")
    private Collection<Customerpayment> customerpayments;
    @JsonIgnore
    @OneToMany(mappedBy = "paymentmethod")
    private Collection<Supplierpayment> supplierpayments;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paymentmethod that = (Paymentmethod) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Collection<Customerpayment> getCustomerpayments() {
        return customerpayments;
    }

    public void setCustomerpayments(Collection<Customerpayment> customerpayments) {
        this.customerpayments = customerpayments;
    }

    public Collection<Supplierpayment> getSupplierpayments() {
        return supplierpayments;
    }

    public void setSupplierpayments(Collection<Supplierpayment> supplierpayments) {
        this.supplierpayments = supplierpayments;
    }
}
