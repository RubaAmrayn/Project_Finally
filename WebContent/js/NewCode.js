window.addEventListener('load',()=>{//page load time
	var btn_send = document.querySelector('button#btn_send');
	btn_send.addEventListener('click',function(e){//when click Execution
	   e.preventDefault();//
	   var Content=document.querySelector('textarea#code_area').value
	   var Language=document.querySelector('select#syntax_list').value
	   var Code_desc=document.querySelector('textarea#desc').value
	   var Title=document.querySelector('input#title').value
	   var permition=document.querySelector('select#permition').value 
	   var code =  simpleQueryString.stringify({//object
		   Content:Content,
		   Language:Language,
		   Code_desc :Code_desc,
		   Title:Title,
		   permition: permition,
		   Users_user_id:localStorage.getItem('user_id')	
			})
		const config = {
			  headers: {
			    'Content-Type': 'application/x-www-form-urlencoded'
			  }
			}
	   axios.post('NewCode',code,config).then(response=>{
		   
		   if(!!response.data.code_id){
				routers.push('Home')//call another page
			}
	   })
	})
})
	