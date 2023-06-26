package lk.weerathunga.denimcenter.report.dao;

import lk.weerathunga.denimcenter.report.entity.Expense;
import lk.weerathunga.denimcenter.report.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncomeDao extends JpaRepository<Income, Integer> {

    @Query(value = "SELECT NEW Income (o.date, SUM(o.amount)) FROM Customerpayment o GROUP BY o.date")
    List<Income> income();

}
