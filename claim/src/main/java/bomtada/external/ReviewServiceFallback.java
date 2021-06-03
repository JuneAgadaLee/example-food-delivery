package bomtada.external;

public class ReviewServiceFallback implements ReviewService{
    @Override
    public boolean cancelReview(Review review) {
        System.out.println("Circuit breaker has been opened. Fallback returned instead.");
        return false;
    }
}
