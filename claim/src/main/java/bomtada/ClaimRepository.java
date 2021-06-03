package bomtada;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="claims", path="claims")
public interface ClaimRepository extends PagingAndSortingRepository<Claim, Long>{


}
