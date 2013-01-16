/*
	xBreadcrumbs (Extended Breadcrumbs) jQuery Plugin
	© 2009 ajaxBlender.com
	For any questions please visit www.ajaxblender.com 
	or email us at support@ajaxblender.com
 */

;
(function($) {
	/* Variables */
	$.fn.ikarusBreadcrumbs = function(settings) {
		var element = $(this);
		var settings = $.extend({}, $.fn.ikarusBreadcrumbs.defaults, settings);

		function _build() {
			if (settings.collapsible) {
				var sz = element.children('LI').length;
				element.children('LI').children('div').css('white-space',
						'nowrap').css('float', 'left');
				element.children('LI').children('div').each(function(i, el) {
					if (!$(this).parent().hasClass('current')) {
						$(this).css('overflow', 'hidden');
						var initwidth = $(this).attr('init-width');
						if (initwidth == null) {
							$(this).attr('init-width', ($(this).width() + 10));
						}
						$(this).width(settings.collapsedWidth);
					}
				});
			}

			element.children('LI').mouseenter(function() {
				if ($(this).hasClass('hover')) {
					return;
				}

				if (settings.collapsible && !$(this).hasClass('current')) {
					var initWidth = $(this).children('div').attr('init-width');
					$(this).children('div').animate({
						width : initWidth
					}, 'normal');
				}
			});

			element.children('LI').mouseleave(function() {

				if (settings.collapsible && !$(this).hasClass('current')) {
					$(this).children('div').animate({
						width : settings.collapsedWidth
					}, 'fast');
				}
			});
		}
		;

		// Entry point
		_build();
	};

	/* Default Settings */
	$.fn.ikarusBreadcrumbs.defaults = {
		showSpeed : 'fast',
		hideSpeed : '',
		collapsible : false,
		collapsedWidth : 10
	};
})(jQuery);