
var ads = function() {}
	
	ads.root_path = 'http://localhost:8080/myshopnshare/images/';
	ads.adsImages = new Array("img0.jpg", "img1.jpg", "img2.jpg", "img3.jpg", "img4.jpg", "img5.jpg", "img6.jpg", "beach1.jpg", "beach2.jpg", "beach3.jpg", "beach4.jpg", "beach5.jpg");
	ads.adsNames = new Array("<p>Enjoy fun in the sun with this great toy.</p>", "<p>Get ready for summer with the latest and greatest.</P>", "<p>Lorem ipsum docum minum suma duma luma uma</p>", "<p>Lorem ipsum docum minum suma duma luma uma</p>", "<p>Lorem ipsum docum minum suma duma luma uma</p>", "<p>Lorem ipsum docum minum suma duma luma uma</p>", "<p>Lorem ipsum docum minum suma duma luma uma</p>", "<p>Lorem ipsum docum minum suma duma luma uma</p>", "<p>Lorem ipsum docum minum suma duma luma uma</p>", "<p>Lorem ipsum docum minum suma duma luma uma</p>", "<p>Lorem ipsum docum minum suma duma luma uma</p>", "<p>Lorem ipsum docum minum suma duma luma uma</p>");
	ads.thisad = 0;
	ads.previousThisAd = 0;
	ads.maxadListSize = 7;
	ads.stop = false;
	
	ads.buildads = function(){
			var adList = "";
			var start = ads.thisad;
			if(ads.previousThisAd > ads.thisad){
				start = ads.thisad > ads.maxadListSize ? ads.thisad - ads.maxadListSize : 0;
			}			
			var displaySize = ads.maxadListSize + start;
			if(displaySize > ads.adsImages.length){
				start = ads.adsImages.length - ads.maxadListSize;
			}
			for ( var i = start; i < ads.adsImages.length && i < displaySize; i++) {
				adList = adList + "<li><a href=\"javascript: ads.switchImageAds(" + i + ")\"><img id=\"" + i + "\" src=\"" + ads.root_path + ads.adsImages[i] + "\" width=\"20\" height=\"20\" alt=\"" + ads.adsNames[i] + "\" /></a></li>\n";
			}
			document.getElementById("ads-list").innerHTML = adList;
		}

	ads.switchImageAds = function (imageIndex){
			document.getElementById("ad-image-link").href = ads.root_path + ads.adsImages[imageIndex];
			document.getElementById("ad-image").src = ads.root_path + ads.adsImages[imageIndex];
			document.getElementById("ad-image").title = ads.adsNames[imageIndex];	
			document.getElementById("ad-name").innerHTML = ads.adsNames[imageIndex];	
			ads.previousThisAd = ads.thisad;
			ads.thisad = imageIndex;
			ads.buildads();
		}

	ads.rotateAds = function (){
			ads.buildads();
			ads.thisad++;
			if(ads.thisad == ads.adsImages.length){
				ads.thisad = 0;
			}
			document.getElementById("ad-image-link").href = ads.root_path + ads.adsImages[ads.thisad];
			document.getElementById("ad-image").src = ads.root_path + ads.adsImages[ads.thisad];
			document.getElementById("ad-image").title = ads.adsNames[ads.thisad];
			document.getElementById("ad-name").innerHTML = ads.adsNames[ads.thisad];
			if(!ads.stop){
				setTimeout(ads.rotateAds, 3 * 2000);
			}
		}
