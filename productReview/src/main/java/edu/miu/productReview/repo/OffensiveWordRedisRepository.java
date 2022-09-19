package edu.miu.productReview.repo;

import edu.miu.productReview.domain.OffensiveWordRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffensiveWordRedisRepository extends CrudRepository<OffensiveWordRecord, Integer> {
}
