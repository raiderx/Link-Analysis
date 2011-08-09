(function($) {
	
	$.ajaxDialog = function(id, url, options, data) {
		var ajaxRequest;
		var dialogHolder = $("<div id='" + id + "' />");
		
		function onDialogOpen() {
			function onSubmit(data, request, form) {
				if (data) {
					dialogHolder.dialog("close");
				}
			}
			
			var form = dialogHolder.find(".dialogForm");
			form.ajaxForm({ dataType: "json", success: onSubmit, data: options.extraFormData || {} });
			options.onOpen(dialogHolder);
		}
		
		function onDialogClose() {
			options.onClose();
			dialogHolder.dialog("destroy");
			dialogHolder.remove();
		}
		
		options = $.extend({
			modal: true,
			open: onDialogOpen,
			close: onDialogClose,
			onOpen: function(dialogHolder) {},
			onClose: function() {},
			autoOpen: false,
			type: "GET"
		}, options || {});
		
		function openDialog(content) {
			dialogHolder.html(content);
			
			var form = dialogHolder.find(".dialogForm");

            options.title = options.title || form.attr("title") || "Dialog";
            options.width = options.width || parseInt(form.attr("width")) || 270;
            options.height = options.height || parseInt(form.attr("height")) || 230;

            form.removeAttr("title");
            form.removeAttr("width");
            form.removeAttr("height");

			dialogHolder.dialog(options);
			dialogHolder.dialog("open");
		}
		
		function errorHandler(xhr, textStatus, errorThrown) {
			alert("Error processing server request: " + xhr.status);
		}
		
		if (ajaxRequest != null)
			ajaxRequest.abort();
		ajaxRequest = $.ajax({ url: url, type: options.type, data: $.extend({ dialog: id }, data || {}), success: openDialog, error: errorHandler });
	}
	
	$.fn.ajaxTable = function(options) {

		var isMethodCall = (typeof options == 'string'), args = Array.prototype.slice.call(arguments, 1);
		if (isMethodCall) {
			return this.each(function() {
				var instance = $.data(this, "ajaxTable");
				instance[options].apply(instance, args);
			});
		}

		options = $.extend({
			table: this,
			id: this.attr("id"),
            filter: "#filterForm",
			url: "table.html"
		}, options || {});
		
		this.initialize = function() {
			onTableLoad();
			return this;
		}
		
		this.reload = function() {
			updateTable();
			return this;
		}
		
		function onTableLoad(content) {
			if (content) {
				options.table.html(content);
			}
		}
		
		function updateTable() {
			send();
		}

		function onLoadError(xmlHttpRequest, textStatus, errorThrown) {
			if (textStatus && textStatus != "success") {
				options.table.html("<span class='error'> Cannot request data from server. HTTP status  " + xmlHttpRequest.status + " </span>");
			}
		}
		
		function send() {
			$.ajax({ url: options.url, data: {}, success: onTableLoad, error: onLoadError });
		}

        $(options.filter).submit(function() {
			updateTable({ Page: 0 }); // if user clicked 'Apply Filter' button table must show first page.
			return false;
		});
		
		updateTable();
		
		this.data('ajaxTable', this);
		
		return this;
	}

    $.ajaxHelper = {
        /**
        * Usage:
        * $.ajaxHelper.performAjaxRequest(
        *   url:        *Required, url to perform ajax request
        *   data:       *Required, data that will be serialized into classic POST format and placed in body of ajax post request
        *   options:     Optional, may contains onSuccess: function(result) {} and/or error: function(errorMessage) {} callbacks
        * );
        **/
        performAjaxRequest: function(url, data, options) {
            var options = $.extend(
							{ onSuccess: function() { }, onError: function(msg) { } },
							(options || {}));

            var ajaxOptions =
							{
							    url: url,
							    type: "POST",
							    dataType: "json",
							    data: data,
							    success: function(result, httpCode) {
							        if (result && result.__ERROR_OCCURRED_MARKER) {
							            options.onError(result.ErrorMessage);
							            return;
							        }
							        options.onSuccess(result);
							    },
							    error: function(xhr, message, optional) {
							        options.onError("Connection or server error");
							    }
							};

            $.extend(ajaxOptions, options);
            return $.ajax(ajaxOptions);
        }
    }

})(jQuery);