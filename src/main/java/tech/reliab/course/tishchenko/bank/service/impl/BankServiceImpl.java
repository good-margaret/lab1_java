package tech.reliab.course.tishchenko.bank.service.impl;

import tech.reliab.course.tishchenko.bank.entity.Bank;
import tech.reliab.course.tishchenko.bank.service.BankService;
import tech.reliab.course.tishchenko.bank.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class BankServiceImpl {
    private static int banksCount = 0;

    private UserService userService;

    private List<Bank> banks = new ArrayList<>();

    public BankServiceImpl(UserService userService) {
        this.userService = userService;
    }

    /**
     * Создание нового банка.
     *
     * @param bankName Название банка.
     * @return Созданный банк.
     */
    public Bank create(String bankName) {
        Bank bank = new Bank(banksCount++, bankName);

        banks.add(bank);
        return bank;
    }

    /**
     * Чтение банка по его идентификатору.
     *
     * @param id Идентификатор банка.
     * @return Банк, если он найден, иначе - пустой Optional.
     */
    public Optional<Bank> getById(int id) {
        return banks.stream()
                .filter(bank -> bank.getId() == id)
                .findFirst();
    }

    /**
     * Чтение всех банков.
     *
     * @return Список всех банков.
     */
    public List<Bank> getAll() {
        return new ArrayList<>(banks);
    }

    /**
     * Обновление информации о банке по его идентификатору.
     *
     * @param id   Идентификатор банка.
     * @param name Новое название банка.
     */
    public void update(int id, String name) {
        Bank bank = getBankIfExists(id);
        bank.setName(name);
    }

    /**
     * Удаление банка по его идентификатору.
     *
     * @param id Идентификатор банка.
     */
    public void delete(int id) {
        Bank bank = getBankIfExists(id);
        banks.remove(bank);
        userService.deleteBank(bank);
    }

    /**
     * Получение банка по идентификатору, если он существует.
     *
     * @param id Идентификатор банка.
     * @return Банк, если он найден.
     * @throws NoSuchElementException Если банк не найден.
     */
    public Bank getBankIfExists(int id) {
        return getById(id).orElseThrow(() -> new NoSuchElementException("Bank was not found"));
    }

    /**
     * Увеличение количества офисов в банке.
     *
     * @param bank Банк, для которого нужно увеличить количество офисов.
     */
    public void addOffice(Bank bank) {
        bank.setNumberOfOffices(bank.getNumberOfOffices() + 1);
    }

    /**
     * Увеличение количества банкоматов в банке.
     *
     * @param bank Банк, для которого нужно увеличить количество банкоматов.
     */
    public void addAtm(Bank bank) {
        bank.setNumberOfATMs(bank.getNumberOfATMs() + 1);
    }

    /**
     * Увеличение количества сотрудников в банке.
     *
     * @param bank Банк, для которого нужно увеличить количество сотрудников.
     */
    public void addEmployee(Bank bank) {
        bank.setNumberOfEmployees(bank.getNumberOfEmployees() + 1);
    }

    /**
     * Увеличение количества клиентов в банке.
     *
     * @param bank Банк, для которого нужно увеличить количество клиентов.
     */
    public void addClient(Bank bank) {
        bank.setNumberOfClients(bank.getNumberOfClients() + 1);
    }

    /**
     * Уменьшение количества офисов в банке.
     *
     * @param bank Банк, для которого нужно уменьшить количество офисов.
     */
    public void removeOffice(Bank bank) {
        bank.setNumberOfOffices(bank.getNumberOfOffices() - 1);
    }

    /**
     * Уменьшение количества банкоматов в банке.
     *
     * @param bank Банк, для которого нужно уменьшить количество банкоматов.
     */
    public void removeAtm(Bank bank) {
        bank.setNumberOfATMs(bank.getNumberOfATMs() - 1);
    }

    /**
     * Уменьшение количества сотрудников в банке.
     *
     * @param bank Банк, для которого нужно уменьшить количество сотрудников.
     */
    public void removeEmployee(Bank bank) {
        bank.setNumberOfEmployees(bank.getNumberOfEmployees() - 1);
    }

    /**
     * Уменьшение количества клиентов в банке.
     *
     * @param bank Банк, для которого нужно уменьшить количество клиентов.
     */
    public void removeClient(Bank bank) {
        bank.setNumberOfClients(bank.getNumberOfClients() - 1);
    }

}
