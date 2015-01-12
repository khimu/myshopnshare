/*
 *  Copyright 2006, 2007, 2008 by Jens Boje
 *  Name    : Imago 
 *  Version : 0.7
 *  Author  : Jens Boje (azarai@codeboje.de)
              http://codeboje.de/contact/
	Terms of Use:
			Imago may be used in any kinds of personal and/or commercial projects. 
			Redistribution or reselling to other companies or third parties is prohibited.
			Specifically, no Redistribution as part of a content management system or online hosting solution.
*/

var GalleryImage = new Class({
	initialize: function(fileName, title, thumbURL, imageURL) {
		this.fileName = fileName;
		this.title = title;
		this.thumbURL = thumbURL;
		this.imageURL = imageURL;
	},
	getFileName: function() {
		return this.fileName;
	},
	getTitle: function() {
		return this.title;
	},
	getID: function() {
		return 'id_' + this.getFileName();
	},
	getThumbURL: function() {
		return this.thumbURL;
	},
	getImageURL: function() {
		return this.imageURL;
	}	
});

var Gallery = new Class({
	initialize: function() {
		this.prefetching = null;
		this.menuDivPosition = 'top';
		this.thumbnailColumns="3";
		this.thumbnailRows="3";
		eval($('imagogallery').innerHTML);
		this.createLayout();
		this.menuDiv = $('imagoThumbMenu');
		this.images = new Array();

		this.lastThumbImageIndex=0;
		this.lastThumbsOnCurrentPage=0;
		this.thumbsPerPage = this.thumbnailRows * this.thumbnailColumns;
		this.title ='';
		this.indexCurrentImage =0;
	},
	addImage: function(img) {
		var counter=0;
		if (this.images.length !=0) {
			counter = this.images.length;
		}
		this.images[counter]=img;
	},
	getThumbImage: function(index) {
		if(this.images != null && this.images.length != 0 && index <= this.images.length) {
			if(this.images[index] != null) {
				var img = new Element("img");
				img.setProperty('src',  this.images[index].getThumbURL());
				img.addClass('imago_thumbImg');
				img.setProperty('alt',  this.images[index].getTitle());
				img.setProperty('id', this.images[index].getID());
				img.onclick = this.switchImage.bind(this.images[index]);
				return img;			
			}
		}
		return null;
	},
	getCurrentThumbTable: function() {
		var thumbTable = new Element("table");
		var thumbTableBody = new Element("TBODY");		
		 
		thumbTable.setProperty('id', 'imagoCurrentThumbTable');
		var counter = this.lastThumbImageIndex;
		if (this.lastThumbImageIndex == 0) {
			ElementHelper.hide('imagoNavPrevLink');
		} else {
			ElementHelper.show('imagoNavPrevLink');
		}
		this.lastThumbsOnCurrentPage =0;
		for(i=0;i<this.thumbnailRows;i++) { 
		  var tr = new Element("tr");
		  		for(j=0;j<this.thumbnailColumns;j++) { 
				  var td = new Element("td");
				  if (this.getThumbImage(counter) != null) {
					  td.appendChild(this.getThumbImage(counter));
					  counter++;
					  this.lastThumbsOnCurrentPage++;
					  if(this.images.length>this.thumbsPerPage) {
						  ElementHelper.show('imagoNavNextLink');
					  }
				  } else {
					  ElementHelper.hide('imagoNavNextLink');
				  }
				  tr.appendChild(td);
			 	}
		  thumbTableBody.appendChild(tr);
	 	}
	 	thumbTable.appendChild(thumbTableBody);
	 	this.lastThumbImageIndex = counter;
	 	return thumbTable;
	},
	thumbMenuNext: function() {
		if (this.images.length>this.lastThumbImageIndex) {
			$('imagoCurrentThumbTable').remove();
			this.menuDiv.appendChild(this.getCurrentThumbTable());
		}	
	},
	thumbMenuPrev: function() {
		if(this.lastThumbImageIndex > this.thumbsPerPage) {
			this.lastThumbImageIndex -= (this.lastThumbsOnCurrentPage + this.thumbsPerPage);
			if(this.lastThumbImageIndex<0) {
				this.lastThumbImageIndex=0;
			}
			$('imagoCurrentThumbTable').remove();
			this.menuDiv.appendChild(this.getCurrentThumbTable());
		}	
	},
	nextImage: function() {
		if(this.indexCurrentImage +1 < this.images.length) {
			if(this.indexCurrentImage +1 >= this.lastThumbImageIndex) {
				this.thumbMenuNext(this);				
			}
			doSwitchImage = this.switchImage.bind(this.images[this.indexCurrentImage +1]);
			doSwitchImage();
		}
	},
	previousImage: function() {
		if(this.indexCurrentImage -1 >= 0) {
			if(this.indexCurrentImage -1< (this.lastThumbImageIndex - this.lastThumbsOnCurrentPage)) {
				this.thumbMenuPrev(this);				
			}
			doSwitchImage = this.switchImage.bind(this.images[this.indexCurrentImage -1]);
			doSwitchImage();
		}
	},	
	showGallery: function() {
		var navPrevLink = new Element("a");
		navPrevLink.className = 'imago_navPrev';
		navPrevLink.setProperty('id', 'imagoNavPrevLink');
		navPrevLink.onclick = this.thumbMenuPrev.bind(this);
		this.menuDiv.appendChild(navPrevLink);
		ElementHelper.hide('imagoNavPrevLink');

		var navNextLink = new Element("a");
		navNextLink.className = 'imago_navNext';
		navNextLink.setProperty('id', 'imagoNavNextLink');
		navNextLink.onclick = this.thumbMenuNext.bind(this);
		this.menuDiv.appendChild(navNextLink);	
		ElementHelper.hide('imagoNavNextLink');
		
		if (this.images.length>this.thumbsPerPage) {
			ElementHelper.show('imagoNavNextLink');
		}
		this.menuDiv.appendChild(this.getCurrentThumbTable());
		if(this.prefetching != null) {
			this.prefetchImages();
		}

		var img = new Element("img");
		img.setProperty('id', 'imagoCurrentImg');
		img.setProperty('alt', this.images[0].getTitle());
		img.setProperty('title', this.images[0].getTitle());
		if(this.title != null && this.title != "")
			ElementHelper.setInnerHTML('imagoGalleryTitle',this.title);
		else
			ElementHelper.setInnerHTML('imagoGalleryTitle',"&nbsp;");
		ElementHelper.setInnerHTML('imagoCurrentImageTitle',this.images[0].getTitle());	
		this.setCurrentSelection(this.images[0].getID());
		img.addEvent('load', gallery.applyLayoutFixes.bind(this.images[0]));
		img.setProperty('src', this.images[0].getImageURL());
		img.injectInside('imagoCurrentImageDiv');
		
		
		
		var nextImageLink = new Element("a");
		nextImageLink.className = 'imago_navNextImage';
		nextImageLink.setProperty('id', 'imagoNextImageLink');
		nextImageLink.setProperty('href', '#');
		nextImageLink.setProperty('accesskey', 'n');
		nextImageLink.onclick = this.nextImage.bind(this);
		nextImageLink.injectAfter('imagoCurrentImg');	
		ElementHelper.hide('imagoNextImageLink');
		
		if (this.images.length>1) {
			ElementHelper.show('imagoNextImageLink');
		}
		
		var prevImageLink = new Element("a");
		prevImageLink.className = 'imago_navPreviousImage';
		prevImageLink.setProperty('id', 'imagoPreviousImageLink');
		prevImageLink.setProperty('href', '#');
		prevImageLink.setProperty('accesskey', 'p');
		prevImageLink.onclick = this.previousImage.bind(this);
		prevImageLink.injectBefore('imagoCurrentImg');
		ElementHelper.hide('imagoPreviousImageLink');
		ElementHelper.hide('imagoLoadingDiv');
		ElementHelper.hide('imagoCurrentImageLoadingDiv');
		
		document.addEvent('keydown', gallery.handleKey.bind(this));
	},
	handleKey : function(event) {
		var keycode = event.keyCode;
		switch(keycode) {
			// left arrow
			case 37:		// firefox
			case 63234:		// safari
				prv = gallery.previousImage.bind(this);
				prv();
				break;
			// right arrow
			case 39:		// firefox
			case 63235:		// safari
				nxt = gallery.nextImage.bind(this);
				nxt();
				break;						
		}
	},	
	switchImage: function() {
		$('imagoCurrentImageLoadingDiv').setStyle('width', $('imagoCurrentImg').getSize().size.x);
		$('imagoCurrentImageLoadingDiv').setStyle('height', $('imagoCurrentImg').getSize().size.y);
		ElementHelper.show('imagoCurrentImageLoadingDiv');
		file = this.getImageURL();
		title = this.getTitle();
		if ($(gallery.getCurrentSelection()) != null) {
			$(gallery.getCurrentSelection()).removeClass('imago_selectedThumb');
		}
		cimage = this;
		gallery.setCurrentSelection(this.getID());
		var myFx = new Fx.Style('imagoCurrentImg', 'opacity', {duration:1000, onComplete: function() {
			oldSrc = $('imagoCurrentImg').getProperty('src');
		
			$('imagoCurrentImg').addEvent('load', gallery.fadeIn.bind(cimage));
    		$('imagoCurrentImg').setProperty('src', file);
			$('imagoCurrentImg').setProperty('alt', title);
			$('imagoCurrentImg').setProperty('title', title);
    				
			ElementHelper.setInnerHTML('imagoCurrentImageTitle',title);
			if (window.opera) {
			    $('imagoCurrentImg').removeEvents('load');
				fireFadein = gallery.fadeIn.bind(cimage);
				fireFadein();
	        }
		}, onStart: function() {
		  	$('imagoCurrentImg').removeEvents('load');
	      	cimage.img = new Image();
			cimage.img.src = file;
    }}).custom(1,0);
	},
	fadeIn: function() {
    i = this.img;
		ElementHelper.hide('imagoCurrentImageLoadingDiv');
		$('imagoCurrentImg').setStyles({width: i.width + 'px', height: i.height + 'px'});
		gallery.applyLayoutFixes();

		var myFx = new Fx.Style('imagoCurrentImg', 'opacity', {duration:1000}).custom(0,1);
		if (gallery.indexCurrentImage +1 == gallery.images.length) {
			ElementHelper.hide('imagoNextImageLink');
		} else {
			ElementHelper.show('imagoNextImageLink');
		}
		if (gallery.indexCurrentImage == 0) {
			ElementHelper.hide('imagoPreviousImageLink');
		} else {
			ElementHelper.show('imagoPreviousImageLink');
		}
	},
	applyLayoutFixes: function() {
		$('imagoCurrentImageDiv').setStyle('width', $('imagoCurrentImg').getSize().size.x);
		$('imagoFrame').setStyle('width', $('imagoCurrentImg').getSize().size.x);
		$('imagoNextImageLink').setStyle('height', $('imagoCurrentImg').getSize().size.y);
		$('imagoPreviousImageLink').setStyle('height', $('imagoCurrentImg').getSize().size.y);
	},
	getCurrentSelection: function() {
		return this.selection;
	},
	setCurrentSelection: function(selection) {
		this.selection = selection;
		$(this.selection).addClass('imago_selectedThumb');
		for (var i = 0; i < this.images.length; i++) {
			if(this.images[i].getID() == this.selection) {
				this.indexCurrentImage = i;
			}
		}		
	},
	prefetchImages: function() {
		var thumbURLs = new Array();
		var imageURLs = new Array();
		for(var i=0; i< this.images.length; i++) {
			thumbURLs.push(this.images[i].getThumbURL());
			imageURLs.push(this.images[i].getImageURL());
		}
		new Asset.images(thumbURLs, {
		    onComplete: function(){
			    if(gallery.prefetching == 'all') {
					new Asset.images(imageURLs, {
					    onComplete: function(){
					    }
					});
				}
		    }
		});
		
	},	
	createLayout: function() {
		var menuLayout = '<div id="imagoThumbMenu"><h3 id="imagoGalleryTitle"></h3></div>';
		var layout = '';
		if (this.menuDivPosition == 'top') {
			layout = layout + menuLayout;
		}
		layout = layout +
			'<div id="imagoFrame">' +
			'<div id="imagoCurrentImageLoadingDiv"></div>' +
			'<a id="imagoError">Gallery definition or Gallery server not available.</a>' +
			'<div id="imagoCurrentImageDiv">' +
			'<div id="imagoCurrentImageLoadingDiv" ></div>' +
			'</div>' +
			'<h3 id="imagoCurrentImageTitle" ></h3>' + 
			'</div>';
		if (this.menuDivPosition == 'bottom') {
			layout = layout + menuLayout;
		}
		layout = layout +
			'<div id="imagoLoadingDiv"></div>';

		ElementHelper.setInnerHTML('imagogallery', layout);
	}
});

