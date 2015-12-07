
//定位菜单,且将菜单信息保存在session中,避免刷新的时候菜单回复称原样
function locateMenu(id) {
	var currentTopMenu = id;
	var currentSubMenu = id;
	var currentSubMenuName = $("#" + id + " span:last").text();
	var currentTopMenuName = currentSubMenuName;

	try {
		var idtop = $("#" + id).parent().parent().attr("id");
		if (idtop != undefined) {
			currentTopMenu = idtop
			currentTopMenuName = $("span:first", $("#" + id).parent().parent()).text();
		}
	} catch (e) {

	}
	
	$.ajax({
		type : "post",
		url : "/session/sessoin-locate-menu.do",
		async : false, //是否ajax同步
		data : "currentTopMenu=" + currentTopMenu + "&currentSubMenu="
				+ currentSubMenu + "&currentTopMenuName=" + currentTopMenuName
				+ "&currentSubMenuName=" + currentSubMenuName + "&gnDm=" + id,
		success : function(msg) {
		}
	});

}