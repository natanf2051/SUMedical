package SUMedical.br.main.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import SUMedical.br.main.Service.MagnetoService;

public interface MagnetoRepository extends JpaRepository<MagnetoService, Long> {
}
