package tr11.theater.service.mapper;

public interface RequestResponseMapper<M, RQ, RS> {
    M toModel(RQ dto);

    RS toDto(M model);
}
