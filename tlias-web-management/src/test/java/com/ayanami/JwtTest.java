package com.ayanami;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtTest {
    /**
     * 生成JWT令牌
     */
    @Test
    public void testGenJwt(){
        Map<String,Object> dataMap=new HashMap<>();
        dataMap.put("id",1);
        dataMap.put("username","admin");
        String jwt=Jwts.builder().signWith(SignatureAlgorithm.HS256,"YXlhbmFtaQo=")//指定加密算法，密钥
                .addClaims(dataMap)//添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis()+12*3600*1000))//设置过期时间
                .compact();//生成令牌
        System.out.println(jwt);
    }

    /**
     * 解析令牌
     */
    @Test
    public void testParseJwt() {
        String token="eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc3MjQ4OTUzN30.Hnm7e4xv4JLnCqO7Jdx2UNjLEgUUXa4AASk47i6ds_4";
        Claims claims = Jwts.parser().setSigningKey("YXlhbmFtaQo=")//制订密钥
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims);
    }
}
