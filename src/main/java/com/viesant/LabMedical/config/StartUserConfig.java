package com.viesant.LabMedical.config;

import com.viesant.LabMedical.entities.PerfilEntity;
import com.viesant.LabMedical.entities.UsuarioEntity;
import com.viesant.LabMedical.repositories.PerfilRepository;
import com.viesant.LabMedical.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Configuration
@AllArgsConstructor
public class StartUserConfig implements CommandLineRunner {

  private final UsuarioRepository usuarioRepository;
  private final PerfilRepository perfilRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public void run(String... args) throws Exception {

    salvaPerfil("ADMIN");
    salvaPerfil("MEDICO");
    salvaPerfil("PACIENTE");

    if( usuarioRepository.findByNome("admin").isEmpty()){

      UsuarioEntity admin = new UsuarioEntity();
      admin.setNome("admin");
      admin.setPassword(passwordEncoder.encode("admin"));

      admin.setDataNascimento(LocalDate.now());
      admin.setCpf("123.456.789-99");
      admin.setEmail("admin@viesant.com");

      PerfilEntity perfilAdmin = perfilRepository.findByNome("ADMIN").orElseThrow(
              () -> new RuntimeException("Perfil ADMIN não existe!")
      );

      admin.setPerfil(Set.of(perfilAdmin));
      usuarioRepository.save(admin);


    }

  }

  private void salvaPerfil(String perfil) {
    if (perfilRepository.findByNome(perfil).isEmpty()) {
      PerfilEntity novoPerfil = new PerfilEntity();
      novoPerfil.setNome(perfil);
      perfilRepository.save(novoPerfil);
    }
  }
}
