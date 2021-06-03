
package bomtada;

import java.util.Date;

public class ClaimReceived extends AbstractEvent {

    private Long id;
    private Integer customerId;
    private Integer price;
    private String status;
    private Date claimDt;

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

