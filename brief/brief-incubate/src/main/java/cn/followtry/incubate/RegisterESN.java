package cn.followtry.incubate;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *  brief-incubate/cn.followtry.incubate.RegisterESN
 * @author 
 *		jingzz 
 * @since 
 *		2017年1月4日 下午6:56:10
 */
public class RegisterESN {
	
	public static void main(String[] args) throws IOException, UnirestException {
		HttpResponse<String> response = Unirest.post("https://ezone.upesn.com//signin/attentance/encryptSignIn?token=cf43a346-5784-481e-a1fa-7c918ea00ef5")
				  .header("content-type", "application/x-www-form-urlencoded")
				  .header("connection", "keep-alive")
				  .header("accept", "*/*")
				  .header("user-agent", "esn/3.0.7 (iPhone; iOS 10.2; Scale/2.00)")
				  .header("accept-language", "zh-Hans-CN;q=1")
				  .header("content-length", "532")
				  .header("accept-encoding", "gzip, deflate")
				  .header("cache-control", "no-cache")
				  .header("postman-token", "4ecee25a-936d-e706-a8f4-b2fcee2f9c46")
				  .body("encryptedAttentance=A5A6034E3B79FE990ECA21D8E0A64AB6FA71E47B58C5F3CE66FEAE25FCD02DCF2C1109B3A33E2377FE923F039D9C5A46B352802B6ACEEB5F3EF3E99ADF01E43A725C22763BA5A2321158779504941319B09693262DF5D0BBD97B89796DECA5D577D65F21FA6F29AA1D90B2798AE46404E884E09E80283FC804DB0C0C014C244462BB60CA669A490BCC1F29399053D0F822E02F8B01BE73FFE6116C74DF66FC8734372D8E767EADC0C7C0E0C24761F1BC3A9BD626B6CCE852A4889D57B366463AB0A408127100C51438814E4CF722C4D9ADE2F9E54E40291BA99A74A40AF91102B4AE5C07CC56F26393FFBF86B223E16857E2A4EB0B5E7319F34C45C24D10724D")
				  .asString();
		String body = response.getBody();
	}

	/**
	 * @author jingzz
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	private static void okhttpPost() throws IOException, UnsupportedEncodingException {
		String encryData ="A5A6034E3B79FE990ECA21D8E0A64AB6FA71E47B58C5F3CE66FEAE25FCD02DCF2C1109B3A33E2377FE923F039D9C5A46B352802B6ACEEB5F3EF3E99ADF01E43A725C22763BA5A2321158779504941319B09693262DF5D0BBD97B89796DECA5D577D65F21FA6F29AA1D90B2798AE46404E884E09E80283FC804DB0C0C014C244462BB60CA669A490BCC1F29399053D0F822E02F8B01BE73FFE6116C74DF66FC8734372D8E767EADC0C7C0E0C24761F1BC3A9BD626B6CCE852A4889D57B366463AB0A408127100C51438814E4CF722C4D9ADE2F9E54E40291BA99A74A40AF91102B4AE5C07CC56F26393FFBF86B223E16857E2A4EB0B5E7319F34C45C24D10724D";

		OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		
		String content = "encryptedAttentance="+encryData;
		RequestBody body = RequestBody.create(mediaType, content);
		Request request = new Request.Builder()
		  .url("https://ezone.upesn.com//signin/attentance/encryptSignIn?token=cf43a346-5784-481e-a1fa-7c918ea00ef5")
		  .post(body)
		  .addHeader("content-type", "application/x-www-form-urlencoded")
		  .addHeader("connection", "keep-alive")
		  .addHeader("accept", "*/*")
		  .addHeader("user-agent", "esn/3.0.7 (iPhone; iOS 10.2; Scale/2.00)")
		  .addHeader("accept-language", "zh-Hans-CN;q=1")
		  .addHeader("content-length", "532")
		  .addHeader("accept-encoding", "gzip, deflate")
		  .addHeader("cache-control", "no-cache")
		  .addHeader("postman-token", "b7b6a64c-70ef-2178-5eef-cfd255dc9b7f")
		  .build();

		Call newCall = client.newCall(request);
		Response response = newCall.execute();
		InputStream is = response.body().byteStream();
//		System.out.println(response.body().string());
		String string = new String(response.body().bytes(), "utf-8");
		System.out.println(string);
//		System.out.println(response.request().header("user-agent"));
	}
}
