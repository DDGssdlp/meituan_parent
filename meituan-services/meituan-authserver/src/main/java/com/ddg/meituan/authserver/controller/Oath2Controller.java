package com.ddg.meituan.authserver.controller;



import com.ddg.meituan.common.utils.HttpUtils;
import com.ddg.meituan.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title: Oath2Controller</p>
 * Description：
 * date：2020/6/26 14:14
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class Oath2Controller {

	@GetMapping("qq/success")
	public R qqLogin(){
		ResponseEntity<String> stringResponseEntity = HttpUtils.postForJson(null, null, null);
		String body = stringResponseEntity.getBody();
		// TODO 登录或者是注册：
		return R.ok();
	}


}
