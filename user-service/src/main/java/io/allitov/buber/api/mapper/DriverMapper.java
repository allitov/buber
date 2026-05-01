package io.allitov.buber.api.mapper;

import io.allitov.buber.api.dto.driver.DriverRequest;
import io.allitov.buber.api.dto.driver.DriverResponse;
import io.allitov.buber.core.model.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Маппер для сущности {@link Driver}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DriverMapper {

    /**
     * Создает dto для передачи информации о водителе.
     *
     * @param driver сущность, из которой взять данные для формирования dto.
     * @return {@link DriverResponse}.
     */
    DriverResponse toResponse(Driver driver);

    /**
     * Создает сущность водителя на основании полученного dto.
     *
     * @param driverRequest dto, из которого взять данные для формирования сущности.
     * @return {@link Driver}.
     */
    Driver toModel(DriverRequest driverRequest);
}
