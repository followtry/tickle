package cn.followtry.comm.token;
/**
 * 
 */

/**
 * 
 *  brief-comm/cn.followtry.comm.token.Token
 * @author 
 *		jingzz 
 * @since 
 *		2017年1月10日 下午3:16:25
 */
public class Token {

	private String token;

	private Long expiration; // 过期时间

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getExpiration() {
		return expiration;
	}

	public void setExpiration(Long expiration) {
		this.expiration = expiration;
	}

	@Override
	public String toString() {
		return "Token [token=" + token + ", expiration=" + expiration + "]";
	}
}