var GalleryLoader = new Class({
    initialize: function(galleryFileName, baseURL, albumName){
		this.galleryFileName= galleryFileName;
		this.baseURL= baseURL;
		this.albumName = albumName;
    },
    load: function() {
    	try {
	    	ElementHelper.show('imagoLoadingDiv');
			ElementHelper.setOpacity('imagoLoadingDiv', 0.5);
			this.specialLoading();
    	} catch(e) {
    		this.error("Can't load the config");
		}
    },
    finished: function() {  
   		gallery.showGallery();
    },
    error: function(msg) {
    	$('imagoError').setText(msg);
    	ElementHelper.show('imagoError');
    	ElementHelper.hide('imagoLoadingDiv');
    },
    specialLoading: function() {
        this.albumBaseURL = "";
        if (this.baseURL != null) {
	        this.albumBaseURL = this.baseURL + "/";
        }
		if (this.albumName != null) {
			this.albumBaseURL = this.albumBaseURL + this.albumName + "/";
		}
		var myAjax = new Ajax(this.albumBaseURL + this.galleryFileName, {method: 'get', onComplete: function() {
			if(this.response.xml != null) {
				gallery.loader.readGalleryXML(this.response.xml);	
			} else {
				gallery.loader.error("Retrieved empty gallery config");	
			}
		}, onFailure: function(){
			if (this.transport.status == 0 && this.transport.responseXML) {
				//assume we are running local                
                if (window.ie) {
                    // strange workaround for ie
                    var result = this.transport.responseXML;
                    if (!result.documentElement && this.transport.responseStream) {
                        result.load(this.transport.responseStream);
                    }
                }
				gallery.loader.readGalleryXML(this.transport.responseXML);
			} else {
				gallery.loader.error("Can't connect to server and retrieve the gallery config");	
			}
		}}).request();
    },
    readGalleryXML:function(galleryXML) {
			gallery.thumbnailColumns=getAttributeValue(galleryXML, 'simpleviewerGallery', 'thumbnailColumns');
			gallery.thumbnailRows=getAttributeValue(galleryXML, 'simpleviewerGallery', 'thumbnailRows');
			thumbnailPath=getAttributeValue(galleryXML, 'simpleviewerGallery', 'thumbPath');
			imagePath=getAttributeValue(galleryXML, 'simpleviewerGallery', 'imagePath');
			gallery.thumbsPerPage = gallery.thumbnailRows * gallery.thumbnailColumns;
			gallery.title = getAttributeValue(galleryXML, 'simpleviewerGallery', 'title');
			
		    var images = galleryXML.getElementsByTagName('image');
			for (var i=0;i<images.length;i++) {
				caption = getNodeValue(images[i], 'caption');
				if (caption == null) {
					caption = "&nbsp;";
				}
				var fileName = getNodeValue(images[i], 'filename');
				gallery.addImage(new GalleryImage(fileName,caption, this.albumBaseURL + thumbnailPath + fileName, this.albumBaseURL + imagePath + fileName));
			}
			gallery.loader.finished();
		}
});

