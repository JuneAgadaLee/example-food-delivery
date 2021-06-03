package bomtada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

@Transactional
@RestController
public class ClaimController {
private static Logger log = LoggerFactory.getLogger(ClaimController.class);

@Autowired
ClaimRepository claimRepository;

@RequestMapping(value = "/claims/{claimId}",
                method = RequestMethod.POST,
                produces = "application/json;charset=UTF-8")
public Claim cancelClaim(@PathVariable Long claimId, @RequestBody Claim claim) throws Exception {
    log.info("### cancelClaim called ###");

    claim.setId(claimId);

    bomtada.external.Review review = new bomtada.external.Review();
    // mappings goes here
    review.setClaimId(claimId);
    review.setStatus("Canceled Review");

    boolean rslt = ClaimApplication.applicationContext.getBean(bomtada.external.ReviewService.class)
        .cancelReview(review);

    if (rslt) {
        ClaimCanceled claimCanceled = new ClaimCanceled();
        BeanUtils.copyProperties(claim, claimCanceled);
        claimCanceled.publishAfterCommit();
    }
    return claim;
}

}
