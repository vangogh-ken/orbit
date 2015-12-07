var Login = function () {
	var handleLogin = function() {
        $('.login-form input').keypress(function (e) {
            if (e.which == 13) {
               $('.login-form').submit();
            }
        });
	}

    return {
        //init
        init: function () {
            handleLogin();
	       	$.backstretch([
		        "/V/s2/assets/img/bg/1.jpg",
		        "/V/s2/assets/img/bg/2.jpg",
		        "/V/s2/assets/img/bg/3.jpg",
		        "/V/s2/assets/img/bg/4.jpg"
		        ], {
		          fade: 1000,
		          duration: 8000
		    });
        }

    };

}();