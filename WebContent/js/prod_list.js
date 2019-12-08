/**
 * 
 */

$(document).ready(
		function() {
			var win = $(window);
			var count = 0;

			$.urlParam = function(name) {
				var results = new RegExp('[\?&]' + name + '=([^&#]*)')
						.exec(window.location.href);
				if (results == null) {
					return null;
				} else {
					return results[1] || 0;
				}
			}

			// Each time the user scrolls
			win.scroll(function() {
				// End of the document reached?
				if ($(document).height() - win.height() == win.scrollTop()) {
					// $("section ul").append("<li><img
					// src=\"/shopping/images/test.png\" /><div class=\"count\"
					// id=\"a\"></div></li>");
					var query = {
						page : count++,
						category : $.urlParam("category")
					};
					$.ajax({
						type : "POST",
						url : '/shopping/moreListAction.do',
						data : query,
						success : function(data) {
							$("section ul").append(data);
						}
					});

				}
			});
		});
