package cn.followtry.test.token;
/**
 * 
 */

/**
 * @author jingzz
 * @time 2016年3月30日 下午2:20:42
 * @name service-im/com.yonyou.worktime.service.im.model.Token
 * @since 2016年3月30日 下午2:20:42
 */
public class Token {

	private String token; // UserToken或APPToken

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
