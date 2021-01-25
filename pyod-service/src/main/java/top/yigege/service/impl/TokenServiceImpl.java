package top.yigege.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yigege.config.JwtConfig;
import top.yigege.constant.PyodConstant;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.exception.BusinessException;
import top.yigege.model.User;
import top.yigege.service.ITokenService;

import java.util.Date;

/**
 * @ClassName: TokenServiceImpl
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月05日 16:39
 */
@Service
public class TokenServiceImpl implements ITokenService {

    @Autowired
    JwtConfig jwtConfig;

    @Override
    public String getToken(User user) {
        //过期时间
        long exp = 0;
        long now = System.currentTimeMillis();
        exp = now + 1000 * 60 * jwtConfig.getExpire();

        String token = "";
        token = JWT.create()
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(exp))
                .withClaim(PyodConstant.JWT.USER_ID,user.getUserId())
                .sign(Algorithm.HMAC256(jwtConfig.getSecret()));
        return token;
    }

    /**
     * 检查token
     * @param token
     * @return
     */
    @Override
    public boolean checkToken(String token) {
        if (StringUtils.isBlank(token)) {
            throw new BusinessException(ResultCodeEnum.TOKEN_ERROR);
        }


        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtConfig.getSecret())).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            //包含了过期的异常
           throw new BusinessException(ResultCodeEnum.ILLEGAL_TOKEN_ERROR);
        }

        Claim userIdClaim = jwt.getClaims().get(PyodConstant.JWT.USER_ID);
        if (null == userIdClaim || null == userIdClaim.asLong()) {
            throw new BusinessException(ResultCodeEnum.ILLEGAL_TOKEN_ERROR);
        }

        return true;
    }

    /**
     * 返回userId
     * @param token
     * @return
     */
    @Override
    public Long getUserId(String token){
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtConfig.getSecret())).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            //包含了过期的异常
            throw new BusinessException(ResultCodeEnum.ILLEGAL_TOKEN_ERROR);
        }

        Claim userIdClaim = jwt.getClaims().get(PyodConstant.JWT.USER_ID);
        if (null == userIdClaim || null == userIdClaim.asLong()) {
            throw new BusinessException(ResultCodeEnum.ILLEGAL_TOKEN_ERROR);
        }

        return userIdClaim.asLong();
    }
}
