package landing.JWT;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

public class JWT {

    private long time=1000*60*60*24;
    private String singlator="admin";

    public String jWT() {
        JwtBuilder JwtBuilder= Jwts.builder();
        String jwtToken=JwtBuilder
                // 设置 JWT Token 的类型为 JWT
                .setHeaderParam("typ","JWT")
                // 设置 JWT Token 的签名算法为 HS256
                .setHeaderParam("alg","HS256")
                // 设置 JWT Token 中的自定义声明信息，存储用户的用户名
                .claim("username","8848")
                // 设置 JWT Token 中的自定义声明信息，存储用户的角色
                .claim("password","8848")
                // 设置 JWT Token 的主题为 "admin-test"
                .setSubject("admin-test")
                // 设置 JWT Token 的过期时间为当前时间加上 time
                .setExpiration(new Date(System.currentTimeMillis()+time))
                // 设置 JWT Token 的唯一标识符为一个随机生成的 UUID
                .setId(UUID.randomUUID().toString())
                // 使用 ES256 算法和签名密钥对 JWT Token 进行签名
                .signWith(SignatureAlgorithm.ES256,singlator)
                // 将 JwtBuilder 对象编码为一个 JWT Token 字符串
                .compact();
        return jwtToken;
    }
}
