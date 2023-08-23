package com.nearnstyle.apis.role.service;

import com.nearnstyle.apis.role.dto.FeatureDTO;

import java.util.List;

public interface FeatureService {

    List<FeatureDTO> getAllFeatures();

    FeatureDTO getFeatureById(Long id);

    FeatureDTO createFeature(FeatureDTO featureDto);

    FeatureDTO updateFeature(Long id, FeatureDTO featureDto);

    void deleteFeature(Long id);

}

