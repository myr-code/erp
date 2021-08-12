//主页的项目名
var path = $(window.parent.document.body).find("#path").val();

//未开发提示 点击后提示
function tips(){
	alert("该功能未配置，敬请期待！");
}

//复选框选中的个数 返回选中的数量
function chklen(){
	var len = $("input[type='checkbox']:checked").length;
	return len;
}

//获取checkbox选中的value数据 返回int[] 数组
function getCheckedVal() {
	//$('input:checkbox:checked') 等同于 $('input[type=checkbox]:checked')
	var trs  = new Array();
	var i = 0;
	$.each($('input:checkbox:checked'), function() {

		if(trs.indexOf($(this).val()) == -1){//不重复
			trs[i] = $(this).val();
			i++;
		}

	});
	return trs;
}

//正整数判断 var regex = /^([1-9]\d{0,15}|0)(\.\d{1,4})?$/;
function checkNum(obj){
	var value = obj.value;
	var re = /^[1-9]{1}[0-9]*$/;//正整数
	if(!re.test(value)){
		obj.value=0;
		alert("请输入正整数！");
	}
}

//正数小数点1位判断
function checkNum1(obj){
	var value = obj.value;
	var re = /^([1-9]\d{0,15}|0)(\.\d{1,1})?$/;//正数1位小数点
	if(!re.test(value)){
		obj.value=0;
		alert("请输入正确数值！(1位小数点)");
		return false;
	}
}

//正数小数点2位判断
function checkNum2(obj){
	var value = obj.value;
	var re = /^([1-9]\d{0,15}|0)(\.\d{1,2})?$/;//正数2位小数点
	if(!re.test(value)){
		obj.value=0;
		alert("请输入正确数值！(2位小数点)");
		return false;
	}
}

//正数小数点3位判断
function checkNum3(obj){
	var value = obj.value;
	var re = /^([1-9]\d{0,15}|0)(\.\d{1,3})?$/;//正数3位小数点
	if(!re.test(value)){
		obj.value=0;
		alert("请输入正确数值！(3位小数点)");
		return false;
	}
}

//正数小数点4位判断
function checkNum4(obj){
	var value = obj.value;
	var re = /^([1-9]\d{0,15}|0)(\.\d{1,4})?$/;//正数4位小数点
	if(!re.test(value)){
		obj.value=0;
		alert("请输入正确数值！(4位小数点)");
		return false;
	}
}