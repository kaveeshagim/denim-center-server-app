package lk.weerathunga.denimcenter.report;

import lk.weerathunga.denimcenter.entity.Productionstatus;
import lk.weerathunga.denimcenter.report.dao.CountByCorderstatusDao;
import lk.weerathunga.denimcenter.report.dao.CountByPorderstatusDao;
import lk.weerathunga.denimcenter.report.dao.CountByProductionstatusDao;
import lk.weerathunga.denimcenter.report.dao.ExpenseDao;
import lk.weerathunga.denimcenter.report.entity.CountByCorderstatus;
import lk.weerathunga.denimcenter.report.entity.CountByPorderstatus;
import lk.weerathunga.denimcenter.report.entity.CountByProductionstatus;
import lk.weerathunga.denimcenter.report.entity.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.temporal.IsoFields;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;


@CrossOrigin
@RestController
@RequestMapping(value = "/reports")
public class ReportController {
    @Autowired
    private CountByProductionstatusDao countByProductionstatusdao;
    @Autowired
    private CountByCorderstatusDao countByCorderstatusdao;
    @Autowired
    private CountByPorderstatusDao countByPorderstatusdao;
    @Autowired
    private ExpenseDao expensedao;

    @GetMapping(path = "/countbyproductionstatus", produces = "application/json")
    public List<CountByProductionstatus> getCountByProductionstatus() {

        List<CountByProductionstatus> productionstatuses = this.countByProductionstatusdao.countByProductionstatus();
        long totalCount = 0;

        for (CountByProductionstatus countByProductionstatus : productionstatuses) {
            totalCount += countByProductionstatus.getCount();
        }

        for (CountByProductionstatus countByProductionstatus : productionstatuses) {
            long count = countByProductionstatus.getCount();
            double percentage = (double) count / totalCount * 100;
            percentage = Math.round(percentage * 100.0) / 100.0;
            countByProductionstatus.setPercentage(percentage);
        }

        return productionstatuses;
    }

    @GetMapping(path = "/countbycorderstatus", produces = "application/json")
    public List<CountByCorderstatus> getCountByCorderstatus() {

        List<CountByCorderstatus> corderstatuses = this.countByCorderstatusdao.countByCorderstatus();
        long totalCount = 0;

        for (CountByCorderstatus countByCorderstatus : corderstatuses) {
            totalCount += countByCorderstatus.getCount();
        }

        for (CountByCorderstatus countByCorderstatus : corderstatuses) {
            long count = countByCorderstatus.getCount();
            double percentage = (double) count / totalCount * 100;
            percentage = Math.round(percentage * 100.0) / 100.0;
            countByCorderstatus.setPercentage(percentage);
        }

        return corderstatuses;
    }

    @GetMapping(path = "/countbyporderstatus", produces = "application/json")
    public List<CountByPorderstatus> getCountByPorderstatus() {

        List<CountByPorderstatus> porderstatuses = this.countByPorderstatusdao.countByPorderstatus();
        long totalCount = 0;

        for (CountByPorderstatus countByPorderstatus : porderstatuses) {
            totalCount += countByPorderstatus.getCount();
        }

        for (CountByPorderstatus countByPorderstatus : porderstatuses) {
            long count = countByPorderstatus.getCount();
            double percentage = (double) count / totalCount * 100;
            percentage = Math.round(percentage * 100.0) / 100.0;
            countByPorderstatus.setPercentage(percentage);
        }

        return porderstatuses;
    }

    @GetMapping(path = "/expense", produces = "application/json")
    public List<Expense> getExpense() {
        return this.expensedao.expense();
    }

    @GetMapping(path = "/dailyexpenses", produces = "application/json")
    public List<Expense> getDailyExpenses() {
        List<Expense> expenses = this.expensedao.expense();
        Map<Date, BigDecimal> dailyExpensesMap = new HashMap<>();

        // Calculate daily expenses by summing up individual expenses
        for (Expense expense : expenses) {
            Date date = expense.getDate();
            BigDecimal amount = expense.getAmount();

            BigDecimal currentAmount = dailyExpensesMap.getOrDefault(date, BigDecimal.ZERO);
            BigDecimal updatedAmount = currentAmount.add(amount);
            dailyExpensesMap.put(date, updatedAmount);
        }

        // Convert the daily expenses map into a list of DailyExpense objects
        List<Expense> dailyExpenses = new ArrayList<>();
        for (Map.Entry<Date, BigDecimal> entry : dailyExpensesMap.entrySet()) {
            Date date = entry.getKey();
            BigDecimal amount = entry.getValue();
            Expense dailyExpense = new Expense(date, amount);
            dailyExpenses.add(dailyExpense);
        }

        return dailyExpenses;
    }

