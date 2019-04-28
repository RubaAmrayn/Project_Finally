const getShared = ()=>{
	return new Promise((resolve,reject)=>{
	 var Code_id = localStorage.getItem('Code_id');
	var code = simpleQueryString.stringify({
		  'Code_id': Code_id
	});

			const config = {
			  headers: {
			    'Content-Type': 'application/x-www-form-urlencoded'
			  }
			}
	 axios.post('viewCode',code,config).then(response=>{
		 resolve(response)
	 })
		
	})
}
getShared().then(res=>{
     var title= document.querySelector('div.code-title h1');
     var desc= document.querySelector('div.code-desc h3');
     var create= document.querySelector('div.code-created span.created-date span');
     var permition = document.querySelector('div.code-created span.permition span');
     var language = document.querySelector('div.left h4');
     var content = document.querySelector('code');
     title.innerHTML = res.data.codes[0].Title;
     desc.innerHTML = res.data.codes[0].Code_desc == "null"?"":res.data.codes[0].Code_desc;
     create.innerHTML= res.data.codes[0].Create_Date.split(' ')[0];
     permition.innerHTML=res.data.codes[0].permition;
     language.innerHTML= res.data.codes[0].Language;
     content.innerHTML= res.data.codes[0].Content;
     content.className = res.data.codes[0].Language
});
