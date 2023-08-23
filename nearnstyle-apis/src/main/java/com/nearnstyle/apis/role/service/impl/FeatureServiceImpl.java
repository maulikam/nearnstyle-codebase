package com.nearnstyle.apis.role.service.impl;

import com.nearnstyle.apis.role.dto.FeatureDTO;
import com.nearnstyle.apis.role.mapper.FeatureMapper;
import com.nearnstyle.apis.role.model.Feature;
import com.nearnstyle.apis.role.repository.FeatureRepository;
import com.nearnstyle.apis.role.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    public List<FeatureDTO> getAllFeatures() {
        List<Feature> features = featureRepository.findAll();
        return features.stream()
                .map(FeatureMapper::convertFeatureToDto)
                .collect(Collectors.toList());
    }

    public FeatureDTO getFeatureById(Long id) {
        Feature feature = featureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid feature Id:" + id));
        return FeatureMapper.convertFeatureToDto(feature);
    }

    public FeatureDTO createFeature(FeatureDTO featureDto) {
        Feature feature = FeatureMapper.convertDtoToFeature(featureDto);
        feature = featureRepository.save(feature);
        return FeatureMapper.convertFeatureToDto(feature);
    }

    public FeatureDTO updateFeature(Long id, FeatureDTO featureDto) {
        Feature existingFeature = featureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid feature Id:" + id));

        existingFeature.setName(featureDto.getName());
        existingFeature.setDescription(featureDto.getDescription());

        existingFeature = featureRepository.save(existingFeature);

        return FeatureMapper.convertFeatureToDto(existingFeature);
    }

    public void deleteFeature(Long id) {
        if (!featureRepository.existsById(id)) {
            throw new IllegalArgumentException("Invalid feature Id:" + id);
        }
        featureRepository.deleteById(id);
    }
}

