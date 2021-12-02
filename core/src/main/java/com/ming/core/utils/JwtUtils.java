package com.ming.core.utils;


//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * jwt 工具类
 * https://www.jianshu.com/p/576dbf44b2ae
 *
 * @author ming
 * @date 2021-07-07 14:53
 */
@Slf4j
public class JwtUtils {
//    private static final String HMAC_256_SECRET = "ming";
//    private static final Algorithm ALGORITHM = Algorithm.HMAC256(HMAC_256_SECRET);
//    private static final String IS_USER = "ming";
//    private static final String CLAIM_KEY = "data";
//    private static final JWTVerifier JWT_VERIFIER = JWT.require(ALGORITHM)
//            //匹配指定的token发布者
//            .withIssuer(IS_USER)
//            .build();
//
//
//    /**
//     * 生成jwt  默认两个小时
//     *
//     * @param data 自定义数据
//     * @return String jwt
//     * @author ming
//     * @date 2021-07-07 17:10
//     */
//    public static String create(String data) {
//        return create(data, 2 * 60);
//    }
//
//    /**
//     * 生成jwt
//     *
//     * @param data    自定义数据
//     * @param minutes jwt有效时长  单位分钟
//     * @return String jwt
//     * @author ming
//     * @date 2021-07-07 17:10
//     */
//    public static String create(String data, int minutes) {
//        Date date = new Date();
//        return JWT.create()
//                // 发布者
//                .withIssuer(IS_USER)
//                // 生成签名的时间
//                .withIssuedAt(date)
//                // 生成签名的有效期
//                .withExpiresAt(DateUtils.addMinutes(date, minutes))
//                // 插入数据
//                .withClaim(CLAIM_KEY, data)
//                .sign(ALGORITHM);
//
//    }
//
//    /**
//     * 验证token
//     *
//     * @param token jwt
//     * @return DecodedJWT
//     * @throws JWTVerificationException
//     * @author ming
//     * @date 2021-07-07 17:21
//     */
//    public static DecodedJWT verifier(String token) throws JWTVerificationException {
//        return JWT_VERIFIER.verify(token);
//    }
//
//    /**
//     * 验证并且获取jwt中的传入的数据
//     *
//     * @param token jwt
//     * @return String   data
//     * @author ming
//     * @date 2021-07-07 17:52
//     */
//    public static String getData(String token) {
//        return verifier(token).getClaim(CLAIM_KEY).asString();
//    }
//
//    /**
//     * 获取jwt中的传入的数据
//     *
//     * @param decodedJWT decodedJWT
//     * @return String   data
//     * @author ming
//     * @date 2021-07-07 17:52
//     */
//    public static String getData(DecodedJWT decodedJWT) {
//        return decodedJWT.getClaim(CLAIM_KEY).asString();
//    }

}
