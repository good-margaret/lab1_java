package tech.reliab.course.tishchenko.bank.entity;

import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditAccount {
    private int id;
    private User user;
    private Bank bank;
    private LocalDate startDate;
    private LocalDate endDate;
    private int creditMonths;
    private double creditAmount;
    private double monthlyPayment;
    private double interestRate;
    private Employee employee;
    private PaymentAccount paymentAccount;

    public CreditAccount(int id, User user, Bank bankName, LocalDate startDate, int creditMonths,
                         double creditAmount, double interestRate, Employee employee, PaymentAccount paymentAccount) {
        this.id = id;
        this.user = user;
        this.bank = bankName;
        this.startDate = startDate;
        this.creditMonths = creditMonths;
        this.creditAmount = creditAmount;
        this.interestRate = interestRate;
        this.employee = employee;
        this.paymentAccount = paymentAccount;

        this.endDate = calculateEndDate(startDate, creditMonths);
        this.monthlyPayment = calculateMonthlyPayment(creditAmount, interestRate, creditMonths);
    }

    private LocalDate calculateEndDate(LocalDate startDate, int months) {
        return startDate.plusMonths(months);
    }

    private double calculateMonthlyPayment(double creditAmount, double interestRate, int months) {
        double monthlyRate = interestRate / 100 / 12;
        return (creditAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        this.endDate = calculateEndDate(startDate, creditMonths);
    }

    public void setCreditMonths(int creditMonths) {
        this.creditMonths = creditMonths;
        this.endDate = calculateEndDate(startDate, creditMonths);
        this.monthlyPayment = calculateMonthlyPayment(creditAmount, interestRate, creditMonths);
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
        this.monthlyPayment = calculateMonthlyPayment(creditAmount, interestRate, creditMonths);
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
        this.monthlyPayment = calculateMonthlyPayment(creditAmount, interestRate, creditMonths);
    }

    @Override
    public String toString() {
        return "\n Кредитный счет\n" +
                "Пользователь: " + (user != null ? user.getFullName() : "Не указано") + '\n' +
                "Банк: " + (bank != null ? bank.getName() : "Не указано") + '\n' +
                "Дата начала кредита: " + startDate + '\n' +
                "Дата окончания кредита" + endDate +'\n' +
                "Длительность кредита (месяца): " + creditMonths +'\n' +
                "Сумма кредита: " + String.format("%.2f", creditAmount) +'\n' +
                "Ежемесячная выплата: " + String.format("%.2f", monthlyPayment) + '\n' +
                "Процентная ставка: " + String.format("%.2f", interestRate) + '\n' +
                "Сотрудник: " + (employee != null ? employee.getFullName() : "Не указано") + '\n' +
                "Платежный счет" + (paymentAccount != null ? paymentAccount.getId() : "Не указано");
    }
}
