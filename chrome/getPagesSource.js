// @author Rob W <http://.com/users/938089/rob-w>
// Demo: var serialized_html = DOMtoString(document);

function DOMtoString() {
	
	var metas = document.getElementsByTagName('meta');
	
	var image;
	var description;
	
	for (var i=0; i<metas.length; i++) {
		
		if (metas[i].getAttribute("property") == "og:image") { 
			image = metas[i].getAttribute("content");
		}
		else if (metas[i].getAttribute("property") == "og:description") { 
			description = metas[i].getAttribute("content");
		}
	} 
	
	return {
		image : image,
		description : description
	};
	
	/*
    var html = '',
        node = document_root.firstChild;
    
    while (node) {
        switch (node.nodeType) 
        {
        case Node.ELEMENT_NODE:
        	html += node.outerHTML;
            break;
        case Node.TEXT_NODE:
            //html += node.nodeValue;
            break;
        case Node.CDATA_SECTION_NODE:
            //html += '<![CDATA[' + node.nodeValue + ']]>';
            break;
        case Node.COMMENT_NODE:
            //html += '<!--' + node.nodeValue + '-->';
            break;
        case Node.DOCUMENT_TYPE_NODE:
            // (X)HTML documents are identified by public identifiers
            //html += "<!DOCTYPE " + node.name + (node.publicId ? ' PUBLIC "' + node.publicId + '"' : '') + (!node.publicId && node.systemId ? ' SYSTEM' : '') + (node.systemId ? ' "' + node.systemId + '"' : '') + '>\n';
            break;
        }
        node = node.nextSibling;
    }
    return html;
    */
}

chrome.runtime.sendMessage({
    action: "getSource",
    source: DOMtoString()
});