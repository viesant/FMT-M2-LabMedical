package com.viesant.LabMedical.services;

import com.viesant.LabMedical.DTO.UsuarioRequest;
import com.viesant.LabMedical.DTO.UsuarioResponse;
import com.viesant.LabMedical.entities.PerfilEntity;
import com.viesant.LabMedical.entities.UsuarioEntity;
import com.viesant.LabMedical.repositories.PerfilRepository;
import com.viesant.LabMedical.repositories.UsuarioRepository;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.viesant.LabMedical.mappers.UsuarioMapper.map;

@Service
@AllArgsConstructor
public class UsuarioService {
  private final UsuarioRepository usuarioRepository;
  private final PerfilRepository perfilRepository;
  private final BCryptPasswordEncoder passwordEncoder;


  public UsuarioResponse novoUsuario(UsuarioRequest usuarioRequest) {

    if (usuarioRepository.findByEmail(usuarioRequest.email()).isPresent()) {
      throw new DuplicateKeyException("Já existe um usuário cadastrado com esse email");
    }
    if (usuarioRepository.findByCpf(usuarioRequest.cpf()).isPresent()) {
      throw new DuplicateKeyException("Já existe um usuário cadastrado com esse cpf");
    }

    PerfilEntity perfilTarget =
        perfilRepository
            .findByNome(usuarioRequest.perfil())
            .orElseThrow(() -> new BadCredentialsException("Perfil inválido"));

    UsuarioEntity target = new UsuarioEntity();

    target.setNome(usuarioRequest.nome());
    target.setEmail(usuarioRequest.email());
    target.setDataNascimento(usuarioRequest.dataNascimento());
    target.setCpf(usuarioRequest.cpf());
    target.setPassword(passwordEncoder.encode(usuarioRequest.password()));

    Set<PerfilEntity> perfis = Set.of(perfilTarget);
    target.setPerfil(perfis);



    return map(usuarioRepository.save(target));
  }
}
