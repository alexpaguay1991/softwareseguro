package ec.edu.espe.utils;

import ec.edu.espe.excepciones.EcovidaAppException;
import io.jsonwebtoken.*;
import org.springframework.http.HttpStatus;

import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class JWTUtil {

    private static final String SECRET_KEY = "JWTSecretKey"; // Cambia esto por una clave más segura

    public static String generateToken(String username) {
        Key key = new SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA512"); // Usamos HmacSHA512 para HS512
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Expira en 1 día
                .signWith(SignatureAlgorithm.HS512, key) // Cambiado a HS512
                .compact();
    }

    /*public static Claims validateToken(String token) throws SignatureException {
        System.out.println("Token: " + token);
        try {
            Key key = new SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA512"); // Usamos HmacSHA512 para HS512

            return Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            System.err.println("Error al validar el token: " + e.getMessage());
            throw new SignatureException("Token inválido o expirado", e);
        }
    }*/
    public static boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex) {
            System.err.println("Error  " + ex.getMessage());
            throw new EcovidaAppException(HttpStatus.BAD_REQUEST,"Firma JWT no valida");
        }
        catch (MalformedJwtException ex) {
            System.err.println("Error  " + ex.getMessage());
            throw new EcovidaAppException(HttpStatus.BAD_REQUEST,"Token JWT no valida");
        }
        catch (ExpiredJwtException ex) {
            System.err.println("Error  " + ex.getMessage());
            throw new EcovidaAppException(HttpStatus.BAD_REQUEST,"Token JWT caducado");
        }
        catch (UnsupportedJwtException ex) {
            System.err.println("Error  " + ex.getMessage());
            throw new EcovidaAppException(HttpStatus.BAD_REQUEST,"Token JWT no compatible");
        }
        catch (IllegalArgumentException ex) {
            System.err.println("Error  " + ex.getMessage());
            throw new EcovidaAppException(HttpStatus.BAD_REQUEST,"La cadena claims JWT esta vacia");
        }
    }
}