// JavaScript Document

$(document).ready(function() {

	var dateFormat = 'yyyy-mm-dd hh:ii';
	
	$(".js_form_datetime").datetimepicker({
        autoclose: true,
        format: dateFormat,
        pickerPosition: "bottom-left"
    });

	
	$('.js_boolean_checkbox').click(function(e) {
		var name = "#" + $(this).attr('data-name');
		var checked = $(this).is(':checked');
		console.log(name+":" + checked);
		
		var value = ""+checked;
		
		$(name+"_txt").text(value);
		$(name+"_input").val(value);
	});
	
	/**
	 * 执行opt
	 */
	$('.js_invoke_opt').click(function(e) {
		var formName = "#"+$(this).attr('data-form-name');
		console.log("Click on Invoke Btn, form=" + formName);

		var form=$(formName);
		var param = form.serialize();
		$.post("/invokeOpt", param, function(res) {
			console.log(res);
			if (res.success) {
				toastr["success"](res.opName,"Invoke Success");
				if (res.hasReturn) {
					$("#invodeResult_body").text(res.returnData);
					$("#invokeResult_modal").modal();
				}
			} else {
				toastr["error"](res.errorMsg, "Invoke fail!");
			}
		}, "json");

	});

	/**
	 * Modify mbean attribute value. 修改MBean属性的链接 We use Bootstrap Editable
	 * 
	 * @see http://vitalets.github.io/x-editable/docs.html
	 */
	$('.js_edit_attr').each(function(index, element) {

		// option
		var option = {
			url : '/ajaxChangeAttr',
			ajaxOptions : {
				type : 'post',
				dataType : 'json'
			},
			type  : 'textarea',
			mode  :'inline',
			value : '', // 初始值为空
			params : function(params) {
				params.objectName = $(this).attr("data-param-objectName");
				return params;
			},
			pk : 1,
			display : false,// 默认修改完成后不修改界面上的值
			success : function(response, newValue) {
				if (!response.success) {
					// @see Java JsonErrorResponse
					return response.errorMsg;
				} else {
					var valueType = $(this).attr('data-value-type');
					if (valueType == 'java.util.Date') {
						var old = response.newValue;
						var date = $(this).data('editable').input.str2value(old);					
						response.newValue = date; 
					}
					
					return response;
				}
			},
		}
		
		var valueType = $(this).attr('data-value-type');
		if (valueType == 'boolean' || valueType == 'java.lang.Boolean') {
			option.type = 'select';
            option.source = [{
	                value: 'false',
	                text: 'false'
	            }, {
	                value: 'true',
	                text: 'true'
	            }
	        ];
            option.showbuttons = false;
		} else if (valueType == 'java.util.Date') {
			option.type = 'datetime';
			option.format = 'yyyy-mm-dd hh:ii';
			option.viewformat = 'yyyy-mm-dd hh:ii';
			option.escape = true;
		}
		
		if ($(this).attr("data-readable") == "true") {
			// 如果该属性可读
			option.value = $(this).text();
			option.display = null;
		}

		$(this).editable(option);
	});
});
