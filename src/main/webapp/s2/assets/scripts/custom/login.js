var Login = function () {

	var handleLogin = function() {
	        $('.login-form input').keypress(function (e) {
	            if (e.which == 13) {
	                if ($('.login-form').validate().form()) {
	                    $('.login-form').submit(); //form validation success, call ajax form submit
	                }
	                return false;
	            }
	        });
	}
    
    return {
        init: function () {
            handleLogin();
        }

    };

}();