package SUMedical.br.main.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class TokenService {

    public static String gerarToken(LoginService login) {
        return JWT.create()
                .withIssuer("login")
                .withSubject(login.getUsername())
                .withClaim("id", login.getId())
                .withExpiresAt(LocalDateTime.now()
                        .plusSeconds(60)
                        .toInstant(ZoneOffset.of("-03:00")))
                .sign(Algorithm.HMAC256("sumedical"));

    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("sumedical"))
            .withIssuer("login")
            .build().verify(token).getSubject();
    }

}
