package br.com.cotiinformatica.api_usuarios.repositories;

import br.com.cotiinformatica.api_usuarios.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    /*
        Método para consultar os dados de 1 usuário
        attravés do email para verificarmos se o
        usuário por exemplo ja existe.
     */
    @Query("""
        SELECT u FROM Usuario u
        WHERE u.email = :email
    """)
    Usuario findByEmail(@Param("email") String email);

}
