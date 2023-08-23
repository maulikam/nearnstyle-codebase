package com.nearnstyle.apis.role.mapper;

import com.nearnstyle.apis.role.dto.FeatureDTO;
import com.nearnstyle.apis.role.model.Feature;

import java.util.List;
import java.util.stream.Collectors;

public class FeatureMapper {

    public static FeatureDTO convertFeatureToDto(Feature feature) {
        if (feature == null) {
            return null;
        }

        FeatureDTO featureDTO = new FeatureDTO();
        featureDTO.setId(feature.getId());
        featureDTO.setName(feature.getName());
        featureDTO.setDescription(feature.getDescription());

        return featureDTO;
    }

    public static Feature convertDtoToFeature(FeatureDTO featureDTO) {
        if (featureDTO == null) {
            return null;
        }

        Feature feature = new Feature();
        feature.setId(featureDTO.getId());
        feature.setName(featureDTO.getName());
        feature.setDescription(featureDTO.getDescription());

        return feature;
    }

    public static List<FeatureDTO> convertFeatureListToDtoList(List<Feature> features) {
        return features.stream()
                .map(FeatureMapper::convertFeatureToDto)
                .collect(Collectors.toList());
    }

    public static List<Feature> convertFeatureDtoListToList(List<FeatureDTO> featureDTOs) {
        return featureDTOs.stream()
                .map(FeatureMapper::convertDtoToFeature)
                .collect(Collectors.toList());
    }
}

