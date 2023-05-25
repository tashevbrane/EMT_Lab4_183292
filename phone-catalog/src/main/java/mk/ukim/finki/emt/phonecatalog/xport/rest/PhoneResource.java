package mk.ukim.finki.emt.phonecatalog.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.phonecatalog.domain.models.Phone;
import mk.ukim.finki.emt.phonecatalog.service.PhoneService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/phones")
@AllArgsConstructor
public class PhoneResource {

    private final PhoneService phoneService;

    @GetMapping
    public List<Phone> getAll() {
        return phoneService.getAll();
    }
}
