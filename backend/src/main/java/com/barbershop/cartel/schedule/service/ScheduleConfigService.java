package com.barbershop.cartel.schedule.service;

import com.barbershop.cartel.schedule.entity.ScheduleConfigEntity;
import com.barbershop.cartel.schedule.interfaces.ScheduleConfigInterface;
import com.barbershop.cartel.schedule.models.ScheduleConfigModel;
import com.barbershop.cartel.schedule.repository.ScheduleConfigRepository;
import com.barbershop.cartel.barbers.entity.BarberEntity;
import com.barbershop.cartel.barbers.interfaces.BarberInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleConfigService implements ScheduleConfigInterface {

    @Autowired
    private BarberInterface barberInterface;

    @Autowired
    private ScheduleConfigRepository scheduleConfigRepository;

    private void saveConfiguration(ScheduleConfigModel configuration) {

        Optional<BarberEntity> barber = barberInterface.getBarberById(configuration.getBarberId());

        if (barber.isPresent()) {

            ScheduleConfigEntity scheduleConfig = new ScheduleConfigEntity();

            scheduleConfig.setBarber(barber.get());
            scheduleConfig.setFirstAppointment(configuration.getFirstAppointment());
            scheduleConfig.setLastAppointment(configuration.getLastAppointment());
            scheduleConfig.setDate(configuration.getDate());
            scheduleConfig.setWorkingDay(configuration.isHoliday());

            scheduleConfigRepository.save(scheduleConfig);
        }
    }

    @Override
    public void save(ScheduleConfigModel configuration) {
        saveConfiguration(configuration);
    }

    @Override
    public void save(List<ScheduleConfigModel> configurations) {

        for (ScheduleConfigModel configuration : configurations) {

            saveConfiguration(configuration);
        }
    }

    @Override
    public List<ScheduleConfigModel> getConfigurationsByBarberId(long barberId) {

        Optional<BarberEntity> barber = barberInterface.getBarberById(barberId);

        List<ScheduleConfigModel> configurations = new ArrayList<>();

        if (barber.isPresent()) {

            List<ScheduleConfigEntity> configsByBarber = scheduleConfigRepository.findAllByBarber(barber.get());

            for (ScheduleConfigEntity config : configsByBarber) {

                ScheduleConfigModel configuration = new ScheduleConfigModel();
                configuration.setBarberId(config.getId());
                configuration.setFirstAppointment(config.getFirstAppointment());
                configuration.setLastAppointment(config.getLastAppointment());
                configuration.setDate(config.getDate());
                configuration.setHoliday(config.isWorkingDay());

                configurations.add(configuration);
            }
        }

        return configurations;
    }

    @Override
    public ScheduleConfigModel getConfigurationByBarberIdAndDate(long barberId, LocalDate date) {

        Optional<BarberEntity> barber = barberInterface.getBarberById(barberId);

        ScheduleConfigModel configuration = new ScheduleConfigModel();

        if (barber.isPresent()) {

            ScheduleConfigEntity config = scheduleConfigRepository.findByBarberAndDate(barber.get(), date);

            if (config != null) {

                configuration = new ScheduleConfigModel();

                configuration.setBarberId(config.getId());
                configuration.setFirstAppointment(config.getFirstAppointment());
                configuration.setLastAppointment(config.getLastAppointment());
                configuration.setDate(config.getDate());
                configuration.setHoliday(config.isWorkingDay());
            }
        }

        return configuration;
    }
}
