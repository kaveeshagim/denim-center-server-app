package lk.weerathunga.denimcenter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Material {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "unitofmeasure")
    private String unitofmeasure;
    @Basic
    @Column(name = "costperunit")
    private BigDecimal costperunit;
    @Basic
    @Column(name = "description")
    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "material")
    private Collection<Billofmaterial> billofmaterials;
    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id", nullable = false)
    private Supplier supplier;
    @JsonIgnore
    @OneToMany(mappedBy = "material")
    private Collection<Materialinventory> materialinventories;
    @JsonIgnore
    @OneToMany(mappedBy = "material")
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

    public String getUnitofmeasure() {
        return unitofmeasure;
    }

    public void setUnitofmeasure(String unitofmeasure) {
        this.unitofmeasure = unitofmeasure;
    }

    public BigDecimal getCostperunit() {
        return costperunit;
    }

    public void setCostperunit(BigDecimal costperunit) {
        this.costperunit = costperunit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return Objects.equals(id, material.id) && Objects.equals(name, material.name) && Objects.equals(unitofmeasure, material.unitofmeasure) && Objects.equals(costperunit, material.costperunit) && Objects.equals(description, material.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, unitofmeasure, costperunit, description);
    }

    public Collection<Billofmaterial> getBillofmaterials() {
        return billofmaterials;
    }

    public void setBillofmaterials(Collection<Billofmaterial> billofmaterials) {
        this.billofmaterials = billofmaterials;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Collection<Materialinventory> getMaterialinventories() {
        return materialinventories;
    }

    public void setMaterialinventories(Collection<Materialinventory> materialinventories) {
        this.materialinventories = materialinventories;
    }

    public Collection<Porder> getPorders() {
        return porders;
    }

    public void setPorders(Collection<Porder> porders) {
        this.porders = porders;
    }
}
