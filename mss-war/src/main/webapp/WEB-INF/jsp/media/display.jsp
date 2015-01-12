
<%-- 
		<script type="text/javascript" src="/js/jquery.media.js"></script>

$('.media').media();

// global defautls; override as needed 
$.fn.media.defaults = { 
    preferMeta:    1,         // true if markup metadata takes precedence over options object 
    autoplay:      0,         // normalized cross-player setting 
    bgColor:       '#ffffff', // background color 
    params:        {},        // added to object element as param elements; added to embed element as attrs 
    attrs:         {},        // added to object and embed elements as attrs 
    flashvars:     {},        // added to flash content as flashvars param/attr 
    flashVersion:  '7',       // required flash version 
 
    // default flash video and mp3 player 
    // @see: http://jeroenwijering.com/?item=Flash_Media_Player 
    flvPlayer:     'mediaplayer.swf', 
    mp3Player:     'mediaplayer.swf', 
     
    // Silverlight options 
    // @see http://msdn2.microsoft.com/en-us/library/bb412401.aspx 
    silverlight: { 
        inplaceInstallPrompt: 'true', // display in-place install prompt? 
        isWindowless:         'true', // windowless mode (false for wrapping markup) 
        framerate:            '24',   // maximum framerate 
        version:              '0.9',  // Silverlight version 
        onError:              null,   // onError callback 
        onLoad:               null,   // onLoad callback 
        initParams:           null,   // object init params 
        userContext:          null    // callback arg passed to the load callback 
    } 
}; 
--%>


<a class="media" href="sample.mov">My Quicktime Movie</a> 
<a class="media" href="sample.swf">My Flash Movie</a> 
<a class="media" href="sample.wma">My Audio File</a> 