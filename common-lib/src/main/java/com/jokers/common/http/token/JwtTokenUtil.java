package com.jokers.common.http.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jokers.common.date.DateUtil;
import com.jokers.common.uuid.UUIDUtil;
import com.jokers.pojo.bo.UserBo;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * JwtTokenUtil
 * Created by yuTong on 2018/03/08.
 */
public class JwtTokenUtil {
    public static String createToken(UserBo userBo, String secret, String issuer) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(issuer)
                    .withClaim("userId", userBo.getUserId())
                    .withClaim("role", userBo.getRoleName())
                    .withExpiresAt(DateUtil.addWeeks(new Date(), 1))
                    .withSubject(userBo.getUsername())
                    .withIssuedAt(new Date())
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
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 从token中解析中用户信息
     */
    public static UserBo getAuthentication(String token) {

        DecodedJWT decodedJWT = JWT.decode(token);
        String authorityString = decodedJWT.getClaim("role").asString();
        UserBo user = new UserBo();
        user.setUsername(decodedJWT.getSubject());
        user.setRoleName(authorityString);
        return user;
    }

    /**
     * 从token中获取用户名
     *
     * @param token String
     */
    public static String getUsername(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getSubject();
    }

    /**
     * 刷新token
     *
     * @param oldToken 旧token
     */
    public static String refreshToken(String oldToken, String secret, String issuer) {

        try {
            if (validateToken(oldToken, secret)) {
                DecodedJWT jwt = JWT.decode(oldToken);
                if (StringUtils.isNotBlank(jwt.getSubject())) {
                    return JWT.create()
                            .withIssuer(issuer)
                            .withClaim("userId", jwt.getClaim("userId").asString())
                            .withClaim("role", jwt.getClaim("role").asString())
                            .withExpiresAt(DateUtil.addWeeks(new Date(), 1))
                            .withSubject(jwt.getSubject())
                            .withIssuedAt(new Date())
                            .withJWTId(UUIDUtil.getShortUUid())
                            .sign(Algorithm.HMAC256(secret));
                }
            }
        } catch (UnsupportedEncodingException ignore) {
        }
        return null;
    }
}
