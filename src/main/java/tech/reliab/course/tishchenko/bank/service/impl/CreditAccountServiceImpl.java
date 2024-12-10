package tech.reliab.course.tishchenko.bank.service.impl;

import tech.reliab.course.tishchenko.bank.entity.*;
import tech.reliab.course.tishchenko.bank.service.BankService;
import tech.reliab.course.tishchenko.bank.service.CreditAccountService;
import tech.reliab.course.tishchenko.bank.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class CreditAccountServiceImpl implements CreditAccountService {
    private static int creditAccountsCount = 0;

    private final UserService userService;
    private final BankService bankService;

    private List<CreditAccount> creditAccounts = new ArrayList<>();

    public CreditAccountServiceImpl(UserService userService, BankService bankService) {
        this.userService = userService;
        this.bankService = bankService;
    }

    /**
     * Создание нового кредитного аккаунта.
     *
     * @param user              Пользователь, которому принадлежит аккаунт.
     * @param bankName              Банк, который предоставляет кредит.
     * @param startDate         Дата начала кредита.
     * @param creditMonths    Срок кредита в месяцах.
     * @param creditAmount       Сумма кредита.
     * @param interestRate      Процентная ставка по кредиту.
     * @param employee          Сотрудник, который выдал кредит.
     * @param paymentAccount    Платежный аккаунт пользователя.
     * @return Созданный кредитный аккаунт.
     */
    public CreditAccount create(User user, Bank bankName, LocalDate startDate,
                                             int creditMonths, double creditAmount,
                                             double interestRate, Employee employee,
                                             PaymentAccount paymentAccount) {

        CreditAccount creditAccount = new CreditAccount(creditAccountsCount++, user, bankName, startDate, creditMonths,
                creditAmount, interestRate, employee, paymentAccount);
        creditAccounts.add(creditAccount);
        userService.addCreditAccount(creditAccount, user);
        return creditAccount;
    }

    /**
     * Чтение кредитного аккаунта по его идентификатору.
     *
     * @param id Идентификатор кредитного аккаунта.
     * @return Кредитный аккаунт, если он найден, иначе - пустой Optional.
     */
    public Optional<CreditAccount> getById(int id) {
        return creditAccounts.stream()
                .filter(creditAccount -> creditAccount.getId() == id)
                .findFirst();
    }

    /**
     * Чтение всех кредитных аккаунтов.
     *
     * @return Список всех кредитных аккаунтов.
     */
    public List<CreditAccount> getAll() {
        return new ArrayList<>(creditAccounts);
    }

    /**
     * Обновление информации о кредитном аккаунте по его идентификатору.
     *
     * @param id   Идентификатор кредитного аккаунта.
     * @param bank Банк, который предоставляет кредит.
     */
    public void update(int id, Bank bank) {
        CreditAccount creditAccount = getByIdIfPossible(id);
        creditAccount.setBank(bank);
    }

    /**
     * Удаление кредитного аккаунта по его идентификатору и идентификатору пользователя.
     *
     * @param accountId Идентификатор кредитного аккаунта.
     */
    public void delete(int accountId) {
        CreditAccount creditAccount = getByIdIfPossible(accountId);
        creditAccounts.remove(creditAccount);
    }

    /**
     * Получение кредитного аккаунта по его идентификатору, если он существует.
     *
     * @param id Идентификатор кредитного аккаунта.
     * @return Кредитный аккаунт, если он найден.
     * @throws NoSuchElementException Если кредитный аккаунт не найден.
     */
    public CreditAccount getByIdIfPossible(int id) {
        return getById(id).orElseThrow(() -> new NoSuchElementException("CreditAccount was not found"));
    }
}
