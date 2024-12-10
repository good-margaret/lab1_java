package tech.reliab.course.tishchenko.bank.service.impl;

import tech.reliab.course.tishchenko.bank.entity.Bank;
import tech.reliab.course.tishchenko.bank.entity.BankOffice;
import tech.reliab.course.tishchenko.bank.service.BankOfficeService;
import tech.reliab.course.tishchenko.bank.service.BankService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class BankOfficeServiceImpl implements BankOfficeService {

    private static int bankOfficesCount = 0;

    private List<BankOffice> bankOffices = new ArrayList<>();

    private BankService bankService;

    public BankOfficeServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }

    /**
     * Создание нового офиса банка.
     *
     * @param name           Название офиса.
     * @param address        Адрес офиса.
     * @param bank           Банк, которому принадлежит офис.
     * @return Созданный офис банка.
     */
    public BankOffice create(String name, String address, Bank bank) {
        BankOffice bankOffice = new BankOffice(bankOfficesCount++, name, address);
        bankOffices.add(bankOffice);
        bankService.addOffice(bank);
        return bankOffice;
    }


    /**
     * Чтение всех офисов банка.
     *
     * @return Список всех офисов банка.
     */
    public List<BankOffice> getAll() {
        return new ArrayList<>(bankOffices);
    }

    /**
     * Обновление информации об офисе банка по его идентификатору.
     *
     * @param id   Идентификатор офиса банка.
     * @param name Новое название офиса банка.
     */
    public void update(int id, String name) {
        BankOffice bankOffice = getByIdIfPossible(id);
        bankOffice.setName(name);
    }

    /**
     * Удаление офиса банка по его идентификатору и идентификатору банка.
     *
     * @param officeId Идентификатор офиса банка.
     */
    public void delete(int officeId) {
        BankOffice bankOffice = getByIdIfPossible(officeId);
        bankOffices.remove(bankOffice);
    }

    /**
     * Получение офиса банка по его идентификатору, если он существует.
     *
     * @param id Идентификатор офиса банка.
     * @return Офис банка, если он найден.
     * @throws NoSuchElementException Если офис банка не найден.
     */
    public BankOffice getByIdIfPossible(int id) {
        return getById(id).orElseThrow(() -> new NoSuchElementException("BankOffice was not found"));
    }

    /**
     * Чтение банка по его идентификатору.
     *
     * @param id Идентификатор банка.
     * @return Банк, если он найден, иначе - пустой Optional.
     */
    public Optional<BankOffice> getById(int id) {
        return bankOffices.stream()
                .filter(bank -> bank.getId() == id)
                .findFirst();
    }
}
