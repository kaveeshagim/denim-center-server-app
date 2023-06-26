package lk.weerathunga.denimcenter.entity;

import lk.weerathunga.denimcenter.util.RegexPattern;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Materialmovement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "qty")
    private Integer qty;
    @Basic
    @Column(name = "date")
    @RegexPattern(reg = "^\\d{2}-\\d{2}-\\d{2}$", msg = "Invalid Date Format")
    private Date date;
    @Basic
    @Column(name = "remarks")
    private String remarks;
    @ManyToOne
    @JoinColumn(name = "materialinventory_id", referencedColumnName = "id", nullable = false)
    private Materialinventory materialinventory;
    @ManyToOne
    @JoinColumn(name = "movementtype_id", referencedColumnName = "id", nullable = false)
    private Movementtype movementtype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Materialmovement that = (Materialmovement) o;
        return Objects.equals(id, that.id) && Objects.equals(qty, that.qty) && Objects.equals(date, that.date) && Objects.equals(remarks, that.remarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, qty, date, remarks);
    }

    public Materialinventory getMaterialinventory() {
        return materialinventory;
    }

    public void setMaterialinventory(Materialinventory materialinventory) {
        this.materialinventory = materialinventory;
    }

    public Movementtype getMovementtype() {
        return movementtype;
    }

    public void setMovementtype(Movementtype movementtype) {
        this.movementtype = movementtype;
    }
}
