package com.viesant.LabMedical.services;

import com.viesant.LabMedical.DTO.LoginRequest;
import com.viesant.LabMedical.DTO.LoginResponse;
import com.viesant.LabMedical.entities.PerfilEntity;
import com.viesant.LabMedical.entities.UsuarioEntity;
import com.viesant.LabMedical.repositories.UsuarioRepository;
import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
  private final UsuarioRepository usuarioRepository;
  private final JwtEncoder jwtEncoder;
  private final BCryptPasswordEncoder passwordEncoder;


  public LoginResponse login(LoginRequest login) {
    Optional<UsuarioEntity> usuario = usuarioRepository.findByNome(login.usuario());

    if (usuario.isEmpty() || !usuario.get().isLoginCorrect(login.senha(), passwordEncoder)) {
      throw new BadCredentialsException("Invalid usuario or senha");
    }

    Instant now = Instant.now();
    Long expiration = 300L;

    String perfis =
            usuario.get().getPerfil().stream()
                    .map(PerfilEntity::getNome)
                    .collect(Collectors.joining(" "));

    JwtClaimsSet claims =
            JwtClaimsSet.builder()
                    .issuer("viesant.LabMedical")
                    .subject(login.usuario())
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(expiration))
                    .claim("scope", perfis)
                    .build();
    String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

    return new LoginResponse(token, expiration);
  }
}
