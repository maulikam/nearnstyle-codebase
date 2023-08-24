package com.nearnstyle.apis.notification.mapper;

import com.nearnstyle.apis.notification.dto.NotificationDTO;
import com.nearnstyle.apis.notification.model.Notification;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationDTO toDTO(Notification notification);

    Notification fromDTO(NotificationDTO notificationDTO);

    List<NotificationDTO> toDTOs(List<Notification> notifications);

    List<Notification> fromDTOs(List<NotificationDTO> notificationDTOs);
}

