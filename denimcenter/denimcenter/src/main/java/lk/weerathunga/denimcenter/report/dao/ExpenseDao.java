package lk.weerathunga.denimcenter.report.dao;

import lk.weerathunga.denimcenter.report.entity.CountByCorderstatus;
import lk.weerathunga.denimcenter.report.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ExpenseDao extends JpaRepository<Expense, Integer> {

    @Query(value = "SELECT NEW Expense (o.date, SUM(o.amount)) FROM Supplierpayment o GROUP BY o.date")
    List<Expense> expense();

    List<Expense> findByDate(Date date);
}
