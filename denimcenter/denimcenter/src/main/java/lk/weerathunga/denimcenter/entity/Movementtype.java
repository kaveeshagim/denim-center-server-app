package lk.weerathunga.denimcenter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Movementtype {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "movementtype")
    private Collection<Materialmovement> materialmovements;
    @JsonIgnore
    @OneToMany(mappedBy = "movementtype")
    private Collection<Productmovement> productmovements;

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
        Movementtype that = (Movementtype) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Collection<Materialmovement> getMaterialmovements() {
        return materialmovements;
    }

    public void setMaterialmovements(Collection<Materialmovement> materialmovements) {
        this.materialmovements = materialmovements;
    }

    public Collection<Productmovement> getProductmovements() {
        return productmovements;
    }

    public void setProductmovements(Collection<Productmovement> productmovements) {
        this.productmovements = productmovements;
    }
}
