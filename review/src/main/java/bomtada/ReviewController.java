package bomtada;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

 @RestController
 public class ReviewController {
    private static Logger log = LoggerFactory.getLogger(ReviewController.class);

    @Autowired
    ReviewRepository reviewRepository;

    @RequestMapping(value = "/cancelReview",
                    method = RequestMethod.POST,
                    produces = "application/json;charset=UTF-8")
    public boolean cancelReview(@RequestBody Review review) throws Exception {
        log.info("### cancelReview called ###");

        boolean status = false;
        Optional<Review> reviewOptional = reviewRepository.findByClaimId(review.getClaimId());
        Review selectedReview = reviewOptional.get();

        if (!selectedReview.getStatus().equals("Approved Review") 
            && !selectedReview.getStatus().equals("Declined Review")) {
            selectedReview.setStatus(review.getStatus());
            reviewRepository.save(selectedReview);
            status = true;
        }

        // circuit break 테스트를 위한 임의 부하 처리
        try {
            Thread.currentThread().sleep((long) (400 + (Math.random() * 220)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return status;
    }


 }
