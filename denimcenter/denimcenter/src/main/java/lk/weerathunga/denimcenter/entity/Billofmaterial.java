package lk.weerathunga.denimcenter.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Billofmaterial {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "qty")
    private String qty;
    @ManyToOne
    @JoinColumn(name = "material_id", referencedColumnName = "id", nullable = false)
    private Material material;
    @ManyToOne
    @JoinColumn(name = "productionorder_id", referencedColumnName = "id", nullable = false)
    private Productionorder productionorder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Billofmaterial that = (Billofmaterial) o;
        return Objects.equals(id, that.id) && Objects.equals(qty, that.qty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, qty);
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Productionorder getProductionorder() {
        return productionorder;
    }

    public void setProductionorder(Productionorder productionorder) {
        this.productionorder = productionorder;
    }
}
