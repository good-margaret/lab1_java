package tech.reliab.course.tishchenko.bank.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import tech.reliab.course.tishchenko.bank.entity.*;

public interface CreditAccountService extends BaseOperationsService<CreditAccount> {
    void update(int id, Bank bank);
    CreditAccount create(User user, Bank bankName, LocalDate startDate,
                         int creditMonths, double creditAmount,
                         double interestRate, Employee employee,
                         PaymentAccount paymentAccount);
}