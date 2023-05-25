package mk.ukim.finki.emt.ordermanagement.domain.valueObjects;

import jakarta.persistence.Embeddable;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

@Embeddable
public class PhoneId extends DomainObjectId {

    private PhoneId() {
        super(PhoneId.randomId(PhoneId.class).getId());
    }

    public PhoneId(@NonNull String uuid) {
        super(uuid);
    }


    public static PhoneId of(String uuid) {
        PhoneId  p = new PhoneId(uuid);
        return p;
    }
}
