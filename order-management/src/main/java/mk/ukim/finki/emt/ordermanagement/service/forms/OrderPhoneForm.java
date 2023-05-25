package mk.ukim.finki.emt.ordermanagement.service.forms;

import lombok.Data;
import mk.ukim.finki.emt.ordermanagement.domain.valueObjects.Phone;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class OrderPhoneForm {

    @NotNull
    private Phone phone;

    @Min(1)
    private int quantity = 1;

}
