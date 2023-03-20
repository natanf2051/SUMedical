package SUMedical.br.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SUMedical.br.main.Repositories.LoginRepository;
import SUMedical.br.main.Repositories.MagnetoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private MagnetoRepository magnetoRepository;

    public LoginService criarLogin(LoginService login) {
        return loginRepository.save(login);
    }

    public MagnetoService criarMagneto(Long id, MagnetoService magneto) {
        LoginService logue = loginRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Login não encontrado com o ID " + id));
        magneto.setLoginService(logue);

        return magnetoRepository.save(magneto);
    }

    public List<LoginService> listarClientes() {
        return loginRepository.findAll();
    }

    public LoginService buscarCliente(Long id) {
        return loginRepository.findById(id).orElse(null);
    }

    public List<MagnetoService> listarMagnetos() {
        return magnetoRepository.findAll();
    }

    public MagnetoService buscarMagneto(Long id) {
        return magnetoRepository.findById(id).orElse(null);
    }

}
