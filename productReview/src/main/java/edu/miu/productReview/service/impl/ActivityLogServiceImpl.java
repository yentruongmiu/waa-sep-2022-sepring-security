package edu.miu.productReview.service.impl;

import edu.miu.productReview.domain.ActivityLog;
import edu.miu.productReview.repo.ActivityLogRepo;
import edu.miu.productReview.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityLogServiceImpl implements ActivityLogService {
    @Autowired
    private ActivityLogRepo activityLogRepo;
    @Override
    public ActivityLog save(ActivityLog activityLog) {
        return activityLogRepo.save(activityLog);
    }
}
