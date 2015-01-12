
			$(document).ready(function() {
				$("#news a").click(function () {
					$(".active").removeClass("active");
					$(this).addClass("active");
					$(".news-content").hide();
					var content_show = $(this).attr("title");
					$("#"+content_show).show();				
				});						
				$('#fisheye').Fisheye(
						{
							maxWidth: 40,
							items: 'a',
							itemsText: 'span',
							container: '.fisheyeContainter',
							itemWidth: 40,
							proximity: 10,
							halign : 'center'
						}
					);
				$('#fisheye2').Fisheye(
						{
							maxWidth: 60,
							items: 'a',
							itemsText: 'span',
							container: '.fisheyeContainter',
							itemWidth: 40,
							proximity: 80,
							alignment : 'left',
							valign: 'bottom',
							halign : 'center'
						}
					)
			});								
