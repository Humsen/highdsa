package pers.husen.highdsa.web.fastdfs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import pers.husen.highdsa.web.fastdfs.handler.FastdfsSvc;

/**
 * @Desc 文件消费者
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月26日 上午11:10:36
 * 
 * @Version 1.0.1
 */
@RestController
@RequestMapping("/fastdfs/v1")
public class FastdfsController {
	@Autowired
	FastdfsSvc fastdfsSvc;

	@ResponseBody
	@RequestMapping(value = "/upload.hms", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String fileUpload(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {

		return fastdfsSvc.fileUpload(request, response);
	}
}