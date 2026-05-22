package com.example.semesterproject.service;

import com.example.semesterproject.entity.AuditLog;
import com.example.semesterproject.repository.AuditLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AuditService {
    private static final Logger log = LoggerFactory.getLogger(AuditService.class);
    private final AuditLogRepository auditLogRepository;

    public AuditService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Async("taskExecutor")
    public void logAction(String action, String actor, String details) {
        AuditLog auditLog = new AuditLog();
        auditLog.setAction(action);
        auditLog.setActor(actor);
        auditLog.setDetails(details);
        auditLogRepository.save(auditLog);
        log.info("Audit saved: action={}, actor={}", action, actor);
    }
}
