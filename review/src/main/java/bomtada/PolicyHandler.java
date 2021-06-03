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
    @Autowired ReviewRepository reviewRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverClaimReceived_StartReview(@Payload ClaimReceived claimReceived){

        if(!claimReceived.validate()) return;

        System.out.println("\n\n##### listener StartReview : " + claimReceived.toJson() + "\n\n");

        // Sample Logic //
        Review review = new Review();
        review.setExaminerId(claimReceived.getCustomerId()+100);
        review.setCustomerId(claimReceived.getCustomerId());
        review.setPrice(claimReceived.getPrice());
        review.setStatus("Assigned Examiner");
        review.setReviewDt(new Date());
        review.setClaimId(claimReceived.getId());
        reviewRepository.save(review);
            
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
