package bomtada;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Payment_table")
public class Payment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Integer customerId;
    private Integer contId;
    private Integer price;
    private String status;
    private Date paymentDt;
    private Long claimId;

    @PostPersist
    public void onPostPersist(){
        PaymentAssigned paymentAssigned = new PaymentAssigned();
        BeanUtils.copyProperties(this, paymentAssigned);
        paymentAssigned.publishAfterCommit();


    }

    @PostUpdate
    public void onPostUpdate(){
        PaymentCompleted paymentCompleted = new PaymentCompleted();
        BeanUtils.copyProperties(this, paymentCompleted);
        paymentCompleted.publishAfterCommit();


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
    public Integer getContId() {
        return contId;
    }

    public void setContId(Integer contId) {
        this.contId = contId;
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
    public Date getPaymentDt() {
        return paymentDt;
    }

    public void setPaymentDt(Date paymentDt) {
        this.paymentDt = paymentDt;
    }
    public Long getClaimId() {
        return claimId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }




}
