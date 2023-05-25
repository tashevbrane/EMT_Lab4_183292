package mk.ukim.finki.emt.phonecatalog.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.phonecatalog.domain.exceptions.PhoneNotFoundException;
import mk.ukim.finki.emt.phonecatalog.domain.models.Phone;
import mk.ukim.finki.emt.phonecatalog.domain.models.PhoneId;
import mk.ukim.finki.emt.phonecatalog.domain.repository.PhoneRepository;
import mk.ukim.finki.emt.phonecatalog.service.PhoneService;
import mk.ukim.finki.emt.phonecatalog.service.form.PhoneForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;

    @Override
    public Phone findById(PhoneId phoneId) {
        return phoneRepository.findById(phoneId).orElseThrow(PhoneNotFoundException::new);
    }

    @Override
    public Phone createPhone(PhoneForm form) {
        Phone p = Phone.build(form.getPhoneName(), form.getPrice(), form.getSales());
        phoneRepository.save(p);
        return p;
    }

    @Override
    public Phone orderPhoneCreated(PhoneId phoneId, int quantity) {
        Phone p = phoneRepository.findById(phoneId).orElseThrow(PhoneNotFoundException::new);
        p.addSales(quantity);
        phoneRepository.saveAndFlush(p);
        return p;
    }

    @Override
    public Phone orderPhoneRemoved(PhoneId phoneId, int quantity) {
        Phone p = phoneRepository.findById(phoneId).orElseThrow(PhoneNotFoundException::new);
        p.removeSales(quantity);
        phoneRepository.saveAndFlush(p);
        return p;
    }

    @Override
    public List<Phone> getAll() {
        return phoneRepository.findAll();
    }
}
