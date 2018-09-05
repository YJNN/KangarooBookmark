$(document).ready(function(){
	$('#contact-submit').click(function () {
		
		$('#contact').submit();
		
		setTimeout(function() {
			$('#container').html('<div id="send"><h2>북마크 등록 완료</h2></send>');
		}, 2000);
	});
});

chrome.tabs.getSelected(null, function(tab) {
	    $('#title').val(tab.title);
	    $('#url').val(tab.url);
});

chrome.runtime.onMessage.addListener(function(request, sender) {
	
	if (request.action == "getSource") {
		//message.innerText = request.source;
		var metas = request.source;
		
		$('#image').val(metas.image);
		$('#description').val(metas.description);
		
		var url = $('#url').val();
		var video_id = url.split('v=')[1];
		var ampersandPosition = video_id.indexOf('&');
		if(ampersandPosition != -1) {
		  video_id = video_id.substring(0, ampersandPosition);
		}
		$('#video').val(video_id);
	}
});

function onWindowLoad() {

	var message = document.querySelector('#message');

	chrome.tabs.executeScript(null, {
		file: "getPagesSource.js"
	}, function() {
		// If you try and inject into an extensions page or the webstore/NTP you'll get an error
    
		if (chrome.runtime.lastError) {
			message.innerText = 'There was an error injecting script : \n' + chrome.runtime.lastError.message;
		}
	});
}

window.onload = onWindowLoad;