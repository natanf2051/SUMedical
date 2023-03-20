package SUMedical.br.main.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import SUMedical.br.main.Repositories.MagnetoRepository;
import SUMedical.br.main.Service.MagnetoService;
import SUMedical.br.main.Service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping
public class MagnetoController {

    @Autowired
    private MagnetoRepository clienteRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/buscar")
    public ResponseEntity<List<MagnetoService>> buscar() {
        var clientes = clienteRepository.findAll();

        return ResponseEntity.ok(clientes);

    }

    @GetMapping("/lista/magnetos")
    public List<MagnetoService> listarMagnetos() {
        return userService.listarMagnetos();
    }

    @PostMapping("/add/{loginID}/magneto")
    @Transactional
    public ResponseEntity<MagnetoService> criarMagneto(@PathVariable Long loginID,
            @RequestBody MagnetoService magneto) {
        MagnetoService magnetoCriado = userService.criarMagneto(loginID, magneto);
        return ResponseEntity.ok(magnetoCriado);
    }

}
