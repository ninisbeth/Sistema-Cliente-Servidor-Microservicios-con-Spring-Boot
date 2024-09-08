package dao;

import org.springframework.data.jpa.repository.*;

import jakarta.transaction.*;
import model.Contacto;

public interface AgendaJpaSpring extends JpaRepository<Contacto, Integer>{
	
	Contacto findByEmail(String email);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Contacto c WHERE c.email = ?1") //Instrucción JPQL
	void eliminarPorEmail(String email);

}
