/**
 * @Desc 测试md5加密
 * 
 * @Author 何明胜
 * 
 * @Created at 2018年3月17日 下午4:57:56
 * 
 * @Version 1.0.0
 */

$(function() {
	btnShowClick();
});

/**
 * 显示按钮点击事件
 * 
 * @returns
 */
function btnShowClick() {
	$('#btn_show').click(function() {
		$('#p_showMd5').text($.md5($('#txt_input').val()));
	});
}