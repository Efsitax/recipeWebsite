package com.Kadir.recipeWebsite.Configs;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieTokenAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        // Çerezden tokeni alarak kullanıcının kimliğini belirleyin
        String token = getCookieToken(request);
        // Tokeni kullanarak kullanıcıyı doğrulayın ve gerekirse kullanıcının rolünü alın
        String role = authenticateAndRetrieveRole(token);
        // Kullanıcının kimlik bilgilerini veya yetkisini döndürün
        return new UserAuthenticationToken(token, role);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        // Kimlik doğrulamasında kullanılan herhangi bir bilgiyi döndürün (burada token döndürüyoruz)
        return getCookieToken(request);
    }

    private String getCookieToken(HttpServletRequest request) {
        // Çerezi alarak tokeni döndürün (burada çerez adı "token" olarak varsayılmıştır)
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // "Bearer " önekini kaldırarak tokeni alın
        }
        return null;
    }

    private String authenticateAndRetrieveRole(String token) {
        // Token doğrulama işlemlerini gerçekleştirin
        // Örnek olarak, tokenin geçerliliğini kontrol edelim ve kullanıcının rolünü alalım

        if (isValidToken(token)) {
            // Token doğrulandıysa, kullanıcının rolünü döndürün
            return getRoleFromToken(token);
        }

        return null;
    }
    private boolean isValidToken(String token) {
        // Tokenin geçerliliğini kontrol etmek için gerekli doğrulama işlemlerini gerçekleştirin
        // Örnek olarak, tokenin geçerli bir JWT olup olmadığını ve son kullanma süresini kontrol edebilirsiniz

        try {
            // Tokeni doğrulama işlemlerini gerçekleştirin

            // Örneğin, JWT kütüphanesi kullanarak tokenin geçerliliğini kontrol edebilirsiniz
            // JWT kütüphanesinin kullanımına bağlı olarak:
            // - Tokenin imzasını doğrulayın
            // - Tokenin son kullanma süresini kontrol edin
            // - Tokeni önbellekte veya veritabanında kontrol edin, vb.

            // Örnek JWT doğrulama işlemi:
            JwtParser jwtParser = Jwts.parser().setSigningKey("secretKey"); // Gizli anahtarınızı buraya yerleştirin
            jwtParser.parseClaimsJws(token);

            return true; // Token geçerli ise true döndürün
        } catch (Exception e) {
            // Token geçerli değilse veya doğrulama hatası oluştuysa false döndürün
            return false;
        }
    }

    private String getRoleFromToken(String token) {

        try {
            JwtParser jwtParser = Jwts.parser().setSigningKey("secretKey"); // Gizli anahtarınızı buraya yerleştirin
            Jws<Claims> claims = jwtParser.parseClaimsJws(token);
            String role = claims.getBody().get("role", String.class);

            return role; // Kullanıcının rolünü döndürün
        } catch (Exception e) {
            // Token çözümleme hatası oluştuysa veya rol bilgisi bulunamadıysa null döndürün
            return null;
        }
    }
}
