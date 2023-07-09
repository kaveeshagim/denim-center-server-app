package lk.weerathunga.denimcenter.report;

import lk.weerathunga.denimcenter.entity.Productionstatus;
import lk.weerathunga.denimcenter.report.dao.*;
import lk.weerathunga.denimcenter.report.entity.*;
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
    @Autowired
    private IncomeDao incomedao;
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

    @GetMapping(path = "/income", produces = "application/json")
    public Map<Date, BigDecimal> getIncome() {
        List<Income> incomes = this.incomedao.income();
        List<Expense> expenses = this.expensedao.expense();

        Map<Date, BigDecimal> incomeMap = new HashMap<>();

        for (Income income : incomes) {
            Date date = income.getDate();
            BigDecimal amount = income.getAmount();
            incomeMap.put(date, amount);
        }

        for (Expense expense : expenses) {
            Date date = expense.getDate();
            BigDecimal amount = expense.getAmount();
            incomeMap.merge(date, amount, BigDecimal::subtract);
        }

        return incomeMap;
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

    @GetMapping(path = "/dailyincome", produces = "application/json")
    public List<Income> getDailyIncome() {
        List<Income> incomes = this.incomedao.income();
        List<Expense> expenses = this.expensedao.expense();

        Map<Date, BigDecimal> dailyIncomeMap = new HashMap<>();

        // Calculate daily income by summing up individual incomes
        for (Income income : incomes) {
            Date date = income.getDate();
            BigDecimal amount = income.getAmount();

            BigDecimal currentAmount = dailyIncomeMap.getOrDefault(date, BigDecimal.ZERO);
            BigDecimal updatedAmount = currentAmount.add(amount);
            dailyIncomeMap.put(date, updatedAmount);
        }

        // Subtract daily expenses from daily income
        for (Expense expense : expenses) {
            Date date = expense.getDate();
            BigDecimal amount = expense.getAmount();

            BigDecimal currentAmount = dailyIncomeMap.getOrDefault(date, BigDecimal.ZERO);
            BigDecimal updatedAmount = currentAmount.subtract(amount);
            dailyIncomeMap.put(date, updatedAmount);
        }

        // Convert the daily income map into a list of Income objects
        List<Income> dailyIncome = new ArrayList<>();
        for (Map.Entry<Date, BigDecimal> entry : dailyIncomeMap.entrySet()) {
            Date date = entry.getKey();
            BigDecimal amount = entry.getValue();
            Income dailyIncomeEntry = new Income(date, amount);
            dailyIncome.add(dailyIncomeEntry);
        }

        return dailyIncome;
    }

    @GetMapping(path = "/weeklyexpenses", produces = "application/json")
    public List<Expense> getWeeklyExpenses() {
        List<Expense> expenses = this.expensedao.expense();
        Map<Date, BigDecimal> weeklyExpensesMap = new HashMap<>();

        // Calculate weekly expenses by summing up individual expenses
        for (Expense expense : expenses) {
            Date expenseDate = expense.getDate();
            BigDecimal amount = expense.getAmount();

            Date week = getWeek(expenseDate);
            BigDecimal currentAmount = weeklyExpensesMap.getOrDefault(week, BigDecimal.ZERO);
            BigDecimal updatedAmount = currentAmount.add(amount);
            weeklyExpensesMap.put(week, updatedAmount);
        }

        // Convert the weekly expenses map into a list of Expense objects
        List<Expense> weeklyExpenses = new ArrayList<>();
        for (Map.Entry<Date, BigDecimal> entry : weeklyExpensesMap.entrySet()) {
            Date week = entry.getKey();
            BigDecimal amount = entry.getValue();
            Expense weeklyExpense = new Expense(week, amount);
            weeklyExpenses.add(weeklyExpense);
        }

        return weeklyExpenses;
    }

    private Date getWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);

        // Create a new Calendar instance and set it to the start of the week
        Calendar startOfWeek = Calendar.getInstance();
        startOfWeek.clear();
        startOfWeek.set(Calendar.YEAR, year);
        startOfWeek.set(Calendar.WEEK_OF_YEAR, weekOfYear);
        startOfWeek.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        startOfWeek.set(Calendar.HOUR_OF_DAY, 0);
        startOfWeek.set(Calendar.MINUTE, 0);
        startOfWeek.set(Calendar.SECOND, 0);
        startOfWeek.set(Calendar.MILLISECOND, 0);

        return startOfWeek.getTime();
    }

    @GetMapping(path = "/weeklyincome", produces = "application/json")
    public List<Income> getWeeklyIncome() {
        List<Income> incomes = this.incomedao.income();
        List<Expense> expenses = this.expensedao.expense();

        Map<Date, BigDecimal> weeklyIncomeMap = new HashMap<>();

        // Calculate weekly income by summing up individual incomes
        for (Income income : incomes) {
            Date incomeDate = income.getDate();
            BigDecimal amount = income.getAmount();

            Date week = getWeek(incomeDate);
            BigDecimal currentAmount = weeklyIncomeMap.getOrDefault(week, BigDecimal.ZERO);
            BigDecimal updatedAmount = currentAmount.add(amount);
            weeklyIncomeMap.put(week, updatedAmount);
        }

        // Subtract weekly expenses from weekly income
        for (Expense expense : expenses) {
            Date expenseDate = expense.getDate();
            BigDecimal amount = expense.getAmount();

            Date week = getWeek(expenseDate);
            BigDecimal currentAmount = weeklyIncomeMap.getOrDefault(week, BigDecimal.ZERO);
            BigDecimal updatedAmount = currentAmount.subtract(amount);
            weeklyIncomeMap.put(week, updatedAmount);
        }

        // Convert the weekly income map into a list of Income objects
        List<Income> weeklyIncome = new ArrayList<>();
        for (Map.Entry<Date, BigDecimal> entry : weeklyIncomeMap.entrySet()) {
            Date week = entry.getKey();
            BigDecimal amount = entry.getValue();
            Income weeklyIncomeEntry = new Income(week, amount);
            weeklyIncome.add(weeklyIncomeEntry);
        }

        return weeklyIncome;
    }


    @GetMapping(path = "/monthlyexpenses", produces = "application/json")
    public List<Expense> getMonthlyExpenses() {
        List<Expense> expenses = this.expensedao.expense();
        Map<Date, BigDecimal> monthlyExpensesMap = new HashMap<>();

        // Calculate monthly expenses by summing up individual expenses
        for (Expense expense : expenses) {
            Date expenseDate = expense.getDate();
            BigDecimal amount = expense.getAmount();

            Date month = getMonth(expenseDate);
            BigDecimal currentAmount = monthlyExpensesMap.getOrDefault(month, BigDecimal.ZERO);
            BigDecimal updatedAmount = currentAmount.add(amount);
            monthlyExpensesMap.put(month, updatedAmount);
        }

        // Convert the monthly expenses map into a list of Expense objects
        List<Expense> monthlyExpenses = new ArrayList<>();
        for (Map.Entry<Date, BigDecimal> entry : monthlyExpensesMap.entrySet()) {
            Date month = entry.getKey();
            BigDecimal amount = entry.getValue();
            Expense monthlyExpense = new Expense(month, amount);
            monthlyExpenses.add(monthlyExpense);
        }

        return monthlyExpenses;
    }

    private Date getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        // Create a new Calendar instance and set it to the start of the month
        Calendar startOfMonth = Calendar.getInstance();
        startOfMonth.clear();
        startOfMonth.set(Calendar.YEAR, year);
        startOfMonth.set(Calendar.MONTH, month);
        startOfMonth.set(Calendar.DAY_OF_MONTH, 1);
        startOfMonth.set(Calendar.HOUR_OF_DAY, 0);
        startOfMonth.set(Calendar.MINUTE, 0);
        startOfMonth.set(Calendar.SECOND, 0);
        startOfMonth.set(Calendar.MILLISECOND, 0);

        return startOfMonth.getTime();
    }

    @GetMapping(path = "/monthlyincome", produces = "application/json")
    public List<Income> getMonthlyIncome() {
        List<Income> incomes = this.incomedao.income();
        List<Expense> expenses = this.expensedao.expense();

        Map<Date, BigDecimal> monthlyIncomeMap = new HashMap<>();

        // Calculate monthly income by summing up individual incomes
        for (Income income : incomes) {
            Date incomeDate = income.getDate();
            BigDecimal amount = income.getAmount();

            Date month = getMonth(incomeDate);
            BigDecimal currentAmount = monthlyIncomeMap.getOrDefault(month, BigDecimal.ZERO);
            BigDecimal updatedAmount = currentAmount.add(amount);
            monthlyIncomeMap.put(month, updatedAmount);
        }

        // Subtract monthly expenses from monthly income
        for (Expense expense : expenses) {
            Date expenseDate = expense.getDate();
            BigDecimal amount = expense.getAmount();

            Date month = getMonth(expenseDate);
            BigDecimal currentAmount = monthlyIncomeMap.getOrDefault(month, BigDecimal.ZERO);
            BigDecimal updatedAmount = currentAmount.subtract(amount);
            monthlyIncomeMap.put(month, updatedAmount);
        }

        // Convert the monthly income map into a list of Income objects
        List<Income> monthlyIncome = new ArrayList<>();
        for (Map.Entry<Date, BigDecimal> entry : monthlyIncomeMap.entrySet()) {
            Date month = entry.getKey();
            BigDecimal amount = entry.getValue();
            Income monthlyIncomeEntry = new Income(month, amount);
            monthlyIncome.add(monthlyIncomeEntry);
        }

        return monthlyIncome;
    }

    @GetMapping(path = "/yearlyexpenses", produces = "application/json")
    public List<Expense> getYearlyExpenses() {
        List<Expense> expenses = this.expensedao.expense();
        Map<Date, BigDecimal> yearlyExpensesMap = new HashMap<>();

        // Calculate yearly expenses by summing up individual expenses
        for (Expense expense : expenses) {
            Date expenseDate = expense.getDate();
            BigDecimal amount = expense.getAmount();

            Date year = getYear(expenseDate);
            BigDecimal currentAmount = yearlyExpensesMap.getOrDefault(year, BigDecimal.ZERO);
            BigDecimal updatedAmount = currentAmount.add(amount);
            yearlyExpensesMap.put(year, updatedAmount);
        }

        // Convert the yearly expenses map into a list of Expense objects
        List<Expense> yearlyExpenses = new ArrayList<>();
        for (Map.Entry<Date, BigDecimal> entry : yearlyExpensesMap.entrySet()) {
            Date year = entry.getKey();
            BigDecimal amount = entry.getValue();
            Expense yearlyExpense = new Expense(year, amount);
            yearlyExpenses.add(yearlyExpense);
        }

        return yearlyExpenses;
    }

    @GetMapping(path = "/yearlyincome", produces = "application/json")
    public List<Income> getYearlyIncome() {
        List<Income> incomes = this.incomedao.income();
        List<Expense> expenses = this.expensedao.expense();

        Map<Date, BigDecimal> yearlyIncomeMap = new HashMap<>();

        // Calculate yearly income by summing up individual incomes
        for (Income income : incomes) {
            Date incomeDate = income.getDate();
            BigDecimal amount = income.getAmount();

            Date year = getYear(incomeDate);
            BigDecimal currentAmount = yearlyIncomeMap.getOrDefault(year, BigDecimal.ZERO);
            BigDecimal updatedAmount = currentAmount.add(amount);
            yearlyIncomeMap.put(year, updatedAmount);
        }

        // Subtract yearly expenses from yearly income
        for (Expense expense : expenses) {
            Date expenseDate = expense.getDate();
            BigDecimal amount = expense.getAmount();

            Date year = getYear(expenseDate);
            BigDecimal currentAmount = yearlyIncomeMap.getOrDefault(year, BigDecimal.ZERO);
            BigDecimal updatedAmount = currentAmount.subtract(amount);
            yearlyIncomeMap.put(year, updatedAmount);
        }

        // Convert the yearly income map into a list of Income objects
        List<Income> yearlyIncome = new ArrayList<>();
        for (Map.Entry<Date, BigDecimal> entry : yearlyIncomeMap.entrySet()) {
            Date year = entry.getKey();
            BigDecimal amount = entry.getValue();
            Income yearlyIncomeEntry = new Income(year, amount);
            yearlyIncome.add(yearlyIncomeEntry);
        }

        return yearlyIncome;
    }

    private Date getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);

        // Create a new Calendar instance and set it to the start of the year
        Calendar endOfYear = Calendar.getInstance();
        endOfYear.clear();
        endOfYear.set(Calendar.YEAR, year);
        endOfYear.set(Calendar.MONTH, Calendar.DECEMBER);
        endOfYear.set(Calendar.DAY_OF_MONTH, 31);
        endOfYear.set(Calendar.HOUR_OF_DAY, 23);
        endOfYear.set(Calendar.MINUTE, 59);
        endOfYear.set(Calendar.SECOND, 59);
        endOfYear.set(Calendar.MILLISECOND, 999);

        return endOfYear.getTime();
    }

}










