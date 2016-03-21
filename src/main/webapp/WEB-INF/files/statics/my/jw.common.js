// JavaScript Document
$(document).ready(function() {
	jmxweb.handleGoTop();// go top
	jmxweb.initViewMail();//查看邮件
	//$(".js_viewMail").click();
});

var jmxweb = {

	initViewMail : function() {
		console.log("jmxweb.initViewMail()");
		$(".js_viewMail").click(function(e) {
			e.preventDefault();//先禁止页面跳转，我们是用ajax查看邮件的
			
            var msgId = $(this).attr("data-msgId");
			console.log("view mail msgid=" + msgId);
        });
	},

	handleGoTop : function() {
		console.log("jmxweb.handleGoTop()");
		var offset = 100;
		var duration = 500;

		if (navigator.userAgent.match(/iPhone|iPad|iPod/i)) { // ios supported
			$(window).bind("touchend touchcancel touchleave", function(e) {
				if ($(this).scrollTop() > offset) {
					$('.scroll-to-top').fadeIn(duration);
				} else {
					$('.scroll-to-top').fadeOut(duration);
				}
			});
		} else { // general
			$(window).scroll(function() {
				if ($(this).scrollTop() > offset) {
					$('.scroll-to-top').fadeIn(duration);
				} else {
					$('.scroll-to-top').fadeOut(duration);
				}
			});
		}

		$('.scroll-to-top').click(function(e) {
			e.preventDefault();
			$('html, body').animate({
				scrollTop : 0
			}, duration);
			return false;
		});
	},

};