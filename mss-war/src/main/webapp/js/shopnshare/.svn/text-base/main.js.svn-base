Ext.onReady(function()
{
	
	viewport = new Ext.Viewport({
		monitorResize: true ,
		id: 'main-layout',
		layout:'border',
		items:[{ // raw
			region:'north',
			contentEl: 'north',
			margins:'0 20px 0 20px' ,
			split: false ,
			autoHeight: false ,
			layout: 'fit',
			height: 50
		},{
			id: 'east-panel',
		    title: 'Friends',
		    collapsible: true ,
		    layout:'accordion',
		    margins:'0 20px 0 0' ,
		    region:'east',
		    contentEl: 'east',
		    defaults: {
		        // applied to each contained panel
		        bodyStyle: 'border: 0px none white'
		    },
		    layoutConfig: {
		        // layout-specific configs go here
		        titleCollapse: false,
		        animate: true,
		        activeOnTop: false
		    },
		    items: [{
		        title: 'Friends',
		        contentEl: 'friends-box'
		    },{
		        title: 'Friends Updates',
		        contentEl: 'friends-updates'
		    },{
		        title: 'Bank Account',
		        contentEl: 'virtual-bank'
		    },
		    {
		        title: 'Shopping Bucket',
		        contentEl: 'shopping-bucket'
		    }]
			
		},{
			region:'west',
			id:'west-panel',
			collapsible: true ,
			title:'Advertisements',
			layout: 'accordion',
			margins:'0 0 0 20px' ,
			contentEl: 'west',
			defaults: {
	        	// applied to each contained panel
	        	bodyStyle: 'border: 0px none white'
		    },
		    layoutConfig: {
		        // layout-specific configs go here
		        titleCollapse: false,
		        animate: true,
		        activeOnTop: false
		    },
		    items: [{
		        title: 'Advertisements',
		        contentEl: 'ads-box'
		    },{
		        title: 'Information',
		        contentEl: 'user-info'
		    }]

		},{
			region:'center',
			id: 'mainCentralPanel',
			width: 640 ,
			layout: 'fit',
			autoHeight: false ,
			bodyStyle: 'position: relative;background-color:#FFFFFF;border: 0px none #FFF' ,
			//autoScroll: true ,
			contentEl: 'center'
		},
		{
			region:'south',
			height: 60,
			layout: 'fit',
			margins:'0 20px 0 20px' ,
			contentEl: 'south-panel'
		}		
		]
	});
}) ;
