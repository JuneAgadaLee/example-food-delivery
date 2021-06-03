
package bomtada;

import java.util.Date;

public class PaymentCompleted extends AbstractEvent {

    private Long id;
    private Integer customerId;
    private Integer contId;
    private Integer price;
    private String status;
    private Date paymentDt;
    private Long claimId;

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

