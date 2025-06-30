package com.Javando.cadastro_usurio.business;

import com.Javando.cadastro_usurio.infrastructure.entitys.Usuario;
import com.Javando.cadastro_usurio.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void salvarUsuario(Usuario usuario){
        repository.saveAndFlush(usuario);
    }

    public Usuario buscarUsuarioPorEmail(String email){

        return repository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email não encontrado")
        );
    }

    public void deletarUsuarioPorEmail(String email){
        repository.deleteByEmail(email);
    }

    public void atualizarUsuarioPorId(Integer Id, Usuario usuario){
        Usuario usuarioEntity = (Usuario) repository.findById(id).orElseThrow(() ->
                new RuntimeException("Usuario não encontrado"));
        Usuario usuarioAtualizado = Usuario.builder()
                .email(usuario.getEmail() != null ? usuario.getEmail() :
                        usuarioEntity.getEmail())
                .nome(usuario.getNome() != null ? usuario.getNome() :
                        usuarioEntity.getNome())
                .telefone(usuario.getTelefone() != null ? usuario.getTelefone() :
                        usuarioEntity.getTelefone())
                .endereco(usuario.getEndereco() != null ? usuario.getEndereco() :
                        usuarioEntity.getEndereco())
                .id(usuarioEntity.getId())
                .build();

        repository.saveAndFlush(usuarioAtualizado);
    }
}

