package com.jokers.common.http.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jokers.common.uuid.UUIDUtil;
import com.jokers.pojo.bo.JwtBo;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * JwtTokenUtil
 * Created by yuTong on 2018/03/08.
 */
public class JwtTokenUtil {
    private final static String ROLE = "role";
    private final static String USER_ID = "userId";

    public static String createToken(JwtBo jwtBo) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtBo.getSecret());
            return JWT.create()
                    .withIssuer(jwtBo.getIssuer())
                    .withClaim(USER_ID, jwtBo.getUserId())
                    .withClaim(ROLE, jwtBo.getRoleName())
                    .withExpiresAt(jwtBo.getExpiresAt())
                    .withSubject(jwtBo.getSubject())
                    .withIssuedAt(jwtBo.getIssuedAt())
                    .withJWTId(UUIDUtil.getShortUUid())
                    .sign(algorithm);
        } catch (UnsupportedEncodingException ignore) {
        }
        return null;
    }

    public static boolean validateToken(String token, String secret) {
        if (StringUtils.isBlank(token)) {
            return false;
        }
        try {
            JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
        } catch (TokenExpiredException | UnsupportedEncodingException e) {
            return false;
        }
        return true;
    }

    /**
     * 从token中解析中用户信息
     *
     * @param token 生成token
     * @return JwtBo
     */
    public static JwtBo getAuthentication(String token) {

        DecodedJWT decodedJWT = JWT.decode(token);
        String authorityString = decodedJWT.getClaim(ROLE).asString();
        JwtBo user = new JwtBo();
        user.setUsername(decodedJWT.getSubject());
        user.setRoleName(authorityString);
        return user;
    }

    /**
     * 从token中获取用户名
     *
     * @param token String
     * @return String
     */
    public static String getUsername(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getSubject();
    }

    /**
     * 刷新token
     *
     * @param jwtBo JwtBo
     * @param oldToken 旧token
     * @return String
     */
    public static String refreshToken(String oldToken, JwtBo jwtBo) {
        try {
            if (!validateToken(oldToken, jwtBo.getSecret())) {
                DecodedJWT jwt = JWT.decode(oldToken);
                if (StringUtils.isNotBlank(jwt.getSubject())) {
                    return JWT.create()
                            .withIssuer(jwt.getIssuer())
                            .withClaim(USER_ID, jwt.getClaim(USER_ID).asString())
                            .withClaim(ROLE, jwt.getClaim(ROLE).asString())
                            .withExpiresAt(jwtBo.getExpiresAt())
                            .withSubject(jwt.getSubject())
                            .withIssuedAt(jwtBo.getIssuedAt())
                            .withJWTId(UUIDUtil.getShortUUid())
                            .sign(Algorithm.HMAC256(jwtBo.getSecret()));
                }
            }
        } catch (UnsupportedEncodingException ignore) {
        }
        return null;
    }
}
