
package bomtada.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

// @FeignClient(name="review", url="http://review:8080")
// @FeignClient(name="review", url="http://localhost:8082"//, fallback = ReviewServiceFallback.class)

@FeignClient(name="review", url="http://localhost:8082")
public interface ReviewService {

    @RequestMapping(method= RequestMethod.POST, path="/cancelReview")
    public boolean cancelReview(@RequestBody Review review);

}
