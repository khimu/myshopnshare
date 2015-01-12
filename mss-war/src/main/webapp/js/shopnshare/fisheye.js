			$(document).ready(function() {		
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
