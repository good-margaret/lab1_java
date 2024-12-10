package tech.reliab.course.tishchenko.bank.service.impl;

import tech.reliab.course.tishchenko.bank.entity.Bank;
import tech.reliab.course.tishchenko.bank.entity.BankAtm;
import tech.reliab.course.tishchenko.bank.entity.BankOffice;
import tech.reliab.course.tishchenko.bank.entity.Employee;
import tech.reliab.course.tishchenko.bank.enums.BankAtmStatusEnum;
import tech.reliab.course.tishchenko.bank.service.BankAtmService;
import tech.reliab.course.tishchenko.bank.service.BankService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class BankAtmServiceImpl implements BankAtmService {
    private static int bankAtmsCount = 0;

    private List<BankAtm> bankAtms = new ArrayList<>();

    private final BankService bankService;

    public BankAtmServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }

    /**
     * Создание нового банкомата.
     *
     * @param name           Название банкомата.
     * @param address        Адрес банкомата.
     * @param bank           Банк, которому принадлежит банкомат.
     * @param employee       Сотрудник, ответственный за банкомат.
     * @return Созданный банкомат.
     */
    public BankAtm create(String name,
                                 String address,
                                 Bank bank,
                                 Employee employee) {
        BankAtm bankAtm = new BankAtm(bankAtmsCount++, name, address, bank, employee);

        bankService.addAtm(bank);
        bankAtms.add(bankAtm);
        return bankAtm;
    }

    /**
     * Чтение банкомата по его идентификатору.
     *
     * @param id Идентификатор банкомата.
     * @return Банкомат, если он найден, иначе - пустой Optional.
     */
    public Optional<BankAtm> getById(int id) {
        return bankAtms.stream()
                .filter(bankAtm -> bankAtm.getId() == id)
                .findFirst();
    }

    /**
     * Чтение всех банкоматов.
     *
     * @return Список всех банкоматов.
     */
    public List<BankAtm> getAll() {
        return new ArrayList<>(bankAtms);
    }

    /**
     * Чтение всех банкоматов определенного банка.
     *
     * @param bank Банк, для которого нужно получить банкоматы.
     * @return Список банкоматов, принадлежащих указанному банку.
     */
    public List<BankAtm> getAllBankAtmsByBank(Bank bank) {
        return bankAtms.stream()
                .filter(bankAtm -> bankAtm.getBank().getId() == bank.getId())
                .collect(Collectors.toList());
    }

    /**
     * Получение банкомата по идентификатору, если он существует.
     *
     * @param id Идентификатор банкомата.
     * @return Банкомат, если он найден.
     * @throws RuntimeException Если банкомат не найден.
     */
    public BankAtm getByIdIfPossible(int id) {
        return getById(id).orElseThrow(() -> new NoSuchElementException("BankAtm was not found"));
    }

    /**
     * Обновление информации о банкомате по его идентификатору.
     *
     * @param id   Идентификатор банкомата.
     * @param name Новое название банкомата.
     */
    public void update(int id, String name) {
        BankAtm bankAtm = getByIdIfPossible(id);
        bankAtm.setName(name);
    }

    /**
     * Удаление банкомата по его идентификатору.
     *
     * @param id Идентификатор банкомата.
     */
    public void delete(int id) {
        BankAtm bankAtm = getByIdIfPossible(id);
        bankAtms.remove(bankAtm);
        Bank bank = bankAtm.getBank();
        bankService.removeAtm(bank);
    }
}
