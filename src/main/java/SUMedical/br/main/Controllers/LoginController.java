package SUMedical.br.main.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SUMedical.br.main.Repositories.LoginRepository;
import SUMedical.br.main.Service.LoginService;
import SUMedical.br.main.Service.TokenService;
import SUMedical.br.main.Service.UserService;
import jakarta.validation.Valid;

@RequestMapping
@RestController
public class LoginController {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/cadastrar")
    public ResponseEntity postMethodName(@RequestBody @Valid LoginService login) {
        var loginSalva = new LoginService(login);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaEncriptada = encoder.encode(loginSalva.getSenha());
        loginSalva.setSenha(senhaEncriptada);
        loginRepository.save(loginSalva);

        return ResponseEntity.ok(loginSalva);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginService login) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                login.getLogin(), login.getSenha());

        Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        var usuario = (LoginService) authenticate.getPrincipal();

        return tokenService.gerarToken(usuario);
    }

    @GetMapping("/lista/clientes")
    public List<LoginService> listarClientes() {
        return userService.listarClientes();
    }

    @GetMapping("/buscar/{id}")
    public LoginService buscarCliente(@PathVariable Long id) {
        return userService.buscarCliente(id);
    }
}
