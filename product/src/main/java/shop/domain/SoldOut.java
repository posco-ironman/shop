package shop.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import shop.domain.*;
import shop.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class SoldOut extends AbstractEvent {

    private Long id;

    public SoldOut(Inventory aggregate) {
        super(aggregate);
    }

    public SoldOut() {
        super();
    }
}
//>>> DDD / Domain Event