    /*@GetMapping(path = "/weeklyexpenses", produces = "application/json")
    public List<Expense> getWeeklyExpenses() {
        List<Expense> expenses = this.expensedao.expense();
        Map<Date, BigDecimal> weeklyExpensesMap = new HashMap<>();

        // Calculate weekly expenses by summing up individual expenses
        for (Expense expense : expenses) {
            Date expenseDate = expense.getDate();
            BigDecimal amount = expense.getAmount();

            Date weekStartDate = getWeekStartDate(expenseDate);
            BigDecimal currentAmount = weeklyExpensesMap.getOrDefault(weekStartDate, BigDecimal.ZERO);
            BigDecimal updatedAmount = currentAmount.add(amount);
            weeklyExpensesMap.put(weekStartDate, updatedAmount);
        }

        // Convert the weekly expenses map into a list of WeeklyExpense objects
        List<Expense> weeklyExpenses = new ArrayList<>();
        for (Map.Entry<Date, BigDecimal> entry : weeklyExpensesMap.entrySet()) {
            Date weekStartDate = entry.getKey();
            BigDecimal amount = entry.getValue();
            Expense weeklyExpense = new Expense(weekStartDate, amount);
            weeklyExpenses.add(weeklyExpense);
        }

        return weeklyExpenses;
    }

    private Date getWeekStartDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }*/

    @GetMapping(path = "/weeklyexpenses", produces = "application/json")
    public Map<String, BigDecimal> getWeeklyExpenses() {
        List<Expense> expenses = this.expensedao.expense();
        Map<Date, BigDecimal> weeklyExpensesMap = new HashMap<>();

        // Calculate weekly expenses by summing up individual expenses
        for (Expense expense : expenses) {
            Date expenseDate = expense.getDate();
            BigDecimal amount = expense.getAmount();

            Date weekStartDate = getWeekStartDate(expenseDate);
            BigDecimal currentAmount = weeklyExpensesMap.getOrDefault(weekStartDate, BigDecimal.ZERO);
            BigDecimal updatedAmount = currentAmount.add(amount);
            weeklyExpensesMap.put(weekStartDate, updatedAmount);
        }

        // Convert the weekly expenses map into a map with formatted date range as key and expenses as value
        Map<String, BigDecimal> weeklyExpenses = new HashMap<>();
        for (Map.Entry<Date, BigDecimal> entry : weeklyExpensesMap.entrySet()) {
            Date weekStartDate = entry.getKey();
            BigDecimal amount = entry.getValue();

            Date weekEndDate = getWeekEndDate(weekStartDate);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String expenseRange = dateFormat.format(weekStartDate) + " - " + dateFormat.format(weekEndDate);
            weeklyExpenses.put(expenseRange, amount);
        }

        return weeklyExpenses;
    }

    private Date getWeekStartDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private Date getWeekEndDate(Date weekStartDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(weekStartDate);
        calendar.add(Calendar.DATE, 6); // Add 6 days to get the week end date (assuming a 7-day week)
        return calendar.getTime();
    }




    @GetMapping(path = "/monthlyexpenses", produces = "application/json")
    public List<Expense> getMonthlyExpenses() {
        List<Expense> expenses = this.expensedao.expense();
        Map<Date, BigDecimal> monthlyExpensesMap = new HashMap<>();

        // Calculate monthly expenses by summing up individual expenses
        for (Expense expense : expenses) {
            Date expenseDate = expense.getDate();
            BigDecimal amount = expense.getAmount();

            Date monthStartDate = getMonthStartDate(expenseDate);
            BigDecimal currentAmount = monthlyExpensesMap.getOrDefault(monthStartDate, BigDecimal.ZERO);
            BigDecimal updatedAmount = currentAmount.add(amount);
            monthlyExpensesMap.put(monthStartDate, updatedAmount);
        }

        // Convert the monthly expenses map into a list of MonthlyExpense objects
        List<Expense> monthlyExpenses = new ArrayList<>();
        for (Map.Entry<Date, BigDecimal> entry : monthlyExpensesMap.entrySet()) {
            Date monthStartDate = entry.getKey();
            BigDecimal amount = entry.getValue();
            Expense monthlyExpense = new Expense(monthStartDate, amount);
            monthlyExpenses.add(monthlyExpense);
        }

        return monthlyExpenses;
    }

    private Date getMonthStartDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    @GetMapping(path = "/yearlyexpenses", produces = "application/json")
    public Map<Integer, BigDecimal> getYearlyExpenses() {
        List<Expense> expenses = this.expensedao.expense();
        Map<Integer, BigDecimal> yearlyExpensesMap = new HashMap<>();

        // Calculate yearly expenses by summing up individual expenses
        for (Expense expense : expenses) {
            Date expenseDate = expense.getDate();
            BigDecimal amount = expense.getAmount();

            int year = getYear(expenseDate);
            BigDecimal currentAmount = yearlyExpensesMap.getOrDefault(year, BigDecimal.ZERO);
            BigDecimal updatedAmount = currentAmount.add(amount);
            yearlyExpensesMap.put(year, updatedAmount);
        }

        return yearlyExpensesMap;
    }

    private int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }








}