var FlickrLoader = GalleryLoader.extend({
    initialize: function(){
    },
    specialLoading: function() {
    	new MooPix().callFlickrUrl({ method: 'flickr.photos.getRecent', per_page: '15' });
		jsonFlickrApi = function(rsp){
			if (rsp.stat == 'ok' ){
				if (rsp.photos){
					for(var i=0;i <rsp.photos.photo.length; i++) {
						var url = 'http://static.flickr.com/'+rsp.photos.photo[i].server+'/'+rsp.photos.photo[i].id+'_'+rsp.photos.photo[i].secret; 
						gallery.addImage(new GalleryImage(rsp.photos.photo[i].id,rsp.photos.photo[i].title, url +'_s.jpg', url+'.jpg'));
					}
					gallery.title = 'Latest Flickr Photos';
					gallery.loader.finished();
				}
			} else {
				gallery.loader.error("Can't load photos from flickr");
			}
		}
    }
});

var SmugmugLoader = GalleryLoader.extend({
    initialize: function(albumId, showCaptions){
	    this.albumId = albumId;
	    this.showCaptions = showCaptions;
    },
    specialLoading: function() {
	    new MooSmug().callSmugmug({ method: 'smugmug.login.anonymously' });
		jsonSmugmugApi = function(rsp){
				if (rsp.stat == 'ok' ){
					if (rsp.Login){
						gallery.loader.sessionId = rsp.Login.Session.id;
						new MooSmug().callSmugmug({ method: 'smugmug.albums.getInfo', AlbumID: gallery.loader.albumId, SessionID: gallery.loader.sessionId });
					} else if (rsp.Album) {
						gallery.title = rsp.Album.Title;
						new MooSmug().callSmugmug({ method: 'smugmug.images.get', AlbumID: gallery.loader.albumId, Heavy : gallery.loader.showCaptions, SessionID: gallery.loader.sessionId });
					} else if (rsp.Images) {
						for(var i=0;i <rsp.Images.length; i++) {
							var url ='http://smugmug.com/photos/' + rsp.Images[i].id;
							var caption = "";
							if (gallery.loader.showCaptions) {
								caption =rsp.Images[i].Caption;
							}
							gallery.addImage(new GalleryImage(rsp.Images[i].id, caption, url + '-75x75.jpg', url+ '-M.jpg'));
						}					
						gallery.loader.finished();						
					}
				} else {
					gallery.loader.error("Can't load photos from smugmug");
				}
		}
    }
});


