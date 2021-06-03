package bomtada;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Review_table")
public class Review {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Integer examinerId;
    private Integer customerId;
    private Integer contId;
    private Integer price;
    private String status;
    private Date reviewDt;
    private Long claimId;

    @PostPersist
    public void onPostPersist(){
        ExaminerAssigned examinerAssigned = new ExaminerAssigned();
        BeanUtils.copyProperties(this, examinerAssigned);
        examinerAssigned.publishAfterCommit();


    }

    @PostUpdate
    public void onPostUpdate(){
        if (getStatus().equals("Approved Review")) {
            ReviewApproved reviewApproved = new ReviewApproved();
            BeanUtils.copyProperties(this, reviewApproved);
            reviewApproved.publishAfterCommit();
        } else if (getStatus().equals("Declined Review")) {
            ReviewDeclined reviewDeclined = new ReviewDeclined();
            BeanUtils.copyProperties(this, reviewDeclined);
            reviewDeclined.publishAfterCommit();
        } else if (getStatus().equals("Canceled Review")) {
            ReviewCanceled reviewCanceled = new ReviewCanceled();
            BeanUtils.copyProperties(this, reviewCanceled);
            reviewCanceled.publishAfterCommit();
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Integer getExaminerId() {
        return examinerId;
    }

    public void setExaminerId(Integer examinerId) {
        this.examinerId = examinerId;
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
    public Date getReviewDt() {
        return reviewDt;
    }

    public void setReviewDt(Date reviewDt) {
        this.reviewDt = reviewDt;
    }
    public Long getClaimId() {
        return claimId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }




}
