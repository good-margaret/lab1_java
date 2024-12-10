package tech.reliab.course.tishchenko.bank.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String fullName;
    private LocalDate dateOfBirth;
    private String workplace;
    private double monthlyIncome;
    private List<Bank> banks = new ArrayList<>();
    private List<CreditAccount> creditAccounts = new ArrayList<>();
    private List<PaymentAccount> paymentAccounts = new ArrayList<>();
    private int creditRating;

    public User(int id, String fullName, LocalDate dateOfBirth, String workplace) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.workplace = workplace;
        this.monthlyIncome = generateRandomIncome();
        this.banks = new ArrayList<>();
        this.creditAccounts = new ArrayList<>();
        this.paymentAccounts = new ArrayList<>();
        this.creditRating = calculateCreditRating();
    }

    private double generateRandomIncome() {
        Random random = new Random();
        return 1000 + random.nextInt(9001); // От 1000 до 10000
    }

    private int calculateCreditRating() {
        return (int) (monthlyIncome / 1000) * 100;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
        this.creditRating = calculateCreditRating();
    }

    @Override
    public String toString() {
        return "\nКлиент\n" +
                "ФИО: " + fullName + '\'' +
                "\nДата рождения: " + dateOfBirth +
                "\nМесто работы: " + workplace + '\'' +
                "\nДоход: " + String.format("%.2f", monthlyIncome) +
                "\nСписок банков: " + banks.stream().map(Bank::getName).toList() + '\'' +
                "\nКредитный счета: " + creditAccounts.size() +
                "\nПлатежные счета" + paymentAccounts.size() +
                "\nКредитный рейтинг" + creditRating;
    }
}
