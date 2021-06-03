package bomtada;

import bomtada.config.kafka.KafkaProcessor;

import java.util.Date;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired PaymentRepository paymentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReviewApproved_AssignPayment(@Payload ReviewApproved reviewApproved){

        if(!reviewApproved.validate()) return;

        System.out.println("\n\n##### listener AssignPayment : " + reviewApproved.toJson() + "\n\n");

        // Sample Logic //
        Payment payment = new Payment();
        payment.setCustomerId(reviewApproved.getCustomerId());
        payment.setContId(reviewApproved.getContId());
        payment.setPrice(reviewApproved.getPrice());
        payment.setStatus("Assigned Payment");
        payment.setPaymentDt(new Date());
        payment.setClaimId(reviewApproved.getClaimId());
        paymentRepository.save(payment);
        
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
