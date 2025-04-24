package me.icemoon.java.ai.langchain4j.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.icemoon.java.ai.langchain4j.entity.Appointment;

/**
 * @author Yulong
 * @create 2025/4/24
 * @description
 */
public interface IAppointmentService extends IService<Appointment> {
    Appointment getOne(Appointment appointment);
}
