package tech.reliab.course.tishchenko.bank.service;

import tech.reliab.course.tishchenko.bank.entity.Bank;
import tech.reliab.course.tishchenko.bank.entity.CreditAccount;
import tech.reliab.course.tishchenko.bank.entity.PaymentAccount;
import tech.reliab.course.tishchenko.bank.entity.User;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService extends BaseOperationsService<User>{
    User create(String fullName, LocalDate birthDate, String job);

    void update(int id, String name);

    void addCreditAccount(CreditAccount creditAccount, User user);

    void addPaymentAccount(PaymentAccount paymentAccount, User user);

    void addBank(Bank bank, User user);

    void deleteCreditAccount(CreditAccount creditAccount, User user);

    void deletePaymentAccount(PaymentAccount paymentAccount, User user);

    void deleteBank(Bank bank);
}
