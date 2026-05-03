package io.allitov.buber.user.api.mapper;

import io.allitov.buber.user.api.dto.passenger.PassengerRequest;
import io.allitov.buber.user.api.dto.passenger.PassengerResponse;
import io.allitov.buber.user.core.model.Passenger;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Маппер для сущности {@link Passenger}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PassengerMapper {

    /**
     * Создает dto для передачи информации о пассажире.
     * @param passenger сущность, из которой взять данные для формирования dto.
     * @return {@link PassengerResponse}.
     */
    PassengerResponse toResponse(Passenger passenger);

    /**
     * Создает сущность на основании полученного dto.
     * @param passengerRequest dto, из которого взять данные для формирования сущности.
     * @return {@link Passenger}.
     */
    Passenger toModel(PassengerRequest passengerRequest);
}
