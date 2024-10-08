package shop.domain;

import shop.domain.DeliveryStarted;
import shop.domain.DeliveryCompleted;
import shop.DeliveryApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.time.LocalDate;


@Entity
@Table(name="Delivery_table")
@Data

//<<< DDD / Aggregate Root
public class Delivery  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    private Long id;
    
    
    
    
    private Long orderId;
    
    
    
    
    private String userId;
    
    
    
    
    private Long productId;
    
    
    
    
    private Integer qty;
    
    
    
    
    private String status;

    @PostPersist
    public void onPostPersist(){


        DeliveryStarted deliveryStarted = new DeliveryStarted(this);
        deliveryStarted.publishAfterCommit();



        DeliveryCompleted deliveryCompleted = new DeliveryCompleted(this);
        deliveryCompleted.publishAfterCommit();

    
    }

    public static DeliveryRepository repository(){
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(DeliveryRepository.class);
        return deliveryRepository;
    }



    public void delivery(){
        shop.external.DeliveryQuery deliveryQuery = new shop.external.DeliveryQuery();
        DeliveryApplication.applicationContext
            .getBean(shop.external.Service.class)
            .( deliveryQuery);
    }
    

//<<< Clean Arch / Port Method
    public static void startDelivery(OrderPlaced orderPlaced){
        
        //implement business logic here:

        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);


         });
        */

        
    }
//>>> Clean Arch / Port Method


}
//>>> DDD / Aggregate Root
