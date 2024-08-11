package com.viesant.LabMedical.mappers;

import com.viesant.LabMedical.DTO.UsuarioResponse;
import com.viesant.LabMedical.entities.UsuarioEntity;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;

import static java.util.stream.Collectors.joining;

@NoArgsConstructor
public class UsuarioMapper {


public static UsuarioResponse map (UsuarioEntity source){
  String perfis = source.getPerfil().stream()
                          .map( perfil-> perfil.getNome() )
                                    .collect(Collectors.joining(" "));

  return new UsuarioResponse(
          source.getId(),
          source.getNome(),
          source.getEmail(),
          source.getPassword(),
          source.getDataNascimento(),
          source.getCpf(),
          perfis
  );
}



}
