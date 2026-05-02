package io.allitov.buber.trip.api.mapper;

import io.allitov.buber.trip.api.dto.TripRequest;
import io.allitov.buber.trip.api.dto.TripResponse;
import io.allitov.buber.trip.core.model.Trip;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Маппер для сущности {@link Trip}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripMapper {

    /**
     * Создает dto для передачи информации о поездке.
     *
     * @param trip сущность, из которой взять данные для формирования dto.
     * @return dto с информацией о поездке.
     */
    TripResponse toResponse(Trip trip);

    /**
     * Создает сущность поездки на основании полученного dto.
     *
     * @param tripRequest dto, из которого взять данные для формирования сущности.
     * @return сущность поездки.
     */
    Trip toModel(TripRequest tripRequest);

    /**
     * Создает список dto для передачи информации о поездках.
     *
     * @param tripList список сущностей, из которых взять данные для формирования списка dto.
     * @return список dto с информацией о поездках.
     */
    List<TripResponse> toResponseList(List<Trip> tripList);
}
