package com.devcamp.tokofable.security;

import com.devcamp.tokofable.config.AuthModel;
import com.google.common.hash.Hashing;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class Security {
    public Security() {
    }

    public static String hashPassword(String raw) {
        return raw == null ? null : Hashing.sha256().hashString(raw, StandardCharsets.UTF_8).toString();
    }

    public static boolean match(String raw, String hashed) {
        String a = Hashing.sha256().hashString(raw, StandardCharsets.UTF_8).toString();
        return hashed.equals(a);
    }

    public static String createToken(String userId, String roles, String tenantId, String salt, long expire) {
        return t(userId, roles, tenantId, salt, expire);
    }

    public static String createToken(String userId, String roles, String tenantId, String salt) {
        return t(userId, roles, tenantId, salt, -1L);
    }

    private static String t(String userId, String roles, String tenantId, String salt, long expire) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(salt);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder().setId(userId).setIssuedAt(now).setSubject(roles).setAudience(tenantId).signWith(signatureAlgorithm, signingKey);
        if (expire > 0L) {
            long expMillis = nowMillis + expire * 60L * 1000L;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    private static String rt(String token, long expire, String salt) {
        try {
            Claims claims = (Claims)Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(salt)).parseClaimsJws(token).getBody();
            return t(claims.getId(), claims.getSubject(), claims.getAudience(), salt, expire);
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static String recreateToken(String token, String salt) {
        return rt(token, -1L, salt);
    }

    public static String recreateToken(String token, long expire, String salt) {
        return rt(token, expire, salt);
    }

    public static AuthModel getModel(String token, String salt) throws ExpiredJwtException, MalformedJwtException {
        Claims claims = (Claims)Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(salt)).parseClaimsJws(token).getBody();
        return new AuthModel(claims.getId(), claims.getSubject(), claims.getAudience());
    }
}

