<%--
<script type="text/javascript" src="/js/jquery.easydrag.handler.beta2.js"></script>


$(function(){
	// add drag and drop functionality to #box1
	$("#box1").easydrag();
 
        // set a function to be called on a drop event
	$("#box1").ondrop(function(e, element){ alert(element + " Dropped"); });
});
</script>

$("#box2").easydrag();
$("#box2").setHandler('handler3');

It looks like you should set the z-index of the object. It's sitting under text if I move it over text and then I can no longer grab it. Also on firefox 2.0 on xp
--%>