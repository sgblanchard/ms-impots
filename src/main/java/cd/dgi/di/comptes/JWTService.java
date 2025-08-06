package cd.dgi.di.comptes;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class JWTService {
    private final JwtEncoder jwtEncoder;
    public Map<String, String> generateToken(Authentication authentication) {
        Set<String> scopes = authentication
                .getAuthorities()
                .stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toSet());
        Instant maintenant = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet
                .builder()
                .issuedAt(maintenant)
                .expiresAt(maintenant.plus(30, ChronoUnit.MINUTES))
                .subject(authentication.getName())
                .claim("username", authentication.getName())
                .claim("scp", scopes)
                .issuer("self")
                .build();

        String token = this.jwtEncoder.encode(
                JwtEncoderParameters.from(jwtClaimsSet)
        ).getTokenValue();
        return Map.of("token", token);
    }
}
