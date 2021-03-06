package com.barbershop.cartel.generalConfig.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = GeneralConfigurationEntity.TABLE_NAME)
public class GeneralConfigurationEntity {

    public static final String TABLE_NAME = "general_configuration";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "front_page_message")
    private String frontPageMessage;

    @Column(name = "appointment_success_message")
    private String appointmentSuccessMessage;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "instagram")
    private String instagram;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;
}
