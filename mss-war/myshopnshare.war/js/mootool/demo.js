
window.addEvent('domready', function() {
	//create our Accordion instance
	var myAccordion = new Accordion($('ad-accordion'), 'h3.toggler', 'div.element', {
		opacity: false,
		onActive: function(toggler, element){
			toggler.setStyle('color', '#41464D');
		},
		onBackground: function(toggler, element){
			toggler.setStyle('color', '#528CE0');
		}
	});

	//add click event to the "add section" link
	$('add_section').addEvent('click', function(event) {
		event.stop();
		
		// create toggler
		var toggler = new Element('h3', {
			'class': 'toggler',
			el: 'ads-box'
		});
		
		// create content
		var content = new Element('div', {
			'class': 'element',
			el: 'user-info'
		});
		
		// position for the new section
		var position = 0;
		
		// add the section to our myAccordion using the addSection method
		myAccordion.addSection(toggler, content, position);
	});
});
/*
	//scripts have been put here as a courtesy to you, the sourcecode viewer.
	
	//this script uses mootools: http://mootools.net
	
	var stretchers = $$('div.accordion');
	var togglers = $$('h3.toggler');

	stretchers.setStyles({'height': '0', 'overflow': 'hidden'});
	
	window.addEvent('load', function(){
		
		//initialization of togglers effects
		
		togglers.each(function(toggler, i){
			toggler.color = toggler.getStyle('background-color');
			toggler.$tmp.first = toggler.getFirst();
			toggler.$tmp.fx = new Fx.Style(toggler, 'background-color', {'wait': false, 'transition': Fx.Transitions.Quart.easeOut});
		});
		
		//the accordion
		
		var myAccordion = new Accordion(togglers, stretchers, {
			
			'opacity': false,
			
			'start': false,
			
			'transition': Fx.Transitions.Quad.easeOut,
			
			onActive: function(toggler){
				toggler.$tmp.fx.start('#e0542f');
				toggler.$tmp.first.setStyle('color', '#fff');
			},
		
			onBackground: function(toggler){
				toggler.$tmp.fx.stop();
				toggler.setStyle('background-color', toggler.color).$tmp.first.setStyle('color', '#222');
			}
		});
		
		//open the accordion section relative to the url
		
		var found = 0;
		$$('h3.toggler a').each(function(link, i){
			if (window.location.hash.test(link.hash)) found = i;
		});
		myAccordion.display(found);
		
	});
*/