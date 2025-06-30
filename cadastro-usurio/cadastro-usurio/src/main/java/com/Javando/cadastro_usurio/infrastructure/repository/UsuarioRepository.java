package com.Javando.cadastro_usurio.infrastructure.repository;

import com.Javando.cadastro_usurio.infrastructure.entitys.Usuario;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

    Optional<Object> findById(SingularAttribute<AbstractPersistable, Serializable> id);
}
