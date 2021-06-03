package bomtada;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name="Claim_table")
public class Claim {

    private static Logger log = LoggerFactory.getLogger(Claim.class);

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Integer customerId;
    private Integer price;
    private String status;
    private Date claimDt;

    @PostPersist
    public void onPostPersist(){
        ClaimReceived claimReceived = new ClaimReceived();
        BeanUtils.copyProperties(this, claimReceived);
        claimReceived.publishAfterCommit();


    }
    // private int count = 0;
    // @PreUpdate
    @PreUpdate
    public void onPreUpdate(){
        
        // count++;
        // //Following code causes dependency to external APIs
        // // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.
        // log.info("onPreUpdate - " + count);

        // bomtada.external.Review review = new bomtada.external.Review();
        // // mappings goes here
        // review.setClaimId(getId());
        // review.setStatus("Canceled Review");

        // boolean rslt = ClaimApplication.applicationContext.getBean(bomtada.external.ReviewService.class)
        //     .cancelReview(review);

        // if (rslt) {
        //     log.info("onPreUpdate - return true: " + count);
        //     ClaimCanceled claimCanceled = new ClaimCanceled();
        //     BeanUtils.copyProperties(this, claimCanceled);
        //     claimCanceled.publishAfterCommit();
        // }

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Date getClaimDt() {
        return claimDt;
    }

    public void setClaimDt(Date claimDt) {
        this.claimDt = claimDt;
    }




}
