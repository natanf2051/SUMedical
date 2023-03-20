package SUMedical.br.main.Service;

import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "magneto")
@Getter
@Setter
@Transactional
@NoArgsConstructor
@AllArgsConstructor
public class MagnetoService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private LoginService loginService;
    private double helio;
    private double pressao;
    private int temperatura;
    private double umidade;
    private int chiler;
    private int cht;

    public MagnetoService(MagnetoService magneto) {
        this.helio = magneto.getHelio();
        this.pressao = magneto.getPressao();
        this.temperatura = magneto.getTemperatura();
        this.umidade = magneto.getUmidade();
        this.chiler = magneto.getChiler();
        this.cht = magneto.getCht();

    }

}
