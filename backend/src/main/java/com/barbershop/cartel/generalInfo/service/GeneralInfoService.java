package com.barbershop.cartel.generalInfo.service;

import com.barbershop.cartel.generalInfo.entity.GeneralInfoEntity;
import com.barbershop.cartel.generalInfo.interfaces.GeneralInfoInterface;
import com.barbershop.cartel.generalInfo.models.GeneralInfoModel;
import com.barbershop.cartel.generalInfo.repository.GeneralInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeneralInfoService implements GeneralInfoInterface {

    @Autowired
    private GeneralInfoRepository generalInfoRepository;

    @Override
    public void save(GeneralInfoModel generalInfo) {

        GeneralInfoEntity generalInformation = new GeneralInfoEntity();

        generalInformation.setMessage(generalInfo.getMessage());
        generalInformation.setInstagramProfile(generalInfo.getInstagramProfile());
        generalInformation.setFacebookProfile(generalInfo.getFacebookProfile());

        generalInfoRepository.save(generalInformation);
    }

    @Override
    public List<GeneralInfoModel> getGeneralInfo() {

        List<GeneralInfoModel> generalInformation = new ArrayList<>();

        Iterable<GeneralInfoEntity> findAll = generalInfoRepository.findAll();

        for (GeneralInfoEntity entry : findAll) {

            GeneralInfoModel generalInfo = new GeneralInfoModel();

            generalInfo.setMessage(entry.getMessage());
            generalInfo.setInstagramProfile(entry.getInstagramProfile());
            generalInfo.setFacebookProfile(entry.getFacebookProfile());

            generalInformation.add(generalInfo);
        }

        return generalInformation;
    }
}
