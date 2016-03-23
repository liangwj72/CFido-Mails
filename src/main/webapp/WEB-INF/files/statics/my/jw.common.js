// JavaScript Document
$(document).ready(function() {
	jmxweb.handleGoTop();// go top
	jmxweb.initViewMail();//查看邮件
	jmxweb.initPageBar();//翻页按钮
	//$(".js_viewMail").click();
});

var jmxweb = {

	initViewMail : function() {
		console.log("jmxweb.initViewMail()");
		$(".js_viewMail").click(function(e) {
			$("#viewMailDiv").show();
        });
		
		$("#id_viewMailClose").click(function(e) {
			$("#viewMailDiv").hide();
        });
	},
	
	initPageBar : function() {
		$("#id_pageBar_go").click(function(e) {
			var action = $(this).attr("data-action");
			var limit = $(this).attr("data-limit");
			
			var page = parseInt($("#id_pageBar_pageNo").val());
			if (isNaN(page)) {
				page = 1;
			}
			
			if (page>limit) {
				page = limit;
			}
			
			var url = action + "/" + page;
			console.log("翻页到 " + url);
			location.href = url;
            
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