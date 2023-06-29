package lk.weerathunga.denimcenter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Orderstatus {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "orderstatus")
    private Collection<Corder> corders;
    @JsonIgnore
    @OneToMany(mappedBy = "orderstatus")
    private Collection<Porder> porders;

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
        Orderstatus that = (Orderstatus) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Collection<Corder> getCorders() {
        return corders;
    }

    public void setCorders(Collection<Corder> corders) {
        this.corders = corders;
    }

    public Collection<Porder> getPorders() {
        return porders;
    }

    public void setPorders(Collection<Porder> porders) {
        this.porders = porders;
    }
}
