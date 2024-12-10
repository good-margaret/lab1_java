package tech.reliab.course.tishchenko.bank.entity;

import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private int id;
    private String fullName;
    private LocalDate dateOfBirth;
    private String position;
    private Bank bank;
    private boolean worksRemotely;
    private BankOffice bankOffice;
    private boolean canIssueCredits;
    private double salary;

    public Employee(int id, String fullName, LocalDate dateOfBirth, String position,
                    Bank bank, BankOffice bankOffice, double salary) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.position = position;
        this.bank = bank;
        this.bankOffice = bankOffice;
        this.salary = salary;
        this.canIssueCredits = true;
        this.worksRemotely = false;
    }

    @Override
    public String toString() {
        return "\nСотрудник\n" +
                "ФИО: " + fullName + '\n' +
                "Дата раждения" + dateOfBirth + '\n' +
                "Должность" + position + '\n' +
                "Название банка: " + (bank != null ? bank.getName() : "Не указано") + '\n' +
                "Работает ли удаленно: " + worksRemotely + '\n' +
                "Банковский офис: " + (bankOffice != null ? bankOffice.getName() : "Не указано") + '\n' +
                "Дозволение выдавать кредиты: " + canIssueCredits +'\n' +
                "Зарплата: " + String.format("%.2f", salary);
    }
}