//inspired by mooshow
var ImagoElement = new Class({
    initialize: function(){
    
    },
	getWidth: function(element) {
	   	element = $(element);
	   	return element.offsetWidth; 
	},
	getHeight: function(element) {
	   	element = $(element);
	   	return element.offsetHeight; 
	},
	setInnerHTML: function(element,content) {
		element = $(element);
		element.innerHTML = content;
	},	
	hide: function(element) {
      	element = $(element);
      	element.style.display = 'none';
  	},
  	show: function(element) {
      	element = $(element);
      	element.style.display = 'inline';
  	},
	setOpacity: function(element,opacity) {
    	element = $(element);
    	element.style.opacity = opacity; 
	}
});

var ElementHelper = new ImagoElement();

function getNodeValue(obj,tag) {
	child = obj.getElementsByTagName(tag)[0].firstChild;
	if(child != null) {
		return child.nodeValue;
	} 
	return null;
}

function getAttributeValue(obj,tag, attr) {
	return obj.getElementsByTagName(tag)[0].getAttribute(attr);
}
//inspired by mooshow
function addLoadEvent(func) {
	var oldonload = window.onload;
	if (typeof window.onload != 'function') {
		window.onload = func;
	} else {
		window.onload = function() {
			oldonload();
			func();
		}
	}
}
var gallery;
function start() {
	gallery = new Gallery();
	gallery.loader.load();
}

addLoadEvent(start);
