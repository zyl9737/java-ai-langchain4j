package me.icemoon.java.ai.langchain4j.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.icemoon.java.ai.langchain4j.entity.Appointment;
import me.icemoon.java.ai.langchain4j.mapper.IAppointmentMapper;
import me.icemoon.java.ai.langchain4j.service.IAppointmentService;
import org.springframework.stereotype.Service;

/**
 * @author Yulong
 * @create 2025/4/24
 * @description
 */
@Service
public class AppointServiceImpl extends ServiceImpl<IAppointmentMapper, Appointment> implements IAppointmentService {

    /**
     * 查询预约订单是否存在
     * @param appointment
     * @return appintmentDB
     */
    @Override
    public Appointment getOne(Appointment appointment) {
        LambdaQueryWrapper<Appointment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Appointment::getUsername, appointment.getUsername());
        queryWrapper.eq(Appointment::getIdCard, appointment.getIdCard());
        queryWrapper.eq(Appointment::getDepartment, appointment.getDepartment());
        queryWrapper.eq(Appointment::getDate, appointment.getDate());
        queryWrapper.eq(Appointment::getTime, appointment.getTime());

        return baseMapper.selectOne(queryWrapper);
    }
}
