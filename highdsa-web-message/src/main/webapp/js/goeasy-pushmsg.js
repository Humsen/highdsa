/**
 * @Desc goeasy接收消息推送
 * 
 * @Author 何明胜
 * 
 * @Created at 2018年3月13日 下午7:51:28
 * 
 * @Version 1.0.0
 */

var goEasy;

$(function() {
	initGoEasy();
});

/**
 * 初始化goeasy
 * 
 * @returns
 */
function initGoEasy() {
	$.ajax({
		url : 'comet/v1/app_key.hms',
		type : 'GET',
		success : function(data) {
			if (data.success == 'true') {
				goEasy = new GoEasy({
					appkey : data.app_key,
				});

				subscribeGoEasy();
			} else {
				alert('获取app key失败!');
			}
		}
	});
}

/**
 * 订阅goeasy
 * 
 * @returns
 */
function subscribeGoEasy() {
	goEasy
			.subscribe({
				channel : 'restful',
				onMessage : function(message) {
					alert("您有新消息：channel：" + message.channel + " 内容："
							+ message.content);
				},
				onSuccess : function() {
					alert("Channel订阅成功。");
				},
				onFailed : function(error) {
					alert("Channel订阅失败, 错误编码：" + error.code + " 错误信息："
							+ error.content)
				}
			});
}