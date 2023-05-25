package mk.ukim.finki.emt.phonecatalog.domain.repository;

import mk.ukim.finki.emt.phonecatalog.domain.models.Phone;
import mk.ukim.finki.emt.phonecatalog.domain.models.PhoneId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, PhoneId> {
}
