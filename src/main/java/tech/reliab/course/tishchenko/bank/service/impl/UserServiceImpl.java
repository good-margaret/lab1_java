package tech.reliab.course.tishchenko.bank.service.impl;

import tech.reliab.course.tishchenko.bank.entity.Bank;
import tech.reliab.course.tishchenko.bank.entity.CreditAccount;
import tech.reliab.course.tishchenko.bank.entity.PaymentAccount;
import tech.reliab.course.tishchenko.bank.entity.User;
import tech.reliab.course.tishchenko.bank.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private List<User> users = new ArrayList<>();
    int usersCount = 0;

    /**
     * Создание нового пользователя.
     *
     * @param fullName   Полное имя пользователя.
     * @param birthDate Дата рождения пользователя.
     * @param job        Профессия пользователя.
     * @return Созданный пользователь.
     */
    public User create(String fullName, LocalDate birthDate, String job) {
        User user = new User(usersCount++, fullName, birthDate, job);
        users.add(user);
        return user;
    }

    /**
     * Чтение пользователя по его идентификатору.
     *
     * @param id Идентификатор пользователя.
     * @return Пользователь, если он найден, иначе - пустой Optional.
     */
    public Optional<User> getById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    @Override
    public User getByIdIfPossible(int id) {
        return getById(id).orElseThrow(() -> new NoSuchElementException("User was not found"));
    }

    /**
     * Чтение всех пользователей.
     *
     * @return Список всех пользователей.
     */
    public List<User> getAll() {
        return new ArrayList<>(users);
    }

    /**
     * Обновление информации о пользователе по его идентификатору.
     *
     * @param id   Идентификатор пользователя.
     * @param name Новое имя пользователя.
     */
    public void update(int id, String name) {
        User user = getUserIfExists(id);
        user.setFullName(name);
    }

    /**
     * Удаление пользователя по его идентификатору.
     *
     * @param id Идентификатор пользователя.
     */
    public void delete(int id) {
        users.remove(getUserIfExists(id));
    }

    /**
     * Получение пользователя по его идентификатору, если он существует.
     *
     * @param id Идентификатор пользователя.
     * @return Пользователь, если он найден.
     * @throws NoSuchElementException Если пользователь не найден.
     */
    public User getUserIfExists(int id) {
        return getById(id).orElseThrow(() -> new NoSuchElementException("User was not found"));
    }

    /**
     * Добавление кредитного аккаунта пользователю.
     *
     * @param creditAccount Кредитный аккаунт.
     * @param user         Пользователь, которому принадлежит аккаунт.
     */
    public void addCreditAccount(CreditAccount creditAccount, User user) {
        List<CreditAccount> creditAccounts = user.getCreditAccounts();
        creditAccounts.add(creditAccount);
        user.setCreditAccounts(creditAccounts);
    }

    /**
     * Добавление платежного аккаунта пользователю.
     *
     * @param paymentAccount Платежный аккаунт.
     * @param user           Пользователь, которому принадлежит аккаунт.
     */
    public void addPaymentAccount(PaymentAccount paymentAccount, User user) {
        List<PaymentAccount> paymentAccounts = user.getPaymentAccounts();
        paymentAccounts.add(paymentAccount);
        user.setPaymentAccounts(paymentAccounts);
    }

    /**
     * Добавление банка к списку банков пользователя.
     *
     * @param bank Банк.
     * @param user Пользователь, которому добавляется банк.
     */
    public void addBank(Bank bank, User user) {
        List<Bank> banks = user.getBanks();
        banks.add(bank);
        user.setBanks(banks);
    }

    /**
     * Удаление кредитного аккаунта у пользователя.
     *
     * @param creditAccount Кредитный аккаунт.
     * @param user         Пользователь, у которого удаляется аккаунт.
     */
    public void deleteCreditAccount(CreditAccount creditAccount, User user) {
        List<CreditAccount> creditAccounts = user.getCreditAccounts();
        creditAccounts.remove(creditAccount);
        user.setCreditAccounts(creditAccounts);
    }

    /**
     * Удаление платежного аккаунта у пользователя.
     *
     * @param paymentAccount Платежный аккаунт.
     * @param user           Пользователь, у которого удаляется аккаунт.
     */
    public void deletePaymentAccount(PaymentAccount paymentAccount, User user) {
        List<PaymentAccount> paymentAccounts = user.getPaymentAccounts();
        paymentAccounts.remove(paymentAccount);
        user.setPaymentAccounts(paymentAccounts);
    }

    /**
     * Удаление банка из списка банков пользователей.
     *
     * @param bank Банк, который нужно удалить.
     */
    public void deleteBank(Bank bank) {
        for(User curUser: users) {
            List<Bank> banks = curUser.getBanks();
            banks.remove(bank);
            curUser.setBanks(banks);
        }
    }
}
