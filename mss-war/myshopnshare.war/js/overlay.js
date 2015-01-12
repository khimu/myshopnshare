function showOverlayBox() {
		$('.overlayBox').css({
			display:'block',
			left:( $(window).width() - $('.overlayBox').width() )/2,
			top:( $(window).height() - $('.overlayBox').height() )/2 -20,
			position:'absolute' 
		});
		$('.bgCover').css({
			display:'block',
			width: $(window).width(),
			height:$(window).height(),
		})
}
$(window).bind('resize',showOverlayBox);
$('a.launchLink').click(
	function() {
		showOverlayBox();
		$('.bgCover').css({opacity:0}).animate( {opacity:0.5, backgroundColor:'#000'} );
	}
);
$('a.closeLink').click( function() {
	$('.overlayBox').css( 'display', 'none' );
	$('.bgCover').animate( {opacity:0,display:'none'},null,null, function() { $(this).hide(); } );
});
