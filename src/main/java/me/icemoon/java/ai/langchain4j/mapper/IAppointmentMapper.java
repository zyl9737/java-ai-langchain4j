package me.icemoon.java.ai.langchain4j.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.icemoon.java.ai.langchain4j.entity.Appointment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Yulong
 * @create 2025/4/24
 * @description 预约表 Mapper 接口
 */
@Mapper
public interface IAppointmentMapper extends BaseMapper<Appointment> {
}
