package edu.miu.productReview.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;

@RedisHash(value = "offensiveWord")
@Setter
@Getter
@NoArgsConstructor
public class OffensiveWordRecord implements Serializable {
    private int id;
    private int numOfViolation;
    @TimeToLive
    private Long ttl;

    private boolean isBlocked = false;

    public OffensiveWordRecord(int userId, int numOfViolation, Long ttl) {
        this.id = userId;
        this.numOfViolation = numOfViolation;
        this.ttl = ttl;
    }
}
