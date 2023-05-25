package mk.ukim.finki.emt.phonecatalog.service;

import mk.ukim.finki.emt.phonecatalog.domain.models.Phone;
import mk.ukim.finki.emt.phonecatalog.domain.models.PhoneId;
import mk.ukim.finki.emt.phonecatalog.service.form.PhoneForm;

import java.util.List;

public interface PhoneService {
    Phone findById(PhoneId phoneId);
    Phone createPhone(PhoneForm form);
    Phone orderPhoneCreated(PhoneId phoneId, int quantity);
    Phone orderPhoneRemoved(PhoneId phoneId, int quantity);
    List<Phone> getAll();

}
