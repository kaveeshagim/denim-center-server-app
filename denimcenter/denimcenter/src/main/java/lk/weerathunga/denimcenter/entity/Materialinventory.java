package lk.weerathunga.denimcenter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lk.weerathunga.denimcenter.util.RegexPattern;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Materialinventory {
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
    @JoinColumn(name = "material_id", referencedColumnName = "id", nullable = false)
    private Material material;
    @JsonIgnore
    @OneToMany(mappedBy = "materialinventory")
    private Collection<Materialmovement> materialmovements;

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
        Materialinventory that = (Materialinventory) o;
        return Objects.equals(id, that.id) && Objects.equals(number, that.number) && Objects.equals(qty, that.qty) && Objects.equals(updateddate, that.updateddate) && Objects.equals(reorderlevel, that.reorderlevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, qty, updateddate, reorderlevel);
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Collection<Materialmovement> getMaterialmovements() {
        return materialmovements;
    }

    public void setMaterialmovements(Collection<Materialmovement> materialmovements) {
        this.materialmovements = materialmovements;
    }
}
