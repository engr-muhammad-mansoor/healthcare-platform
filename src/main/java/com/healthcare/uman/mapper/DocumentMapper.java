package com.healthcare.uman.mapper;

import java.util.List;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.healthcare.uman.dto.document.DocumentDTO;
import com.healthcare.uman.model.document.Document;
import com.healthcare.uman.model.user.User;

@Mapper
public interface DocumentMapper {

    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    @BeforeMapping
    default void beforeMapping(@MappingTarget Document document, DocumentDTO documentDTO) {
        User patient = new User();
        patient.setId(documentDTO.getIdPatient());
        document.setIdPatient(patient);

        User professional = new User();
        professional.setId(documentDTO.getIdPro());
        document.setIdPro(professional);
    }

    @Mapping(source = "idPatient", target = "idPatient.id")
    @Mapping(source = "idPro", target = "idPro.id")
    Document map(final DocumentDTO documentDTO);

    @Mapping(target = "idPatient", source = "idPatient.id")
    @Mapping(target = "idPro", source = "idPro.id")
    DocumentDTO map(final Document document);

    List<DocumentDTO> map(List<Document> documents);

}
