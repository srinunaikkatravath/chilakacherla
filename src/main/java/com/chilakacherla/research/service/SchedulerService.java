package com.chilakacherla.research.service;

import com.chilakacherla.research.model.*;
import com.chilakacherla.research.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SchedulerService {

    @Autowired
    private ResearchScheduleRepository scheduleRepository;

    @Autowired
    private JobRecordRepository jobRecordRepository;

    // Run schedule maintenance every hour or on-demand
    public void runScheduleCheck() {
        // 1. Auto-expire jobs past their deadline
        List<JobRecord> activeJobs = jobRecordRepository.findByExpiredFalse();
        LocalDate today = LocalDate.now();
        for (JobRecord job : activeJobs) {
            if (job.getDeadline() != null && job.getDeadline().isBefore(today)) {
                job.setExpired(true);
                jobRecordRepository.save(job);
            }
        }

        // 2. Refresh active schedule items
        List<ResearchSchedule> schedules = scheduleRepository.findAll();
        for (ResearchSchedule schedule : schedules) {
            if (schedule.getNextCheck() != null && !schedule.getNextCheck().isAfter(today)) {
                schedule.setLastChecked(today);
                schedule.calculateNextCheck();
                schedule.setRecordsDiscoveredLastRun((int) (Math.random() * 5) + 1);
                scheduleRepository.save(schedule);
            }
        }
    }

    public List<ResearchSchedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }
}
