package mk.ukim.finki.emt.phonecatalog.config;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.phonecatalog.domain.models.Phone;
import mk.ukim.finki.emt.phonecatalog.domain.repository.PhoneRepository;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final PhoneRepository phoneRepository;

    @PostConstruct
    public void init() {
        Phone p1 = Phone.build("IPhone 12", Money.valueOf(Currency.MKD, 5000),10);
        Phone p2 = Phone.build("Samsung 12", Money.valueOf(Currency.USD, 120),8);
        Phone p3 = Phone.build("Huawei PRO12", Money.valueOf(Currency.EUR, 300),5);

//        if (phoneRepository.findAll().isEmpty()) {
//            phoneRepository.saveAll(Arrays.asList(p1,p2,p3));
//        }


    }
}
