package SUMedical.br.main.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import SUMedical.br.main.Service.LoginService;

public interface LoginRepository extends JpaRepository<LoginService, Long> {

    LoginService findByLogin(String login);

}
